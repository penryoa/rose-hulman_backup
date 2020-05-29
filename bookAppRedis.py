import redis

conn = redis.Redis()

#def createConn(redCon):
#	conn = redCon

def bookExists(isbn):
	#return (conn.hgetall('{}{}'.format('i-',isbn))!={})
	return conn.sismember('set_isbn',isbn)

def addBook(isbn, title, author, numpages):
	if (bookExists(isbn)):
		print 'This book is already in the system'
		return
	conn.hset('{}{}'.format('i-',isbn), 'title', title)
	conn.hset('{}{}'.format('i-',isbn), 'author', author)
	conn.hset('{}{}'.format('i-',isbn), 'numpages', numpages)
	conn.hset('{}{}'.format('i-',isbn), 'borrower', '')
	#conn.rpush('list_isbn', isbn)
	conn.sadd('set_isbn',isbn)
	print 'Book', isbn, 'was added successfully.'
	return

def delBook(isbn):
	if not bookExists(isbn):
		print 'The book', isbn, 'is not in the system.' 
		return
	
	#while(True):
	#	popped = conn.lpop('list_isbn')
	#	if popped == isbn:
	#		break
	#	conn.rpush('list_isbn',popped)
	conn.srem('set_isbn',isbn)
	#conn.hdel('{}{}'.format('i-',isbn))
	print 'Order 66 was executed on', isbn, '.'
	return
	
def editBook(isbn, newTitle, newAuthor, newNumPages):
	if not bookExists(isbn):
		print 'The book', isbn, 'is not in the system.' 
		return
	print 'Previously:    ', searchBook(isbn)
	if (newTitle != ''):
		conn.hset('{}{}'.format('i-',isbn), 'title', newTitle)
	if (newAuthor != ''):
		conn.hset('{}{}'.format('i-',isbn), 'author', newAuthor)
	if (newNumPages != ''):
		conn.hset('{}{}'.format('i-',isbn), 'numpages', newNumPages)
	print 'Now:    ', searchBook(isbn)
	return

def searchBook(isbn):
	if not bookExists(isbn):
		print 'The book', isbn, 'is not in the system.' 
		return
	print conn.hgetall('{}{}'.format('i-',isbn))
	return

def sortBookBy(field):
	if field == 'title':
		return conn.sort('set_isbn',by='i-*->title',get='i-*->title',alpha=True)
	elif field == 'author':
		return conn.sort('set_isbn',by='i-*->author',get='i-*->author',alpha=True)
	elif field == 'isbn':
		return conn.sort('set_isbn')
	elif field == 'numPages':
		return conn.sort('set_isbn',by='i-*->numpages',get='i-*->numpages')
	print 'Boi, pass in title, author, isbn, or numPages'
	return

def printAllBooks():
	#for isbn in conn.lrange('set_isbn',0,-1):
	for isbn in list(conn.smembers('set_isbn')):
		print ' -------- isbn:', isbn, ' -------- '
		searchBook(isbn)

def borrowerExists(uname):
	#return (conn.hgetall('{}{}'.format('p-',uname))!={})
	return conn.sismember('set_ppl',uname)

def addBorrower(uname, name, phone):
	if (borrowerExists(uname)):
		print 'This borrower is already in the system'
		return
	conn.hset('{}{}'.format('p-',uname), 'name', name)
	conn.hset('{}{}'.format('p-',uname), 'phone', phone)
	#conn.rpush('list_ppl',uname)
	conn.sadd('set_ppl',uname)
	print 'Borrower', uname, 'was added successfully.'
	return

def delBorrower(uname):
	if not borrowerExists(uname):
		print 'This borrower is already in the system'
		return

	#while(True):
	#	popped = conn.lpop('list_ppl')
	#	if popped == isbn:
	#		break
	#	conn.rpush('list_ppl',popped)
	conn.srem('set_ppl',uname)
	#conn.hdel('{}{}'.format('p-',uname))
	print 'Order 66 was executed on', uname,'.'
	return
	
def editBorrower(uname, newName, newPhone):
	if not borrowerExists(uname):
		print 'This borrower is already in the system'
		return
	print 'Previously:    ', searchBorrower(uname)
	if (newName != ''):
		conn.hset('{}{}'.format('p-',uname), 'name', newName)
	if (newPhone != ''):
		conn.hset('{}{}'.format('p-',uname), 'phone', newPhone)
	print 'Now:    ', searchBorrower(uname)
	return

def searchBorrower(uname):
	if not borrowerExists(uname):
		print 'The borrower', uname, 'is not in the system.' 
		return
	print conn.hgetall('{}{}'.format('p-',uname))
	return 

def printAllBorrowers():
	#for p in conn.lrange('list_ppl',0,-1):
	for p in list(conn.smembers('set_ppl')):
		print ' -------- username:', p, ' -------- '
		searchBorrower(p)


def borrowBook(uname, isbn):
	if not borrowerExists(uname) or not bookExists(isbn):
		print 'make sure to get the right uname and isbn.'
		return
	#q = conn.hget('{}{}'.format('i-',isbn),'qty')
	#print 'qty is',q
	#if int(q)<1:
	if conn.hget('{}{}'.format('i-',isbn),'borrower')!='':
		print 'that book is unavailable my dude'
		return
	conn.sadd('{}{}'.format('b-',uname), isbn)
	conn.hset('{}{}'.format('i-',isbn),'borrower',uname)
	#conn.hset('{}{}'.format('i-',isbn), 'qty', qty-1)
	#conn.hincrby('{}{}'.format('i-',isbn),'qty',-1)
	print uname, 'is borrowing', isbn
	return

def returnBook(uname, isbn):
	if not borrowerExists(uname) or not bookExists(isbn):
		print 'make sure to get the right uname and isbn.'
		return
	if not conn.sismember('{}{}'.format('b-',uname), isbn):
		print 'the book was not borrowed by them'
		return
	#qty = conn.hgetkey('{}{}'.format('i-',isbn),'qty')
	#conn.hset('{}{}'.format('i-',isbn), 'qty', qty+1)
	#conn.hincrby('{}{}'.format('i-',isbn),'qty',1)
	conn.hset('{}{}'.format('i-',isbn),'borrower','')
	conn.srem('{}{}'.format('b-',uname), isbn)
	print uname, 'is returning', isbn
	return

def getNumBooksBorrowed(uname):
	print 'user', uname,'is borrowing',conn.scard('{}{}'.format('b-',uname)),'books.'
	return
	
def getBookBorrower(isbn):
	print 'book',isbn,'is borrowed by',conn.hget('{}{}'.format('i-',isbn),'borrower')
	return

