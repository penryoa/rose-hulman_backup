import neo4j
from neo4j import GraphDatabase

driver = GraphDatabase.driver(encrypted=False,uri="bolt://localhost:7687",auth=("neo4j","no"))
s = driver.session()

# drops the current collections and rebuilds them
def initialize():
	s.run("MATCH (n) DETACH DELETE n")
	addBook("1","The Lizard of Oz",["Lizard (L.) Frank Baum"],"92")
	addBook("4","Lizzy McGuire",["Frillary Duff","Lizzy"],"100")
	addBook("2","King Gizzard and the Lizard Wizard",["Lithp Lithard"],"21")
	addBook("3","Cool as a Blizzard Lizard",["Bliz the Whiz Liz"],"53")
	addBook("5","Keep Your Friends Gross and Your Enemies Grosser",["Scissor Lizard"],"7")
	addBorrower("LizaRD Minnelli","liza","9231731491")
	addBorrower("Lizard Boy Scales McStuffin", "scales", "723482910")
	addBorrower("Charma Charma Charmeleon", "charma", "2319341304")
	checkoutBook("scales","1")
	checkoutBook("scales","2")
	checkoutBook("liza","4")
	rateBook("charma","4","5","What a quirky book! Loved it. Got my gizzard ruffled up.")
	rateBook("charma","3","1","Terrible book. ")

# closes the session
def close_session():
	s.close()

# ======================================
#         A U T H O R S
# ======================================
# =========== PRINT OUT ALL AUTHORS
def printAllAuthors(asc="ASC"):
	result = s.run("MATCH (a:Author)-[:WROTE]->(b:Book) WITH a,b ORDER BY a.name " + asc + " RETURN a,b")
	for c in result:
		print("------------------------------")
		printBook(isbn=c["b"]["isbn"], title=c["b"]["title"],numpgs=c['b']["numpgs"],available=c["b"]["available"])
		print("author name: " + c['a']['name'])

## =========== DELETE AN UNUSED AUTHOR AFTER A BOOK IS DELETED
def deleteAuthorIfUnused(name):
	r = s.run("MATCH (a:Author {name:"+'"'+name+'"'+"}) MATCH (a)-[:WROTE]->() RETURN count(*) as c")
	for ugh in r:
		if ugh["c"] <= 0:
			s.run("MATCH (a:Author {name:"+'"'+name+'"'+"}) DELETE a")

# =========== ADD AN AUTHOR
def addAuthor(name,isbn=None):
	s.run("MERGE (auth:Author{name:"+'"'+name+'"'+"})")
	if isbn!=None:
		s.run("MATCH (b:Book "+'{'+ "isbn:{}".format(isbn) + "})" + " MATCH (au:Author {name:"+'"'+name+'"'+"}) CREATE (au)-[w:WROTE]->(b) RETURN w")


# ======================================
#         B O O K S
# ======================================
# =========== PRINT OUT ALL BOOKS
def printAllBooks(sortBy=None,asc="ASC"):
	if sortBy==None: 
		result = s.run("MATCH (b:Book) MATCH (a:Author)-[:WROTE]->(b) WITH a,b ORDER BY b." + "isbn" + " " + asc + " RETURN collect(a.name) as a,b")
	elif sortBy=="author":
		printAllAuthors(asc)
		return
	else:
		result = s.run("MATCH (b:Book) MATCH (a:Author)-[:WROTE]->(b) WITH a,b ORDER BY b." + sortBy + " " + asc + " RETURN collect(a.name) as a,b")
	 
	for c in result:
		print("------------------------------")
		printBook(isbn=c["b"]["isbn"], title=c["b"]["title"],numpgs=c["b"]["numpgs"],available=c["b"]["available"])
		print("author names: ", c["a"])

# =========== PRINT ONE BOOK
def printBook(isbn,title,numpgs,available):
	print("title: ", title, "   isbn: ", isbn, "   number of pages: ", numpgs, "   available: ", available)

# =========== RETURNS WHETHER OR NOT A BOOK EXISTS
def bookExists(isbn):
	r = searchBook("isbn",isbn,False)
	count = 0
	for c in r:
		count+=1
	return count!=0

# =========== ADD A BOOK
def addBook(isbn,title,authors,numpgs):
	if bookExists(isbn):
		print("The book already exists in the system")
		return

	s.run("CREATE (test:Book {"
			+"isbn:" + isbn
			+",title:" + '"'+title+'"'
			+",numpgs:" + numpgs
			+",available:True"
			+"}) RETURN test")

	for a in authors:
		addAuthor(a,isbn)

	print("OK")

# =========== DELETE A BOOK
def delBook(isbn):
	if not bookExists(isbn):
		print("The book does not exist in the system")
		return

	names = s.run("MATCH (b:Book)<-[w:WROTE]-(a:Author) WHERE b.isbn={} DETACH DELETE w RETURN a.name as n".format(isbn))
	for a in names:
		deleteAuthorIfUnused(a["n"])
	s.run("MATCH (b:Book)<-[r:BORROWED]-(u:Borrower) WHERE b.isbn={} DETACH DELETE r RETURN u.uname as u".format(isbn))
	s.run("MATCH (b:Book) WHERE b.isbn={} DETACH DELETE b".format(isbn))
	delRating(isbn=isbn)
	print("OK")

# =========== EDIT A BOOK
def editBook(old_isbn, new_isbn, new_title, new_author,authorInfo, new_len):
	if not bookExists(old_isbn):
		print("The book does not exist in the system")
		return
	if new_title != "None":
		s.run("MATCH (b:Book) WHERE b.isbn={} SET b.title=".format(old_isbn) + '"'+new_title+'"')
	if new_author != "None":
		addAuthor(new_author,old_isbn)
	if authorInfo != "None":
		s.run("MATCH (a:Author)-[:WROTE]->(b:Book) WHERE b.isbn={} AND a.name=".format(old_isbn) + '"'+authorInfo[0]+'"' + " SET a.name=" + '"'+authorInfo[1]+'"')
	if new_len != "None":
		s.run("MATCH (b:Book) WHERE b.isbn={} SET b.numpgs=".format(old_isbn) + new_len)
	if new_isbn != "None":
		s.run("MATCH (b:Book) WHERE b.isbn={} SET b.isbn=".format(old_isbn) + new_isbn)
	print("OK")

# =========== SEARCH FOR A BOOK
def searchBook(val,term,showResult=True):
	if val=="title":
		r = s.run("MATCH (b:Book) WHERE b." + val + "=" + '"' + term + '"' + "MATCH (a:Author)-[:WROTE]->(b) RETURN collect(a.name) as a,b")
	elif val=="isbn":
		r = s.run("MATCH (b:Book) WHERE b.{}={} MATCH (a:Author)-[:WROTE]->(b) RETURN collect(a.name) as a,b".format(val,term))
	elif val=="author":
		r = s.run("MATCH (a:Author)-[:WROTE]->(b:Book) WHERE a.name=" + '"' + term + '"' + " RETURN collect(a.name) as a,b")
	if showResult:
		for c in r:
			printBook(isbn=c["b"]["isbn"], title=c["b"]["title"],numpgs=c["b"]["numpgs"],available=c["b"]["available"])
			print("author names: ", c["a"])
			print("------------------------------")
	return r

# ======================================
#         B O R R O W E R S
# ======================================
# =========== PRINT OUT ALL BORROWERS
def printAllBorrowers():
	borrowers = s.run("MATCH (u:Borrower) RETURN u")
	for c in borrowers:
		print("------------------------------")
		printBorrower(c["u"]["uname"], c["u"]["name"], c["u"]["phone"])

# =========== PRINT OUT ONE BORROWER
def printBorrower(uname, name, phone):
	print("username: ", uname, "   name: ", name, "   phone: ", phone)

# =========== RETURNS WHETHER OR NOT A BORROWER EXISTS
def borrowerExists(uname):
	r = searchBorrower("uname",uname,False)
	count = 0
	for c in r:
		count+=1
	return count!=0

# =========== ADD A BORROWER
def addBorrower(name,uname,phone):
	if borrowerExists(uname):
		print("The borrower already exists in the system")
		return
	s.run("CREATE (test:Borrower {"
			+"name:" + '"'+name+'"'
			+",uname:" + '"'+uname+'"'
			+",phone:" + phone
			+"}) RETURN test")
	print("OK")

# =========== DELETE A BORROWER
def delBorrower(uname):
	if not borrowerExists(uname):
		print("The borrower does not exist in the system")
		return
	s.run("MATCH (u:Borrower {uname: " +'"'+uname+'"'+ "})-[r:BORROWED]->(:Book) DETACH DELETE r") #separate if they don't have any books borrowed
	s.run("MATCH (u:Borrower {uname: " +'"'+uname+'"'+ "}) DETACH DELETE u")
	delRating(uname=uname)
	print("OK")

# =========== EDIT A BORROWER
def editBorrower(old_uname,new_uname,new_name,new_num):
	if not borrowerExists(old_uname):
		print("The borrower does not exist in the system")
		return
	if new_name != "None":
		s.run("MATCH (u:Borrower{uname:" + '"' + old_uname + '"'+"}) SET u.name="+'"'+new_name+'"')
	if new_num != "None":
		s.run("MATCH (u:Borrower{uname:" + '"' + old_uname + '"'+"})" + " SET u.phone={}".format(new_num))
	if new_uname != "None":
		s.run("MATCH (u:Borrower{uname:" + '"' + old_uname + '"'+"}) SET u.uname="+'"'+new_uname+'"')
	print("OK")

# =========== SEARCH FOR A BORROWER
def searchBorrower(val,term,showResult=True):
	r = s.run("MATCH (u:Borrower{" + val + ":" + '"' + term + '"})' +" RETURN u")
	if showResult:
		for c in r:
			print("------------------------------")
			printBorrower(c["u"]["uname"], c["u"]["name"], c["u"]["phone"])			
	return r

# ======================================
#         C H E C K O U T S
# ======================================
# =========== RETURNS WHETHER A BOOK IS BORROWED OR NOT
def bookAvailable(isbn):
	r = s.run("MATCH (:Borrower)-[:BORROWED]->(b:Book) WHERE b.isbn={} RETURN count(*) as c".format(isbn))
	for ugh in r:
		if ugh["c"] <= 0:
			return True
	return False

# =========== CHECK OUT A BOOK
def checkoutBook(uname, isbn):
	if borrowerExists(uname) and bookExists(isbn) and bookAvailable(isbn):
		s.run("MATCH (u:Borrower) WHERE u.uname=" + '"' + uname + '"'+" MATCH (b:Book) WHERE b.isbn={} CREATE (u)-[:BORROWED]->(b)".format(isbn))
		s.run("MATCH (b:Book) WHERE b.isbn={} SET b.available=False".format(isbn))
		print(uname + " checked out book " + isbn)
	else:
		print("Make sure all the args are correct.")

# =========== RETURN A BOOK
def returnBook(uname,isbn):
	bookBorrower = getBorrowerByBook(isbn,False)
	if bookBorrower != False:
		if borrowerExists(uname) and bookExists(isbn) and bookBorrower["u"]["uname"]==uname:
			s.run("MATCH (b:Book) WHERE b.isbn={} SET b.available=True".format(isbn))
			s.run("MATCH (b:Book) WHERE b.isbn={} MATCH (u:Borrower".format(isbn)+"{uname:"+'"'+uname+'"'+"}) MATCH (u)-[r:BORROWED]->(b) DETACH DELETE r")
			print(uname + " returned book " + isbn)
		else:
			print("Check your args. Either the user/book does not exist or the borrower does not have that book")

# =========== GET THE NUMBER OF BOOKS A BORROWER HAS
def getNumBooksByUser(uname,showResult=True):
	if not borrowerExists(uname):
		print("The borrower does not exist in the system")
		return

	r = s.run("MATCH (u:Borrower {uname:"+'"'+uname+'"'+"})-[:BORROWED]->(:Book) RETURN count(*) as c")
	if (showResult):
		for ugh in r:
			print(ugh["c"])
	for ugh in r:
		return ugh["c"]

# =========== PRINT THE BOOKS A BORROWER HAS
def printBooksByUser(uname):
	if not borrowerExists(uname):
		print("The borrower does not exist in the system")
		return

	r = s.run("MATCH (:Borrower {uname:"+'"'+uname+'"'+"})-[:BORROWED]->(b:Book) RETURN b")
	for c in r:
		printBook(isbn=c["b"]["isbn"], title=c["b"]["title"],numpgs=c["b"]["numpgs"],available=c["b"]["available"])
		print("------------------------------")


# =========== GET THE BORROWER OF A BOOK
def getBorrowerByBook(isbn,showResult=True):
	if not bookExists(isbn):
		print("The book does not exist in the system")
		return False
	elif bookAvailable(isbn):
		print("The book is not borrowed by anyone")
		return False
	r = s.run("MATCH (u:Borrower)-[:BORROWED]->(b:Book) WHERE b.isbn = {} RETURN u".format(isbn))
	if (showResult):
		for b in r:
			printBorrower(b["u"]["uname"],b["u"]["name"],b["u"]["phone"])
	for b in r:
		return b


# ======================================
#         R A T I N G S
# ======================================
# =========== RATE A BOOK
def rateBook(uname, isbn, stars, comment):
	if borrowerExists(uname) and bookExists(isbn):
		r = s.run("MATCH (u:Borrower) WHERE u.uname=" 
			+ '"' + uname + '"'
			+" MATCH (b:Book) WHERE b.isbn={} CREATE (u)-[r:RATED".format(isbn)
			+'{'+"stars:" + stars 
			+ ", comment:" + '"' + comment + '"' + '}' 
			+"]->(b) RETURN r")
		print(uname," rated book ", isbn)
	else:
		print("Check your args. The user or book does not exist")

# =========== PRINT OUT ALL OF THE RATINGS
def printRatings(uname=None,isbn=None):
	ratings = s.run("MATCH (u:Borrower)-[r:RATED]->(b:Book) RETURN r,b.title as t")
	for r in ratings:
		print("------------------------------")
		print("Title: ", r["t"])
		for i in range(0,r["r"]["stars"]):
			print("* ", end="",flush=True)
		print(" ~ ",r["r"]["comment"])

# =========== DELETE RATING(S)
def delRating(uname=None, isbn=None):
	if uname != None and isbn != None and borrowerExists(uname) and bookExists(isbn):
		s.run("MATCH (:Borrower {uname: " + uname + "})"+"-[r:RATED]->(:Book {isbn: {}})".format(isbn) + " DETACH DELETE r")
	elif uname!=None and borrowerExists(uname) and isbn==None:
		s.run("MATCH (:Borrower {uname: " + uname + "})"+"-[r:RATED]->(:Book)" + " DETACH DELETE r")
	elif uname==None and isbn!=None and bookExists(isbn):
		s.run("MATCH (:Borrower)"+"-[r:RATED]->(:Book {isbn: {}})".format(isbn) + " DETACH DELETE r")
	elif uname==None and isbn==None:
		s.run("MATCH (:Borrower)"+"-[r:RATED]->(:Book) DETACH DELETE r")