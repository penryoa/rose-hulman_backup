import pymongo
from pymongo import MongoClient

client = MongoClient()
db = client['bookApp']

# drops the current collections and rebuilds them
def initialize():
	db.books.drop()
	db.borrowers.drop()
	addBook("1","Crack is Quack",["Choose Goose"],92)
	addBook("4","The Musty Mallard",["Lusty Ducky","Quacksimodo"],100)
	addBook("2","Crawling through AirDucks",["Lithp Gooth"],21)
	addBook("3","Learning to not Give a Duck",["Edgy Bird"],53)
	addBorrower("Dr. Quack","drquack2000", 8413105373)
	addBorrower("Mallory","mally",2319421059)
	addBorrower("The Ugly Duckling","ug",1032502383)
	checkoutBook("drquack2000","1")
	checkoutBook("ug","3")


# ======================================
#         B O O K S
# ======================================
# =========== PRINT OUT ALL BOOKS
def printAllBooks(sortBy=None,asc=1):
	if sortBy==None:
		cursor = db.books.find()
	else:
		cursor = db.books.find().sort(sortBy,asc)
	
	for c in cursor:
		print("------------------------------")
		print c

# =========== RETURNS WHETHER OR NOT A BOOK EXISTS
def bookExists(isbn):
	return searchBook("isbn",isbn,False).count()!=0

# =========== ADD A BOOK
def addBook(isbn,title,authors,numpgs):
	if bookExists(isbn):
		print("The book already exists in the system")
		return

	db.books.insert_one({"isbn":isbn,
						"title":title,
						"authors":authors,
						"numpages":numpgs,
						"available":"True",
						"borrower":"None"
						})
	print("OK")

# =========== DELETE A BOOK
def delBook(isbn):
	if not bookExists(isbn):
		print("The book does not exist in the system")
		return
	c = db.books.find({"isbn":isbn},{"borrower":1})
	b="None"
	for d in c:
		b = d["borrower"]
	if b != "None":
		returnBook(b,isbn)
	db.books.remove({"isbn":isbn})
	print("OK")

# =========== EDIT A BOOK
def editBook(old_isbn, new_isbn, new_title, new_author,authorInfo, new_len):
	if not bookExists(old_isbn):
		print("The book does not exist in the system")
		return
	if new_title != "None":
		db.books.update_one({"isbn":old_isbn},{"$set": {"title":new_title}},False,False)
	if new_author != "None":
		db.books.update_one({"isbn":old_isbn},{"$addToSet": {"authors":new_author}}, False, False)
	if authorInfo != "None":
		db.books.update_one({"isbn":old_isbn},{"$pull": {"authors":authorInfo[0]}})
		db.books.update_one({"isbn":old_isbn},{"$addToSet": {"authors":authorInfo[1]}}, False,False)
	if new_len != "None":
		db.books.update_one({"isbn":old_isbn},{"$set": {"numpages":new_len}},False,False)
	if new_isbn != "None":
		db.books.update_one({"isbn":old_isbn},{"$set": {"isbn":new_isbn}},False,False)
	print("OK")

# =========== ADD A BOOK ATTRIBUTE
def addAttrBook(isbn,name,val):
	if not bookExists(isbn):
		print("The book does not exist in the system")
		return
	db.books.update_one({"isbn":isbn},{"$set": {name:val}})
	print("OK")

# =========== REMOVE A BOOK ATTRIBUTE
def remAttrBook(isbn, name):
	if not bookExists(isbn):
		print("The book does not exist in the system")
		return
	db.books.update_one({"isbn":isbn},{"$unset": {name:1}})
	print("OK")

# =========== SEARCH FOR A BOOK
def searchBook(val,term,showResult=True):
	c = db.books.find({val:term})
	if showResult:
		for d in c:
			print d
	return c


# ======================================
#         B O R R O W E R S
# ======================================
# =========== PRINT OUT ALL BORROWERS
def printAllBorrowers():
	cursor = db.borrowers.find()
	for c in cursor:
		print("------------------------------")
		print c

# =========== RETURNS WHETHER OR NOT A BORROWER EXISTS
def borrowerExists(uname):
	return searchBorrower("uname",uname,False).count()!=0

# =========== ADD A BORROWER
def addBorrower(name,uname,phone):
	if borrowerExists(uname):
		print("The borrower already exists in the system")
		return
	db.borrowers.insert_one({"name":name,
						"uname":uname,
						"phone":phone
						})
	print("OK")

# =========== DELETE A BORROWER
def delBorrower(uname):
	if not borrowerExists(uname):
		print("The borrower does not exist in the system")
		return
	c = db.borrowers.find({"uname":uname},{"books":1})
	books = []
	for d in c:
		books = d["books"] #just one book
	for b in books:
		returnBook(uname,b)
	db.borrowers.remove({"uname":uname})
	print("OK")

# =========== EDIT A BORROWER
def editBorrower(old_uname,new_uname,new_name,new_num):
	if not borrowerExists(old_uname):
		print("The borrower does not exist in the system")
		return
	if new_name != "None":
		db.borrowers.update_one({"uname":old_uname},{"$set": {"name":new_name}},False,False)
	if new_num != "None":
		db.borrowers.update_one({"uname":old_uname},{"$set": {"phone":new_num}},False,False)
	if new_uname != "None":
		db.borrowers.update_one({"uname":old_uname},{"$set": {"uname":new_uname}},False,False)
	print("OK")

# =========== SEARCH FOR A BORROWER
def searchBorrower(val,term,showResult=True):
	c = db.borrowers.find({val:term})
	if showResult:
		for d in c:
			print d
	return c

# ======================================
#         C H E C K O U T S
# ======================================
# =========== RETURNS WHETHER A BOOK IS BORROWED OR NOT
def bookAvailable(isbn):
	c = db.books.find({"isbn":isbn,"available":"True"})
	return c.count()>0

# =========== CHECK OUT A BOOK
def checkoutBook(uname, isbn):
	if borrowerExists(uname) and bookExists(isbn) and bookAvailable(isbn):
		db.books.update_one({"isbn":isbn},{"$set": {"available":"False"}},False,False)
		db.books.update_one({"isbn":isbn},{"$set": {"borrower":uname}},False,False)
		db.borrowers.update_one({"uname":uname},{"$addToSet": {"books":isbn}},False,False)
		print(uname + " checked out book " + isbn)

# =========== RETURN A BOOK
def returnBook(uname,isbn):
	bookBorrower = getBorrowerByBook(isbn,False)
	if borrowerExists(uname) and bookExists(isbn) and getBorrowerByBook(isbn,False)==uname:
		db.books.update_one({"isbn":isbn},{"$set": {"available":"True"}},False,False)
		db.books.update_one({"isbn":isbn},{"$set": {"borrower":"None"}},False,False)
		db.borrowers.update_one({"uname":uname},{"$pull": {"books":isbn}},False,False)
		print(uname + " returned book " + isbn)
	else:
		print("Check your args. Either the user/book does not exist or the borrower does not have that book")

# =========== GET THE NUMBER OF BOOKS A BORROWER HAS
def getNumBooksByUser(uname,showResult=True):
	if not borrowerExists(uname):
		print("The borrower does not exist in the system")
		return
	c = db.borrowers.find({"uname":uname},{"books":1})
	total = 0
	for d in c:
		total = len(d["books"]) #just one book
	if showResult:
		print total
	return total

# =========== GET THE BORROWER OF A BOOK
def getBorrowerByBook(isbn,showResult=True):
	if not bookExists(isbn):
		print("The book does not exist in the system")
		return
	c = db.books.find({"isbn":isbn},{"borrower":1})
	temp = ""
	for d in c:
		temp = d["borrower"] # just one borrower
	if showResult:
		print temp
	return temp
