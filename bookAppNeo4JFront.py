import cmd
import sys
import string
from bookAppNeo4JBack import *

# NOTE: To run, type "python3 bookAppMongoFront.py" in the commandline.

class bookApp(cmd.Cmd):
	intro ="        ^        \n"
	intro+="       / "+ '\\' + "      \n"
	intro+="      /   "+ '\\' + "      \n"
	intro+="     / _ _ "+ '\\' + "      \n"
	intro+="    |_*_*_*_|    \n"
	intro+="  /___________"+ '\\' + "      \n\n"
	intro+="Welcome to the Wizard Lizard Library, the most esteemed book collection.\nI'm Beardy the bearded dragon. What do you need?\n(Type help or ? to list commands).\n"
	prompt = '\n---|o *~* '
	file = None

	def do_initialize_data(self,arg):
		'sets up basic data'
		initialize()

	# ======================================
	#         A U T H O R S
	# ======================================
	# =========== PRINT AUTHORS
	# def do_print_all_authors(self,arg):
	# 	'Print out all authors currently in the library. Args: none'
	# 	printAllAuthors()

	# ======================================
	#         B O O K S
	# ======================================
	# =========== PRINT BOOKS
	def do_print_all_books(self,arg):
		'Print out all books currently in the library. Args: none'
		printAllBooks()

	# =========== ADD A BOOK
	def do_add_a_book(self,arg):
		'Add a book. Args: none'
		# Get ISBN
		isbn = input("Enter in ISBN: ")
		# Get title
		title = input("Enter in title: ")
		# Get authors
		print("Enter in authors (enter 1 when you are done, must include at least one author): ")
		authors = []
		inp = input(">> ")
		authors.append(inp)
		while (inp != "1"):
			inp = input(">> ")
			authors.append(inp)
		authors = authors[:len(authors)-1]
		# Get length
		numpgs = input("Enter in book length: ")
		addBook(isbn,title,authors,numpgs)

	# =========== DELETE A BOOK
	def do_delete_a_book(self,arg):
		'Delete a book. Args: isbn'
		args = parse(arg)
		if (len(args) != 1):
			temp = input("isbn: ")
			delBook(int(temp))
		else:
			delBook(int(args[0]))

	# =========== EDIT A BOOK
	def do_edit_a_book(self,arg):
		'Edit book information. Args: isbn'
		args = parse(arg)
		if (len(args) != 1):
			old_isbn = int(input("isbn: "))
		else:
			old_isbn = arg
		# edit isbn?
		print("Would you like to change the isbn? (y/n)")
		if get_yn():
			new_isbn = input("Enter the new isbn: ")
		else:
			new_isbn = "None"
		# edit title?
		print("Would you like to change the title? (y/n)")
		if get_yn():
			new_title = input("Enter the new title: ")
		else:
			new_title = "None"
		# add author?
		print("Would you like to add an author? (y/n)")
		if get_yn():
			addedAuthor = input("Enter the new author: ")
		else:
			addedAuthor = "None"
		# edit author? weird bc there can be multiple authors
		print("Would you like to change an author? (y/n)")
		if get_yn():
			authorInfo = []
			inp = input("    Enter the old author: ")
			authorInfo.append(inp)
			inp = input("    Enter the new author: ")
			authorInfo.append(inp)
		else:
			authorInfo = "None"
		# edit book length?
		print("Would you like to change the book length? (y/n)")
		if get_yn():
			new_len = input("Enter the new length: ")
		else:
			new_len = "None"
		editBook(old_isbn, new_isbn, new_title, addedAuthor, authorInfo, new_len)

	# =========== SEARCH FOR A BOOK
	def do_search_for_book(self,arg):
		'Search for a book by isbn, title, author. Args: none'
		print("What do you want to search by? (isbn, title, author)")
		val = ""
		while val not in ('isbn','title','author'):
			val = input()
		term = input("Your search term: ")
		searchBook(val,term)


	# =========== SORT BOOKS
	def do_sort_books(self,arg):
		'Sort books by isbn, title, author, or number of pages. Args: (isbn, title, author, or length)'
		t = ""
		while t not in ('isbn','title','author','length'):
			t = input("Sort by isbn, title, author, or length?: ")
		if t=="length":
			t = "numpgs"
		a = ""
		while a not in ("a","d"):
			a = input("Ascending or descending (a/d)?: ")
		if a=="a":
			printAllBooks(sortBy=t,asc="ASC")
		else:
			printAllBooks(sortBy=t,asc="DESC")


	# ======================================
	#         B O R R O W E R S
	# ======================================
	# =========== PRINT BORROWERS
	def do_print_all_borrowers(self,arg):
		'Print out all borrowers currently in the library. Args: none'
		printAllBorrowers()

	# =========== ADD A BORROWER
	def do_add_a_borrower(self,arg):
		'Add a borrower. Args: none'
		name = input("Name: ")
		uname = input("Username (all chars): ")
		num = input("Phone number (no spaces or dashes): ")
		addBorrower(name,uname,num)

	# =========== DELETE A BORROWER
	def do_delete_a_borrower(self,arg):
		'Delete a borrower. Args: username'
		if (len(arg.split()) != 1):
			temp = input("username: ")
			delBorrower(temp)
			# argsError()
		else:
			delBorrower(arg)

	# =========== EDIT A BOOK
	def do_edit_a_borrower(self,arg):
		'Edit book information. Args: uname'
		args = parse(arg)
		if (len(args) != 1):
			old_uname = input("username: ")
			# argsError()
		else:
			old_uname = arg
		
		# edit uname?
		print("Would you like to change the username? (y/n)")
		if get_yn():
			new_uname = input("Enter the new username: ")
		else:
			new_uname = "None"
		# edit name?
		print("Would you like to change the name? (y/n)")
		if get_yn():
			new_name = input("Enter the new name: ")
		else:
			new_name = "None"
		# edit phone num?
		print("Would you like to change the phone number? (y/n)")
		if get_yn():
			new_num = input("Enter the new phone number: ")
		else:
			new_num = "None"
		editBorrower(old_uname,new_uname,new_name,new_num)

	# ======== SEARCH FOR A BORROWER
	def do_search_for_borrower(self,arg):
		'Search for a borrower by username or name. Args: none'
		print("What do you want to search by (name or username)?")
		val = ""
		while val not in ('name','username'):
			val = input()
		if val=="username":
			val="uname"
		term = input("Your search term: ")
		searchBorrower(val,term)

	# ======================================
	#         C H E C K O U T S
	# ======================================
	def do_checkout_book(self,arg):
		'Have a borrower borrow a book. Args: username,isbn'
		args = parse(arg)
		if len(args)!=2:
			argsError()
		else:
			checkoutBook(args[0],args[1])

	def do_return_book(self,arg):
		'Have a borrower return a book. Args: username,isbn'
		args = parse(arg)
		if len(args)!=2:
			argsError()
		else:
			returnBook(args[0],args[1])

	def do_get_num_books_by_username(self,arg):
		'Gets the number of books a user has. Args: username'
		args = parse(arg)
		if len(args)!=1:
			argsError()
		else:
			getNumBooksByUser(args[0])

	def do_get_books_by_username(self,arg):
		'Gets the books a user has. Args: username'
		args = parse(arg)
		if len(args)!=1:
			argsError()
		else:
			printBooksByUser(args[0])

	def do_get_borrower_by_book(self,arg):
		'Gets the borrower of the book. Args: isbn'
		args = parse(arg)
		if len(args)!=1:
			argsError()
		else:
			getBorrowerByBook(args[0])

	# ======================================
	#          R A T I N G
	# ======================================
	def do_rate_book(self,arg):
		'Create a rating for a book. Args: none'
		username = input("username: ")
		isbn = input("book isbn: ")
		val = ""
		while val not in ("1","2","3","4","5"):
			val = input("How epic is the book? (enter 1-5): ")
		comment = input("Why did you rate it as you did? ")
		rateBook(username, int(isbn), val, comment)

	def do_get_ratings(self,arg):
		'Print out all of the ratings. Args: none'
		printRatings()

	# ======== EXIT THE APP
	def do_leave_the_library(self,arg):
		'Exit the library app'
		close_session()
		print("Don't let the tail hit you on the way out.")
		return True


def argsError():
	print("Check the command usage; incorrect args")

def parse(arg):
	return arg.split()

def get_yn():
	inp = ''
	while inp not in ('y','n'):
		inp = input()
	return inp=='y'

# call main to start it
if __name__ == '__main__':
	bookApp().cmdloop()