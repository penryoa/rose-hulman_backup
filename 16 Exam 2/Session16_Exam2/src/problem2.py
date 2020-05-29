"""
This module contains:
  -- Methods you must implement for the SmartPhone object

Authors: David Mutchler, Dave Fisher, Matt Boutell, their colleagues,
         and Olivia Penry.  Oct 2017.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.


import time
import sys
from numbers import Number


def main():
    """ Calls the   TEST   functions in this module. """

    ####################################################################
    # STUDENTS:  UN-comment these tests as you work the problems.
    ####################################################################
    print('Un-comment the calls in MAIN one by one')
    print(' to run the testing code as you complete the TODOs.')

    run_test_init()
    run_test_call()
    run_test_get_num_calls_to_owner()
    run_test_reset_owner()
    run_test_get_total_calls_to_favorite()
    run_test_get_num_times_called()


########################################################################
# The   SmartPhone   class (and its methods) begins here.
########################################################################

class SmartPhone(object):
    """
    A SmartPhone has:
        -- owner, a string
        -- owners_called, a list of strings (owners of SmartPhones called)
    """

    def __init__(self, owner):
        """
        What comes in: owner, a string for the owner of this SmartPhone
        What goes out: Nothing (i.e., None).
        Side effects:
          -- Stores the SmartPhone's owner in an instance variable:
                  self.owner
          -- Initializes an empty list:
                  self.owners_called
             that will store owners of SmartPhones that this SmartPhone calls.
          -- Initializes other instance variables as needed by methods
        Examples:
          phone1 = SmartPhone('Dave)
            #   phone1.owner           is now 'Dave'
            #   phone1.owners_called   is now []

        Type hints:
          :type owner: string
        """
        # --------------------------------------------------------------
        # DONE: 2. Implement and test this function.
        #     See the testing code (below) for more examples.
        # --------------------------------------------------------------

        self.owner = owner
        self.owners_called = []
        self.original_owner = owner
        self.call_count = 0


    def call(self, other_smart_phone):
        """
        What comes in:
          -- self
          -- other_smart_phone, which is another SmartPhone
        What goes out:
          -- Nothing (i.e. None)

        Side effects:  Adds the owner of the given  other_smart_phone
             to the list of  owners_called  for this SmartPhone.

        Examples:
          phone1 = SmartPhone('Dave')
          phone2 = SmartPhone('Matt')
          phone1.call(phone2)
            #   phone1.owner          is still 'Dave'
            #   phone1.owners_called  is now ['Matt']
            #   phone2.owner          is still 'Matt'
            #   phone2.owners_called  is still []

          phone1 = SmartPhone('Dave')
          phone2 = SmartPhone('Matt')
          phone3 = SmartPhone('Jim')
          phone1.call(phone2)
          phone1.call(phone3)
          phone1.call(phone2)
          phone1.call(phone2)
            #   phone1.owner          is still 'Dave'
            #   phone1.owners_called  is now ['Matt', 'Jim', 'Matt', 'Matt']

        Type hints:
          :type  other_smart_phone: SmartPhone
        """
        # --------------------------------------------------------------
        # DONE: 3. Implement and test this function.
        #     See the testing code (below) for more examples.
        # --------------------------------------------------------------

        self.owners_called = self.owners_called + [other_smart_phone.owner]
        other_smart_phone.call_count = other_smart_phone.call_count + 1






    def get_num_calls_to_owner(self, owner_called):
        """
        What comes in:
          -- self
          -- owner, a string for the owner of interest
        What goes out:
          Returns the number of calls that have been made
          to the given owner called.
        Side effects:
          -- None
        Examples:
          phone1 = SmartPhone('Dave')
          phone2 = SmartPhone('Matt')
          phone1.call(phone2)
          num_calls_to_matt = phone1.get_num_calls_to_owner('Matt')
            #   num_calls_to_matt is 1

          phone1 = SmartPhone('Dave')
          phone2 = SmartPhone('Matt')
          phone3 = SmartPhone('Jim')
          phone1.call(phone2)
          phone1.call(phone3)
          phone1.call(phone2)
          phone1.call(phone2)
          num_calls_to_matt = phone1.get_num_calls_to_owner('Matt')
          num_calls_to_jim = phone1.get_num_calls_to_owner('Jim')
          num_calls_to_bob = phone1.get_num_calls_to_owner('Bob')
            #   num_calls_to_matt is 3
            #   num_calls_to_jim is 1
            #   num_calls_to_bob is 0

        Type hints:
          :type owner_called: string
          :rtype: int
        """
        # --------------------------------------------------------------
        # DONE: 4. Implement and test this function.
        #     See the testing code (below) for more examples.
        # --------------------------------------------------------------
        count = 0
        for k in range(len(self.owners_called)):
            if self.owners_called[k] == owner_called:
                count = count + 1

        return count



    def reset_owner(self):
        """
        What comes in:
          -- self
        What goes out: Nothing (i.e., None).
        Side effects:
          -- Resets this SmartPhone's owner to its original owner
        Examples:
          phone1 = SmartPhone('Dave')
            #   phone1.owner   is now 'Dave'
          phone1.owner = 'Bob'
            #   phone1.owner   is now 'Bob'
          phone1.reset_owner()
            #   phone1.owner   is back to 'Dave'

          phone1 = SmartPhone('Dave')
          phone2 = SmartPhone('Matt')
          phone1.owner = 'Bob'
          phone2.owner = 'Jim'
          phone1.call(phone2)
            #   phone1.owner          is now 'Bob'
            #   phone1.owners_called  is now ['Jim']
            #   phone2.owner          is now 'Jim'
          phone2.reset_owner()
          phone1.call(phone2)
            #   phone1.owner          is still 'Bob'
            #   phone1.owners_called  is now ['Jim', 'Matt']
            #   phone2.owner          is now 'Matt'
        """
        # --------------------------------------------------------------
        # DONE: 5. Implement and test this function.
        #     See the testing code (below) for more examples.
        # --------------------------------------------------------------

        self.owner = self.original_owner





    def get_num_calls_to_favorite(self):
        #######################################################################
        # IMPORTANT: Read the note below!
        #######################################################################
        """
        A SmartPhone's "favorite" is (by definition) the owner in its
        owners_called  list that this SmartPhone has called most often.
          -- For example, if a SmartPhone's  owners_called  list is:
               ['Dave', 'Matt', 'Dave', 'Jim', 'Matt', 'Dave', 'Dave', 'Bob']
            then its favorite is 'Dave', whom it has called 4 times.

        This method returns the number of times that this SmartPhone
        has called its favorite.
          -- For example, if a SmartPhone's  owners_called  list is:
               ['Dave', 'Matt', 'Dave', 'Jim', 'Matt', 'Dave', 'Dave', 'Bob']
            then this method returns  4  for that SmartPhone.
        """
        #######################################################################
        # IMPORTANT:
        #   -- This method  (get_num_calls_to_favorite) is ALREADY IMPLEMENTED.
        #   -- READ its green specification, but do NOT modify it.
        #      It has no TO DO.
        #   -- You must CALL this   get_num_calls_to_favorite  method
        #        as part of your solution to TO DO 8 below.
        #   -- If you do not understand the green specification above,
        #        ASK YOUR INSTRUCTOR to explain it to you.
        #   -- You do NOT need to know ANYTHING about the code below.
        #        The code below s PURPOSEFULLY obscure.
        #######################################################################
        a = {}
        for o in self.owners_called:
            if o in a:
                a[o] += 1
            else:
                a[o] = 1
        return 0 if len(a.values()) == 0 else max(a.values())
        #######################################################################
        # IMPORTANT: Read the note above!
        #######################################################################

    def get_total_calls_to_favorite(self, other_smart_phone):
        #######################################################################
        # IMPORTANT: Read the note below (in PINK)
        #            BEFORE attempting this problem!
        #######################################################################
        """
        What comes in:
          -- self
          -- other_smart_phone, which is another SmartPhone
        What goes out:
          A SmartPhone's "favorite" is (by definition) the owner of the
          SmartPhone that this SmartPhone has called most often.
            -- For example, if a SmartPhone's  owners_called  list is:
                 ['Dave', 'Matt', 'Dave', 'Jim', 'Matt', 'Dave', 'Dave', 'Bob']
               then its favorite is 'Dave', whom it has called 4 times.

          This method returns the SUM of the number of calls that
          THIS SmartPhone has made to ITS favorite and the number of calls
          that the given  other_smart_phone  has made to ITS favorite.

            -- For example, if this SmartPhone's  owners_called  list is:
                 ['Dave', 'Matt', 'Dave', 'Jim', 'Matt', 'Dave', 'Dave', 'Bob']
               and  other_smart_phone's  owners_list  is:
                 ['Bob']
               then:
                 -- This SmartPhone's favorite is 'Dave',
                      whom it has called 4 times.
                 -- The other_smart_phone's favorite is 'Bob',
                      whom it has called 1 time.
               and so this function returns 4 + 1, that is, 5.

        Side effects:
          -- None
        Examples:
            phone1 = SmartPhone('Dave')
            phone2 = SmartPhone('Matt')
            phone3 = SmartPhone('Jim')

            # At this point, phones 1, 2 and 3 have made 0, 0, and 0 calls
            # to their favorites, respectively.  Hence the values of the
            # following 6 variables should all be 0 at this point:

            total_1_2 = phone1.get_total_calls_to_favorite(phone2)
            total_1_3 = phone1.get_total_calls_to_favorite(phone3)

            total_2_1 = phone2.get_total_calls_to_favorite(phone1)
            total_2_3 = phone2.get_total_calls_to_favorite(phone3)

            total_3_1 = phone3.get_total_calls_to_favorite(phone1)
            total_3_2 = phone3.get_total_calls_to_favorite(phone2)

            actual = (total_1_2, total_1_3, total_2_1, total_2_3,
                      total_3_1, total_3_2)
            print(actual)  # Should print (0, 0, 0, 0, 0, 0)

            # Continuing the example, after phone3 calls phone2:
            phone3.call(phone2)

            # Now phone1 and phone2 have still made 0 calls to their respective
            # favorites, but phone3 has made 1 call to its favorite.
            # Hence now:
            #   phone1.get_total_calls_to_favorite(phone2)  is now 0 + 0 = 0
            #   phone1.get_total_calls_to_favorite(phone3)  is now 0 + 1 = 1
            #
            #   phone2.get_total_calls_to_favorite(phone1)  is now 0 + 0 = 0
            #   phone2.get_total_calls_to_favorite(phone3)  is now 0 + 1 = 1
            #
            #   phone3.get_total_calls_to_favorite(phone1)  is now 1 + 0 = 1
            #   phone3.get_total_calls_to_favorite(phone2)  is now 1 + 0 = 1

            total_1_2 = phone1.get_total_calls_to_favorite(phone2)
            total_1_3 = phone1.get_total_calls_to_favorite(phone3)

            total_2_1 = phone2.get_total_calls_to_favorite(phone1)
            total_2_3 = phone2.get_total_calls_to_favorite(phone3)

            total_3_1 = phone3.get_total_calls_to_favorite(phone1)
            total_3_2 = phone3.get_total_calls_to_favorite(phone2)

            actual = (total_1_2, total_1_3, total_2_1, total_2_3,
                      total_3_1, total_3_2)
            print(actual)  # Should print (0, 1, 0, 1, 1, 1)

            # Continuing the example, after still more phone calls:
            phone1.call(phone2)
            phone1.call(phone2)
            phone1.call(phone3)
            phone1.call(phone3)
            phone1.call(phone2)

            # At this point, phones 1, 2 and 3 have made 3, 0, and 1 calls
            # to their favorites, respectively.
            # Hence now:
            #   phone1.get_total_calls_to_favorite(phone2)  is now 3 + 0 = 3
            #   phone1.get_total_calls_to_favorite(phone3)  is now 3 + 1 = 4
            #
            #   phone2.get_total_calls_to_favorite(phone1)  is now 0 + 3 = 3
            #   phone2.get_total_calls_to_favorite(phone3)  is now 0 + 1 = 1
            #
            #   phone3.get_total_calls_to_favorite(phone1)  is now 1 + 3 = 4
            #   phone3.get_total_calls_to_favorite(phone2)  is now 1 + 0 = 1

            total_1_2 = phone1.get_total_calls_to_favorite(phone2)
            total_1_3 = phone1.get_total_calls_to_favorite(phone3)

            total_2_1 = phone2.get_total_calls_to_favorite(phone1)
            total_2_3 = phone2.get_total_calls_to_favorite(phone3)

            total_3_1 = phone3.get_total_calls_to_favorite(phone1)
            total_3_2 = phone3.get_total_calls_to_favorite(phone2)

            actual = (total_1_2, total_1_3, total_2_1, total_2_3,
                      total_3_1, total_3_2)
            print(actual)  # Should print (3, 4, 3, 1, 4, 1)

        Type hints:
          :type  other_smart_phone: SmartPhone
          :rtype: int
        """
        # --------------------------------------------------------------
        # DONE: 6. Implement and test this function.
        #     See the testing code (below) for more examples.
        ################################################################
        # IMPORTANT:
        #   -- Do NOT attempt to write code that determines which owner
        #        is a SmartPhone's favorite.
        #   -- Do NOT attempt to write code that computes the number
        #        of calls that a SmartPhone has made to its favorite.
        #   -- Instead, ** CALL ** the
        #         get_num_calls_to_favorite
        #      method that we supplied (above), with appropriate argument(s).
        ################################################################

        sum = self.get_num_calls_to_favorite() + other_smart_phone.get_num_calls_to_favorite()

        return sum







    def get_num_times_called(self):
        """
        What comes in:
          -- self
        What goes out:
          Returns the number of calls that have been made TO this SmartPhone.
        Side effects:
          -- None
        Examples:
          phone1 = SmartPhone('Dave')
          phone2 = SmartPhone('Matt')
          phone1.call(phone2)
          print(phone1.get_num_times_called())  # Prints 0
          print(phone2.get_num_times_called())  # Prints 1

          phone1 = SmartPhone('Dave')
          phone2 = SmartPhone('Matt')
          phone3 = SmartPhone('Jim')
          phone1.call(phone2)
          phone1.call(phone3)
          phone1.call(phone2)
          phone1.call(phone2)
          print(phone1.get_num_times_called())  # Prints 0
          print(phone2.get_num_times_called())  # Prints 3
          print(phone3.get_num_times_called())  # Prints 1

          phone3.call(phone1)
          print(phone1.get_num_times_called())  # Prints 1
          print(phone2.get_num_times_called())  # Prints 3
          print(phone3.get_num_times_called())  # Prints 1

        Type hints:
          :rtype: int
        """
        # --------------------------------------------------------------
        # DONE: 7. Implement and test this function.
        #     See the testing code (below) for more examples.
        # --------------------------------------------------------------

        return self.call_count








########################################################################
# The TEST functions for the  SmartPhone  class begin here.
########################################################################


# ----------------------------------------------------------------------
# run_test_init:
# ----------------------------------------------------------------------
def run_test_init():
    """ Tests the   __init__   method of the SmartPhone class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   __init__   method of the SmartPhone class.')
    print('-----------------------------------------------------------')

    # Test 1: Constructing one smart_phone:
    print('\nTest 1:')

    smart_phone = SmartPhone('Dave')

    expected_owner = 'Dave'
    expected_owners_called = []

    test_instance_variables(
        smart_phone, expected_owner, expected_owners_called)

    # Test 2: Constructing another smart_phone:
    print('\nTest 2:')

    smart_phone = SmartPhone('Matt')

    expected_owner = 'Matt'
    expected_owners_called = []

    test_instance_variables(
        smart_phone, expected_owner, expected_owners_called)


# ----------------------------------------------------------------------
# run_test_call:
# ----------------------------------------------------------------------
def run_test_call():
    """ Tests the   call   method of the SmartPhone class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   call   method of the SmartPhone class.')
    print('-----------------------------------------------------------')

    # Test 1:
    print('\nTest 1:')

    phone1 = SmartPhone('Dave')
    phone2 = SmartPhone('Matt')
    phone1.call(phone2)
    #   phone1.owner          is still 'Dave'
    #   phone1.owners_called  is now ['Matt']
    #   phone2.owner          is still 'Matt'
    #   phone2.owners_called  is still []
    test_instance_variables(phone1, 'Dave', ['Matt'])

    print('\nTest 1 (continued):')
    test_instance_variables(phone2, 'Matt', [])

    # Test 2:
    print('\nTest 2:')

    phone1 = SmartPhone('Dave')
    phone2 = SmartPhone('Matt')
    phone3 = SmartPhone('Jim')
    phone1.call(phone2)
    phone1.call(phone3)
    phone1.call(phone2)
    phone1.call(phone2)
    test_instance_variables(
        phone1, 'Dave', ['Matt', 'Jim', 'Matt', 'Matt'])

    print('\nTest 2 (continued):')
    test_instance_variables(phone2, 'Matt', [])

    print('\nTest 2 (continued):')
    test_instance_variables(phone3, 'Jim', [])

    # Test 3:
    print('\nTest 3:')

    phone1 = SmartPhone('Dave')
    phone2 = SmartPhone('Matt')
    phone3 = SmartPhone('Jim')
    phone1.call(phone2)
    phone2.call(phone3)
    phone3.call(phone1)
    phone3.call(phone2)
    phone2.call(phone1)
    phone1.call(phone3)
    test_instance_variables(phone1, 'Dave', ['Matt', 'Jim'])

    print('\nTest 3 (continued):')
    test_instance_variables(phone2, 'Matt', ['Jim', 'Dave'])

    print('\nTest 3 (continued):')
    test_instance_variables(phone3, 'Jim', ['Dave', 'Matt'])


def run_test_get_num_calls_to_owner():
    """ Tests the  get_num_calls_to_owner  method of the SmartPhone class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the  get_num_calls_to_owner  method of SmartPhone class.')
    print('-----------------------------------------------------------')

    # Test 1:
    print('\nTest 1:')

    phone1 = SmartPhone('Dave')
    phone2 = SmartPhone('Matt')
    phone1.call(phone2)

    expected = 1
    actual = phone1.get_num_calls_to_owner('Matt')
    print()
    print('Expected from get_num_calls_to_owner():', expected)
    print('Actual   from get_num_calls_to_owner():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    expected = 0
    actual = phone1.get_num_calls_to_owner('Jim')
    print()
    print('Expected from get_num_calls_to_owner():', expected)
    print('Actual   from get_num_calls_to_owner():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 2:
    print('\nTest 2:')

    phone1 = SmartPhone('Dave')
    phone2 = SmartPhone('Matt')
    phone3 = SmartPhone('Jim')
    phone1.call(phone2)
    phone1.call(phone3)
    phone1.call(phone2)
    phone1.call(phone2)

    expected = 3
    actual = phone1.get_num_calls_to_owner('Matt')
    print()
    print('Expected from get_num_calls_to_owner():', expected)
    print('Actual   from get_num_calls_to_owner():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    expected = 1
    actual = phone1.get_num_calls_to_owner('Jim')
    print()
    print('Expected from get_num_calls_to_owner():', expected)
    print('Actual   from get_num_calls_to_owner():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    expected = 0
    actual = phone3.get_num_calls_to_owner('Dave')
    print()
    print('Expected from get_num_calls_to_owner():', expected)
    print('Actual   from get_num_calls_to_owner():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 3:
    print('\nTest 3:')

    phone1 = SmartPhone('Dave')
    phone2 = SmartPhone('Matt')
    phone3 = SmartPhone('Jim')
    phone1.call(phone3)
    phone1.call(phone2)
    phone1.call(phone2)
    phone1.call(phone2)
    phone1.call(phone2)

    expected = 4
    actual = phone1.get_num_calls_to_owner('Matt')
    print()
    print('Expected from get_num_calls_to_owner():', expected)
    print('Actual   from get_num_calls_to_owner():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    expected = 1
    actual = phone1.get_num_calls_to_owner('Jim')
    print()
    print('Expected from get_num_calls_to_owner():', expected)
    print('Actual   from get_num_calls_to_owner():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    expected = 0
    actual = phone2.get_num_calls_to_owner('Dave')
    print()
    print('Expected from get_num_calls_to_owner():', expected)
    print('Actual   from get_num_calls_to_owner():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()


def run_test_reset_owner():
    """ Tests the   reset_owner   method of the SmartPhone class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   reset_owner   method of the SmartPhone class.')
    print('-----------------------------------------------------------')

    # Test 1:
    print('\nTest 1:')

    phone1 = SmartPhone('Dave')
    test_instance_variables(phone1, 'Dave', [])

    print('\nTest 1 (continued):')
    phone1.owner = 'Bob'
    test_instance_variables(phone1, 'Bob', [])

    print('\nTest 1 (continued):')
    phone1.reset_owner()
    test_instance_variables(phone1, 'Dave', [])

    # Test 2:
    print('\nTest 2:')

    phone1 = SmartPhone('Dave')
    phone2 = SmartPhone('Matt')
    phone1.owner = 'Bob'
    phone2.owner = 'Jim'
    test_instance_variables(phone1, 'Bob', [])

    print('\nTest 2 (continued):')
    test_instance_variables(phone2, 'Jim', [])

    print('\nTest 2 (continued):')
    phone1.call(phone2)
    test_instance_variables(phone1, 'Bob', ['Jim'])

    print('\nTest 2 (continued):')
    phone2.reset_owner()
    phone1.call(phone2)
    test_instance_variables(phone1, 'Bob', ['Jim', 'Matt'])

    print('\nTest 2 (continued):')
    phone1.reset_owner()
    test_instance_variables(phone1, 'Dave', ['Jim', 'Matt'])


def run_test_get_num_calls_to_favorite():
    """ Tests the   get_num_calls_to_favorite   method of SmartPhone class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the  get_num_calls_to_favorite method of SmartPhone class.')
    print('-----------------------------------------------------------')

    # Test 1:
    print('\nTest 1:')

    phone1 = SmartPhone('Dave')
    phone2 = SmartPhone('Matt')
    phone3 = SmartPhone('Jim')

    print(phone1.get_num_calls_to_favorite(),
          phone2.get_num_calls_to_favorite(),
          phone3.get_num_calls_to_favorite())  # Should print 0 0 0

    phone3.call(phone2)
    print(phone1.get_num_calls_to_favorite(),
          phone2.get_num_calls_to_favorite(),
          phone3.get_num_calls_to_favorite())  # Should print 0 0 1

    phone1.call(phone2)
    phone1.call(phone2)
    phone1.call(phone3)
    phone1.call(phone3)
    phone1.call(phone2)

    print(phone1.get_num_calls_to_favorite(),
          phone2.get_num_calls_to_favorite(),
          phone3.get_num_calls_to_favorite())  # Should print 3 0 1

    phone1.call(phone3)
    phone1.call(phone1)
    phone1.call(phone1)

    print(phone1.get_num_calls_to_favorite(),
          phone2.get_num_calls_to_favorite(),
          phone3.get_num_calls_to_favorite())  # Should still print 3 0 1

    phone1.call(phone3)
    phone3.call(phone2)
    print(phone1.get_num_calls_to_favorite(),
          phone2.get_num_calls_to_favorite(),
          phone3.get_num_calls_to_favorite())  # Should now print 4 0 2


def run_test_get_total_calls_to_favorite():
    """ Tests the  get_total_calls_to_favorite  method of SmartPhone class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the  get_total_calls_to_favorite  method of SmartPhone.')
    print('-----------------------------------------------------------')

    # Test 1:
    print('\nTest 1:')

    phone1 = SmartPhone('Dave')
    phone2 = SmartPhone('Matt')
    phone3 = SmartPhone('Jim')

    # At this point, phones 1, 2 and 3 have made 0, 0, and 0 calls
    # to their favorites, respectively.  Hence the values of the
    # following 6 variables should all be 0 at this point:

    total_1_2 = phone1.get_total_calls_to_favorite(phone2)
    total_1_3 = phone1.get_total_calls_to_favorite(phone3)

    total_2_1 = phone2.get_total_calls_to_favorite(phone1)
    total_2_3 = phone2.get_total_calls_to_favorite(phone3)

    total_3_1 = phone3.get_total_calls_to_favorite(phone1)
    total_3_2 = phone3.get_total_calls_to_favorite(phone2)

    expected = (0, 0, 0, 0, 0, 0)
    actual = (total_1_2, total_1_3, total_2_1, total_2_3, total_3_1, total_3_2)

    print()
    print('Expected from 6 subtests:', expected)
    print('Actual:                  ', actual)

    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 2:
    print('\nTest 2 (continues from the previous test:')

    # Continuing the example, after phone3 calls phone2:
    phone3.call(phone2)

    # Now phone1 and phone2 have still made 0 calls to their respective
    # favorites, but phone3 has made 1 call to its favorite.
    # Hence now:
    #   phone1.get_total_calls_to_favorite(phone2)  is now 0 + 0 = 0
    #   phone1.get_total_calls_to_favorite(phone3)  is now 0 + 1 = 1
    #
    #   phone2.get_total_calls_to_favorite(phone1)  is now 0 + 0 = 0
    #   phone2.get_total_calls_to_favorite(phone3)  is now 0 + 1 = 1
    #
    #   phone3.get_total_calls_to_favorite(phone1)  is now 1 + 0 = 1
    #   phone3.get_total_calls_to_favorite(phone2)  is now 1 + 0 = 1

    total_1_2 = phone1.get_total_calls_to_favorite(phone2)
    total_1_3 = phone1.get_total_calls_to_favorite(phone3)

    total_2_1 = phone2.get_total_calls_to_favorite(phone1)
    total_2_3 = phone2.get_total_calls_to_favorite(phone3)

    total_3_1 = phone3.get_total_calls_to_favorite(phone1)
    total_3_2 = phone3.get_total_calls_to_favorite(phone2)

    expected = (0, 1, 0, 1, 1, 1)
    actual = (total_1_2, total_1_3, total_2_1, total_2_3, total_3_1, total_3_2)

    print()
    print('Expected from 6 subtests:', expected)
    print('Actual:                  ', actual)

    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 3:
    print('\nTest 3 (continues from the previous test:')

    # Continuing the example, after still more phone calls:
    phone1.call(phone2)
    phone1.call(phone2)
    phone1.call(phone3)
    phone1.call(phone3)
    phone1.call(phone2)

    # At this point, phones 1, 2 and 3 have made 3, 0, and 1 calls
    # to their favorites, respectively.
    # Hence now:
    #   phone1.get_total_calls_to_favorite(phone2)  is now 3 + 0 = 3
    #   phone1.get_total_calls_to_favorite(phone3)  is now 3 + 1 = 4
    #
    #   phone2.get_total_calls_to_favorite(phone1)  is now 0 + 3 = 3
    #   phone2.get_total_calls_to_favorite(phone3)  is now 0 + 1 = 1
    #
    #   phone3.get_total_calls_to_favorite(phone1)  is now 1 + 3 = 4
    #   phone3.get_total_calls_to_favorite(phone2)  is now 1 + 0 = 1

    total_1_2 = phone1.get_total_calls_to_favorite(phone2)
    total_1_3 = phone1.get_total_calls_to_favorite(phone3)

    total_2_1 = phone2.get_total_calls_to_favorite(phone1)
    total_2_3 = phone2.get_total_calls_to_favorite(phone3)

    total_3_1 = phone3.get_total_calls_to_favorite(phone1)
    total_3_2 = phone3.get_total_calls_to_favorite(phone2)

    expected = (3, 4, 3, 1, 4, 1)
    actual = (total_1_2, total_1_3, total_2_1, total_2_3, total_3_1, total_3_2)

    print()
    print('Expected from 6 subtests:', expected)
    print('Actual:                  ', actual)

    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 4:
    print('\nTest 4 (continues from the previous test:')

    # Continuing the example, after still more phone calls:
    phone1.call(phone3)
    phone1.call(phone1)
    phone1.call(phone1)

    # At this point, phones 1, 2 and 3 have still made 3, 0, and 1 calls
    # to their favorites, respectively.
    # Hence we still have:
    #   phone1.get_total_calls_to_favorite(phone2)  is now 3 + 0 = 3
    #   phone1.get_total_calls_to_favorite(phone3)  is now 3 + 1 = 4
    #
    #   phone2.get_total_calls_to_favorite(phone1)  is now 0 + 3 = 3
    #   phone2.get_total_calls_to_favorite(phone3)  is now 0 + 1 = 1
    #
    #   phone3.get_total_calls_to_favorite(phone1)  is now 1 + 3 = 4
    #   phone3.get_total_calls_to_favorite(phone2)  is now 1 + 0 = 1

    total_1_2 = phone1.get_total_calls_to_favorite(phone2)
    total_1_3 = phone1.get_total_calls_to_favorite(phone3)

    total_2_1 = phone2.get_total_calls_to_favorite(phone1)
    total_2_3 = phone2.get_total_calls_to_favorite(phone3)

    total_3_1 = phone3.get_total_calls_to_favorite(phone1)
    total_3_2 = phone3.get_total_calls_to_favorite(phone2)

    expected = (3, 4, 3, 1, 4, 1)
    actual = (total_1_2, total_1_3, total_2_1, total_2_3, total_3_1, total_3_2)

    print()
    print('Expected from 6 subtests:', expected)
    print('Actual:                  ', actual)

    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 5:
    print('\nTest 5 (continues from the previous test:')

    # Continuing the example, after two more phone calls:
    phone1.call(phone3)
    phone3.call(phone2)

    # At this point, phones 1, 2 and 3 have made 4, 0, and 2 calls
    # to their favorites, respectively.
    # Hence now:
    #   phone1.get_total_calls_to_favorite(phone2)  is now 4 + 0 = 4
    #   phone1.get_total_calls_to_favorite(phone3)  is now 4 + 2 = 6
    #
    #   phone2.get_total_calls_to_favorite(phone1)  is now 0 + 4 = 4
    #   phone2.get_total_calls_to_favorite(phone3)  is now 0 + 2 = 2
    #
    #   phone3.get_total_calls_to_favorite(phone1)  is now 2 + 4 = 6
    #   phone3.get_total_calls_to_favorite(phone2)  is now 2 + 0 = 2

    total_1_2 = phone1.get_total_calls_to_favorite(phone2)
    total_1_3 = phone1.get_total_calls_to_favorite(phone3)

    total_2_1 = phone2.get_total_calls_to_favorite(phone1)
    total_2_3 = phone2.get_total_calls_to_favorite(phone3)

    total_3_1 = phone3.get_total_calls_to_favorite(phone1)
    total_3_2 = phone3.get_total_calls_to_favorite(phone2)

    expected = (4, 6, 4, 2, 6, 2)
    actual = (total_1_2, total_1_3, total_2_1, total_2_3, total_3_1, total_3_2)

    print()
    print('Expected from 6 subtests:', expected)
    print('Actual:                  ', actual)

    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()


def run_test_get_num_times_called():
    """ Tests the   get_num_times_called   method of the SmartPhone class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the  get_num_times_called  method of the SmartPhone class.')
    print('-----------------------------------------------------------')

    # Test 1:
    print('\nTest 1:')

    phone1 = SmartPhone('Dave')
    phone2 = SmartPhone('Matt')
    phone1.call(phone2)

    expected = 0
    actual = phone1.get_num_times_called()
    print()
    print('Expected from get_num_times_called():', expected)
    print('Actual   from get_num_times_called():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    expected = 1
    actual = phone2.get_num_times_called()
    print()
    print('Expected from get_num_times_called():', expected)
    print('Actual   from get_num_times_called():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 2:
    print('\nTest 2:')

    phone1 = SmartPhone('Dave')
    phone2 = SmartPhone('Matt')
    phone3 = SmartPhone('Jim')
    phone1.call(phone2)
    phone1.call(phone3)
    phone1.call(phone2)
    phone1.call(phone2)

    expected = 0
    actual = phone1.get_num_times_called()
    print()
    print('Expected from get_num_times_called():', expected)
    print('Actual   from get_num_times_called():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    expected = 3
    actual = phone2.get_num_times_called()
    print()
    print('Expected from get_num_times_called():', expected)
    print('Actual   from get_num_times_called():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    expected = 1
    actual = phone3.get_num_times_called()
    print()
    print('Expected from get_num_times_called():', expected)
    print('Actual   from get_num_times_called():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    phone3.call(phone1)
    expected = 1
    actual = phone1.get_num_times_called()
    print()
    print('Expected from get_num_times_called():', expected)
    print('Actual   from get_num_times_called():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 3:
    print('\nTest 3:')

    phone1 = SmartPhone('Dave')
    phone2 = SmartPhone('Matt')
    phone3 = SmartPhone('Jim')
    phone1.call(phone3)
    phone1.call(phone2)
    phone1.call(phone2)
    phone1.call(phone2)
    phone1.call(phone2)

    expected = 0
    actual = phone1.get_num_times_called()
    print()
    print('Expected from get_num_times_called():', expected)
    print('Actual   from get_num_times_called():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    expected = 4
    actual = phone2.get_num_times_called()
    print()
    print('Expected from get_num_times_called():', expected)
    print('Actual   from get_num_times_called():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    expected = 1
    actual = phone3.get_num_times_called()
    print()
    print('Expected from get_num_times_called():', expected)
    print('Actual   from get_num_times_called():', actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

########################################################################
# The following are HELPER functions that display error messages in RED
# and help make it easier for us to write tests.
# Do NOT change any of the following.
########################################################################


def test_instance_variables(smart_phone,
                            expected_owner,
                            expected_owners_called):
    """
    Tests whether the instance variables for the given SmartPhone
    are per the given expected values.
      -- Prints relevant messages.
      -- Returns True if all is OK, else returns False.
    """
    try:
        return (test_type_of_object(smart_phone) and
                test_types_of_instance_variables(smart_phone) and
                test_values_of_instance_variables(
                    smart_phone,
                    expected_owner,
                    expected_owners_called))
    except Exception:
        something_unexpected_happened_in_our_testing_code()
        return False


def test_values_of_instance_variables(smart_phone,
                                      expected_owner,
                                      expected_owners_called):
    # Print the EXPECTED and ACTUAL values of the instance variables
    format_string = '  {:9} {:7} {}'
    print('  Testing instance variables:')
    print('            owner   owners_called')
    print('            -----   -------------')
    print(format_string.format('Expected:',
                               expected_owner,
                               expected_owners_called))
    print(format_string.format('Actual:',
                               smart_phone.owner,
                               smart_phone.owners_called))

    # Print a message indicating whether or not
    # the EXPECTED values are equal to the ACTUAL values.
    expected = (expected_owner, expected_owners_called)
    actual = (smart_phone.owner, smart_phone.owners_called)

    return print_result_of_test(expected, actual)


def something_unexpected_happened_in_our_testing_code():
    print_failure_message()
    explanation = (
        '  Something unexpected has happened in the testing \n' +
        '  code that we supplied.  You should probably\n' +
        '  SEEK HELP FROM YOUR INSTRUCTOR NOW.')
    print_failure_message(explanation)


def test_type_of_object(smart_phone):
    """ Returns True if the argument is in fact a SmartPhone object """
    if isinstance(smart_phone, SmartPhone):
        return True
    else:
        explanation = ('  The following object to test:\n' +
                       '     ' + str(smart_phone) + '\n' +
                       '  should be a SmartPhone object,\n' +
                       '  but it is not.  Perhaps your code\n' +
                       '  returned something of the wrong type?')
        print_failure_message()
        print_failure_message(explanation)
        return False


def test_types_of_instance_variables(smart_phone):
    """
    Returns True if the argument has the right instance variables
    and they are all numbers.
    """
    # If NONE of the expected instance variables exist,
    # then perhaps the only "problem" is that the  __init__  method
    # has not yet been implemented.
    attributes = dir(smart_phone)
    if ('owner' not in attributes
            and 'owners_called' not in attributes):
        explanation = (
            '  This object:\n' +
            '     ' + str(smart_phone) + '\n' +
            '  should have these instance variables:\n' +
            '     owners\n' +
            '     owners_called\n' +
            '  but it has NONE of them.\n' +
            '  Perhaps you simply have not yet\n' +
            '  implemented the   __init__   method?\n' +
            '  (If so, implement it now.)')
        print_failure_message()
        print_failure_message(explanation)
        return False

    # If SOME (but not all) of the expected instance variables exist,
    # then perhaps something was misspelled in __init__.
    if not ('owner' in attributes
            and 'owners_called' in attributes):
        explanation = (
            '  This object:\n' +
            '     ' + str(smart_phone) + '\n' +
            '  should have these instance variables:\n' +
            '     owners\n' +
            '     owners_called\n' +
            '  but it is missing some of them.\n' +
            '  Perhaps you misspelled something\n' +
            '  in your   __init__   code?')
        print_failure_message()
        print_failure_message(explanation)
        return False

    # Check that the instance variables are of the right types:
#     if not isinstance(smart_phone.owner, str):
#         explanation = (
#             '  This object:\n' +
#             '     ' + str(smart_phone) + '\n' +
#             '  has an instance variable  owner  with this value:\n' +
#             '     owner: ' + str(smart_phone.owner) +
#             '  That value should be a STRING, but is isn\'t.\n')
#         print_failure_message()
#         print_failure_message(explanation)
#         return False
#
#     if not isinstance(smart_phone.owners_called, list):
#         explanation = (
#             '  This object:\n' +
#             '     ' + str(smart_phone) + '\n' +
#             '  has an instance variable  owners_called  with this value:\n' +
#             '     owners_called: ' + str(smart_phone.owners_called) +
#             '  That value should be a LIST, but is isn\'t.\n')
#         print_failure_message()
#         print_failure_message(explanation)
#         return False
#
#     if not is_list_of_strings(smart_phone.owners_called):
#         explanation = (
#             '  This object:\n' +
#             '     ' + str(smart_phone) + '\n' +
#             '  has an instance variable  owners_called  with this value:\n' +
#             '     owners_called: ' + str(smart_phone.owners_called) +
#             '  That value should be a list of STRINGS, but is isn\'t.\n')
#         print_failure_message()
#         print_failure_message(explanation)
#         return False

    return True


def is_list_of_strings(strings):
    return ((strings == [])
            or (isinstance(strings[0], str)
                and is_list_of_strings(strings[1:])))


def print_result_of_test(expected, actual):
    if are_equal(expected, actual):
        print("  PASSED the above test -- good!")
        return True

    print_failure_message()

    if isinstance(expected, list) or isinstance(expected, tuple):
        explanation = (
            '  For at least one of the above, its Expected value\n' +
            '  does not equal its Actual value.')
#          Note: the printed\n' +
#             '  values are the actual values ROUNDED to 1 decimal place.')
        print_failure_message(explanation)

    return False


def are_equal(a, b):
    # We will treat two numbers as being "equal" if they are
    # the same when each is rounded to 12 decimal places.
    if isinstance(a, Number) and isinstance(b, Number):
        return (round(a, 12) == round(b, 12))

    # For lists and tuples, their items have to be equal for the
    # lists/tuples to be equal.
    if isinstance(a, list) and isinstance(b, list):
        if len(a) != len(b):
            return False
        for k in range(len(a)):
            if not are_equal(a[k], b[k]):
                return False
        return True  # All the items were equal.

    if isinstance(a, tuple) and isinstance(b, tuple):
        if len(a) != len(b):
            return False
        for k in range(len(a)):
            if not are_equal(a[k], b[k]):
                return False
        return True  # All the items were equal.

    # For all else, they must be equal in the "usual" way:
    return a == b


def print_failure_message(message='  *** FAILED the above test. ***',
                          flush_time=0.5):
    """ Prints a message onto stderr, hence in RED. """
    time.sleep(flush_time)
    print(message,
          file=sys.stderr, flush=True)
    time.sleep(flush_time)


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
