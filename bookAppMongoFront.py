import cmd
import sys
import string
from bookAppMongoBack import *

# NOTE: To run, type "python bookAppMongoFront.py" in the commandline.

class bookApp(cmd.Cmd):
	
	intro = 'Welcome to the Quacks Library my fellow duck.\nWaddle you be needing today?\n(Type help or ? to list commands).\n'
	prompt = '--> '
	file = None

	def do_initialize_data(self,arg):
		'sets up basic data'
		initialize()

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
		# args=[]
		# Get ISBN
		isbn = raw_input("Enter in ISBN: ")
		# args.append(inp)
		# Get title
		title = raw_input("Enter in title: ")
		# args.append(inp)
		# Get authors
		print("Enter in authors (enter 1 when you are done, must include at least one author): ")
		authors = []
		inp = raw_input(">> ")
		authors.append(inp)
		while (inp != "1"):
			inp = raw_input(">> ")
			authors.append(inp)
		authors = authors[:len(authors)-1]
		# args.append(authors[:len(authors)-1])
		# Get length
		numpgs = input("Enter in book length: ")
		# args.append(inp)
		addBook(isbn,title,authors,numpgs)

	# =========== DELETE A BOOK
	def do_delete_a_book(self,arg):
		'Delete a book. Args: isbn'
		args = parse(arg)
		if (len(args) != 1):
			argsError()
		else:
			delBook(args[0])

	# =========== EDIT A BOOK
	def do_edit_a_book(self,arg):
		'Edit book information. Args: isbn'
		args = parse(arg)
		if (len(args) != 1):
			argsError()
		else:
			old_isbn = arg
			# edit isbn?
			print("Would you like to change the isbn? (y/n)")
			if get_yn():
				new_isbn = input("Enter the new isbn: ")
				# args.append(inp)
			else:
				new_isbn = "None"
				# args.append("None")
			# edit title?
			print("Would you like to change the title? (y/n)")
			if get_yn():
				new_title = raw_input("Enter the new title: ")
				# args.append(inp)
			else:
				new_title = "None"
				# args.append("None")
			# add author?
			print("Would you like to add an author? (y/n)")
			if get_yn():
				addedAuthor = raw_input("Enter the new author: ")
			else:
				addedAuthor = "None"
			# edit author? weird bc there can be multiple authors
			print("Would you like to change an author? (y/n)")
			if get_yn():
				authorInfo = []
				inp = raw_input("    Enter the old author: ")
				authorInfo.append(inp)
				inp = raw_input("    Enter the new author: ")
				authorInfo.append(inp)
				# args.append(authorInfo)
			else:
				authorInfo = "None"
				# args.append("None")
			# edit book length?
			print("Would you like to change the book length? (y/n)")
			if get_yn():
				new_len = input("Enter the new length: ")
				# args.append(str(inp))
			else:
				new_len = "None"
				# args.append("None")
			editBook(old_isbn, new_isbn, new_title, addedAuthor, authorInfo, new_len)
	
	# =========== ADD ATTRIBUTES
	def do_add_attribute_to_book(self,arg):
		'Adds an attribute to a book. Args: isbn'
		args = parse(arg)
		if (len(args) != 1):
			argsError()
		else:
			name = raw_input("  Name of attribute: ")
			val = raw_input("  Value of attribute: ")
			addAttrBook(arg,name,val)

	# =========== REMOVE ATTRIBUTE
	def do_remove_attribute(self,arg):
		'Remove specific attributes from a book. Args: isbn'
		# args = parse(arg)
		if (len(arg.split()) < 1):
			argsError()
		else:
			name = raw_input("  Name of attribute: ")
			remAttrBook(arg,name)

	# =========== SEARCH FOR A BOOK
	def do_search_for_book(self,arg):
		'Search for a book by isbn, title, author. Args: none'
		print("What do you want to search by? (isbn, title, author)")
		val = ""
		while val not in ('isbn','title','author'):
			val = raw_input()
		term = raw_input("Your search term: ")
		searchBook(val,term)


	# =========== SORT BOOKS
	def do_sort_books(self,arg):
		'Sort books by isbn, title, author, or number of pages. Args: (isbn, title, author, or length)'
		# print("What do you want to sort by? (isbn, title, author,length)")
		args = parse(arg)
		if (len(args) != 1):
			argsError()
		else:
			t = args[0]
			while t not in ('isbn','title','author','length'):
				t = raw_input("Please enter in isbn, title, author, or length: ")
			a = ""
			while a not in ("a","d"):
				a = raw_input("Ascending or descending (a/d)?: ")
			if a=="a":
				printAllBooks(sortBy=t,asc=1)
			else:
				printAllBooks(sortBy=t,asc=-1)


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
		name = raw_input("Name: ")
		uname = raw_input("Username (all chars): ")
		num = input("Phone number (no spaces or dashes): ")
		addBorrower(name,uname,num)

	# =========== DELETE A BORROWER
	def do_delete_a_borrower(self,arg):
		'Delete a borrower. Args: username'
		if (len(arg.split()) != 1):
			argsError()
		else:
			delBorrower(arg)

	# =========== EDIT A BOOK
	def do_edit_a_borrower(self,arg):
		'Edit book information. Args: uname'
		if (len(arg.split()) != 1):
			argsError()
		else:
			old_uname = arg
			# edit uname?
			print("Would you like to change the username? (y/n)")
			if get_yn():
				new_uname = raw_input("Enter the new username: ")
				# args.append(inp)
			else:
				new_uname = "None"
				# args.append("None")
			# edit name?
			print("Would you like to change the name? (y/n)")
			if get_yn():
				new_name = raw_input("Enter the new name: ")
				# args.append(inp)
			else:
				new_name = "None"
				# args.append("None")
			# edit phone num?
			print("Would you like to change the phone number? (y/n)")
			if get_yn():
				new_num = input("Enter the new phone number: ")
				# args.append(inp)
			else:
				new_num = "None"
				# args.append("None")
			editBorrower(old_uname,new_uname,new_name,new_num)

	# ======== SEARCH FOR A BORROWER
	def do_search_for_borrower(self,arg):
		'Search for a borrower by username or name. Args: none'
		print("What do you want to search by (name or username)?")
		val = ""
		while val not in ('name','username'):
			val = raw_input()
		if val=="username":
			val="uname"
		term = raw_input("Your search term: ")
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

	def do_get_borrower_by_book(self,arg):
		'Gets the borrower of the book. Args: isbn'
		args = parse(arg)
		if len(args)!=1:
			argsError()
		else:
			getBorrowerByBook(args[0])

	# ======== EXIT THE APP
	def do_leave_the_library(self,arg):
		'Exit the library app'
		print("Thanks! Quack you later.")
		return True


def argsError():
	print("Check the command usage; incorrect args")

def parse(arg):
	return arg.split()

def get_yn():
	inp = ''
	while inp not in ('y','n'):
		inp = raw_input()
	return inp=='y'

# call main to start it
if __name__ == '__main__':
	bookApp().cmdloop()