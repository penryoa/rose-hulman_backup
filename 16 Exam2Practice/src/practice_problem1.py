"""
PRACTICE Test 2, practice_problem 1.

This problem provides practice at:
  ***  IMPLEMENTING CLASSES.  ***

Authors: David Mutchler, Valerie Galluzzi, Mark Hays, Amanda Stouder,
         their colleagues and Olivia Penry.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

########################################################################
# Students:
#
# These problems have DIFFICULTY and TIME ratings:
#  DIFFICULTY rating:  1 to 10, where:
#     1 is very easy
#     3 is an "easy" Test 2 question.
#     5 is a "typical" Test 2 question.
#     7 is a "hard" Test 2 question.
#    10 is an EXTREMELY hard problem (too hard for a Test 2 question)
#
#  TIME ratings: A ROUGH estimate of the number of minutes that we
#     would expect a well-prepared student to take on the problem.
#
#  IMPORTANT: For ALL the problems in this module,
#    if you reach the time estimate and are NOT close to a solution,
#    STOP working on that problem and ASK YOUR INSTRUCTOR FOR HELP
#    on it, in class or via Piazza.
########################################################################

import time
import sys


def main():
    """ Calls the   TEST   functions in this module. """

    ####################################################################
    # UN-comment tests as you work the problems.
    ####################################################################

    run_test_init()
    run_test_append_string()
    run_test_double()
    run_test_shrink()
    run_test_double_then_shrink()
    run_test_reset()
    run_test_steal()
    run_test_get_history()
    run_test_combined_box()


########################################################################
# The   Box   class (and its methods) begins here.
########################################################################

class Box(object):
    """
    A Box has:
        -- CONTENTS, which is a string, and
        -- VOLUME, which is a non-negative integer.
    The length of the Box's CONTENTS can never exceed the Box's VOLUME.
    """

    def __init__(self, contents, volume):
        """
        What comes in:
          -- self
          -- A string that is the contents of the Box
          -- An integer that is the volume (maximum capacity) of the Box
        What goes out: Nothing (i.e., None).
        Side effects:
          -- Stores the Box's contents and volume
             in the instance variables
                 self.contents
                 self.volume
          -- EXCEPT if the length of the given contents
               is bigger than the given volume,
               then   self.contents is set to the empty string
               (simulating a "rejection" of the given contents).
          -- Also initializes other instance variables as needed
              by other methods.
        Examples:
          b1 = Box('Peace', 8)
          #   b1.contents is 'Peace'
          #   b2.volume is 8

          b2 = Box('Peace and Love', 8)
          #   b2.contents is ''   [The contents were too big, hence rejected]
          #   b2.volume is 8

        Type hints:
          :type contents: str
          :type volume: int
        """
        # --------------------------------------------------------------
        # DONE: 2. Implement and test this function.
        #     See the testing code (below) for more examples.
        # --------------------------------------------------------------
        # --------------------------------------------------------------
        # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
        #    DIFFICULTY:      3
        #    TIME ESTIMATE:   5 minutes.
        # --------------------------------------------------------------

        if len(contents) <= volume:
            self.contents = contents
        else:
            self.contents = ''
        self.volume = volume
        self.stuff = 0
        self.n = 0
        self.in_volume = volume
        self.in_contents =self.contents
        self.history = []




    def append_string(self, additional_contents):
        """
        What comes in:
          -- self
          -- A string that is to be appended to this Box's contents
        What goes out:
          Returns a string that is whatever substring of the
          additional_contents did not fit in this Box
          (or the empty string if the entire additional_contents fit)
        Side effects:
          -- Sets this Box's contents to its current contents plus
               (i.e., followed by) as much of the given
               additional_contents as will fit in this Box
        Examples:
          b1 = Box('Hello', 20)
          s = b1.append_string('Goodbye')
          #   b1.contents is now 'HelloGoodbye'
          #   b1.volume is still 20
          #   s is ''  [since the entire additional_contents
          #             fit in this Box]

          b2 = Box('Hello', 8)
          s = b2.append_string('Goodbye')
          #   b2.contents is now 'HelloGoo'
          #   b2.volume is still 8
          #   s is 'dbye'   [this is the part of the additional_contents
          #                  that did NOT fit in this Box]

        Type hints:
          :type additional_contents: str
        """
        # --------------------------------------------------------------
        # DONE: 3. Implement and test this function.
        #     See the testing code (below) for more examples.
        # --------------------------------------------------------------
        # --------------------------------------------------------------
        # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
        #    DIFFICULTY:      10
        #    TIME ESTIMATE:   20 - 30 minutes.
        #
        #   **** IMPORTANT: ****
        # 1. Write a solution to this problem in pseudo-code,
        #    and THEN translate the pseudo-code to a solution.
        #
        # 2. If you do not have a correct solution after 10-15 minutes,
        #    read the file   Read_this_ONLY_when_asked_Part_1.txt
        #    and continue working on the problem.
        #
        # 3. If you still do not have a solution after another 5-10
        #    minutes, then read the file
        #       Read_this_ONLY_when_asked_Part_2.txt
        #    and continue working on the problem.
        # --------------------------------------------------------------

        # Determine how much space is available for the new contents,
        # and then how many characters of the additional_contents
        # can be appended to this Box's contents:

        space = self.volume - len(self.contents)
        number_of_characters_to_append = min(space, len(additional_contents))

        # Build up a string that is the characters to append
        # (that is, those that will fit into this Box).
        # Then append that string to this Box's contents:

        stuff_to_add = ''
        for k in range(number_of_characters_to_append):
            stuff_to_add = stuff_to_add + additional_contents[k]
        self.contents = self.contents + stuff_to_add

        # Build up a string that is the characters that were NOT
        # appended, by starting at the place where the previous loop
        # left off and continuing to the end of the additional_contents.
        # This will be a loop that goes NO times if the entire
        # additional_contents fits into this Box's contents:

        stuff_to_return = ''
        for k in range(number_of_characters_to_append, len(additional_contents)):
            stuff_to_return = stuff_to_return + additional_contents[k]

        # Return the result from the previous loop:
        self.stuff = stuff_to_return

        return stuff_to_return



    def double(self):
        """
        What comes in:
          -- self
        What goes out:
          Returrns a string that is whatever substring of the
          doubled contents did not fit in this Box
          (or the empty string if the entire doubled contents fit)
        Side effects:
          -- Sets this Box's contents to what it was PLUS what it was,
             but clipped if necessary so as not to exceed
             this Box's volume.
        Examples:
          b1 = Box('Robot Fun', 20)
          s = b1.double()
          #   b1.contents is now 'Robot FunRobot Fun'
          #   b1.volume is still 20
          #   s is ''  [since the entire doubled contents fit]

          b2 = Box('Robot Fun', 13)
          s = b2.double()
          #   b2.contents is now 'Robot FunRobo'
          #   b2.volume is still 13
          #   s is 't Fun'   [this is the part of the doubled contents
          #                  that did NOT fit in this Box]

          b3 = Box('Robot Fun', 9)
          s = b3.double()
          #   b3.contents is now 'Robot Fun'
          #   b3.volume is still 9
          #   s is 'Robot Fun'   [this is the part of the doubled
          #                       contents that did NOT fit]
        """
        # --------------------------------------------------------------
        # DONE: 4. Implement and test this function.
        #     The testing code is already written for you (above).
        # --------------------------------------------------------------
        # --------------------------------------------------------------
        # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
        #    DIFFICULTY:      5
        #    TIME ESTIMATE:   3 minutes.
        # --------------------------------------------------------------
        ################################################################
        # FOR FULL CREDIT, YOUR SOLUTION MUST BE NO MORE THAN
        #    ** TWO **   LINES OF CODE.
        ################################################################

        self.append_string(self.contents)
        self.n = self.n + len(self.stuff)
        return self.stuff



    def shrink(self, new_volume):
        """
        What comes in:
          -- self
          -- A nonnegative integer that is to be the new volume
             for this Box
        What goes out:
          Returns the portion (if any) of this Box's contents that had to be
          discarded to make the contents fit within the new volume
          (or the empty string if this Box's contents fit within
          the new volume).
        Side effects:
          -- Sets this Box's volume to the given new_volume.
          -- If the new volume is less than the length of this Box's
             contents, sets this Box's contents to what it was
             but "clipped" to fit in this Box
        Examples:
          b1 = Box('Goodbye', 20)
          s = b1.shrink(8)
          #   b1.contents is still 'Goodbye'
          #   b1.volume is now 8
          #   s is ''  [since the Box's contents fits even with
          #             the new volume]

          b2 = Box('Goodbye', 20)
          s = b2.shrink(4)
          #   b2.contents is now 'Good'
          #   b2.volume is now 4
          #   s is 'bye'  [the portion of the contents that had to be
          #                discarded to make the contents fit
          #                within the new volume]

        Type hints:
          :type new_volume: int
        """
        # --------------------------------------------------------------
        # DONE: 5. Implement and test this function.
        #     The testing code is already written for you (above).
        # --------------------------------------------------------------
        # --------------------------------------------------------------
        # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
        #    DIFFICULTY:      8
        #    TIME ESTIMATE:   12 minutes.
        #
        # IMPORTANT: Write a solution to this problem in pseudo-code,
        # and THEN translate the pseudo-code to a solution.
        # --------------------------------------------------------------

        if len(self.contents) > new_volume:
            new_contents = ''
            for k in range(new_volume):
                new_contents = new_contents + self.contents[k]


            stuff_to_return = ''
            for k in range(new_volume, len(self.contents)):
                stuff_to_return = stuff_to_return + self.contents[k]

            self.contents = new_contents
            self.volume = new_volume

        else:
            self.volume = new_volume
            stuff_to_return = ''

        self.n = self.n + len(stuff_to_return)

        return stuff_to_return




    def double_then_shrink(self, new_volume):
        """
        What comes in:
          -- self
          -- A nonnegative integer that is to be the new volume
             for this Box
        What goes out:
          Returns the number of characters that were discarded (see examples)
        Side effects:
          -- Calls this Box's   double   method, then
          -- Calls this Box's   shrink   method, sending it the given new_volume.
        Examples:
          b1 = Box('Goodbye', 20)
          n = b1.double_then_shrink(17)
          #   b1.contents is now 'GoodbyeGoodbye'
          #   b1.volume is now 17
          #   n is 0  [since no characters were discarded during the doubling
          #            and no characters were discarded during the shrinking]

          b2 = Box('Goodbye', 10)
          n = b2.double_then_shrink(17)
          #   b2.contents is now 'GoodbyeGoo'
          #   b2.volume is now 17
          #   n is 4  [since 4 characters were discarded during the doubling
          #            and 0 characters were discarded during the shrinking]

          b3 = Box('Goodbye', 20)
          n = b3.double_then_shrink(13)
          #   b3.contents is now 'GoodbyeGoodby'
          #   b3.volume is now 13
          #   n is 1  [since 0 characters were discarded during the doubling
          #            and 1 character was discarded during the shrinking]

          b4 = Box('Goodbye', 10)
          n = b4.double_then_shrink(3)
          #   b4.contents is now 'Goo'
          #   b4.volume is now 3
          #   n is 11  [since 4 characters were discarded during the doubling
          #             and 7 characters were discarded during the shrinking]

        Type hints:
          :type new_volume: int
        """
        # --------------------------------------------------------------
        # DONE: 6. Implement and test this function.
        #     The testing code is already written for you (above).
        # --------------------------------------------------------------
        # --------------------------------------------------------------
        # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
        #    DIFFICULTY:      5
        #    TIME ESTIMATE:   5 minutes.
        # --------------------------------------------------------------


        self.double()
        self.shrink(new_volume)

        return self.n







    def reset(self):
        """
        What comes in:
          -- self
        What goes out: Nothing (i.e., None).
        Side effects:
          Changes this Box's contents and volume to whatever they were
          when this Box was constructed.
        """
        # --------------------------------------------------------------
        # DONE: 7. Implement and test this function.
        #     The testing code is already written for you (above).
        # --------------------------------------------------------------
        # --------------------------------------------------------------
        # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
        #    DIFFICULTY:      4
        #    TIME ESTIMATE:   5 minutes.
        # -------------------------------------------------------------

        self.history = self.history + [self.contents]


        self.contents = self.in_contents
        self.volume = self.in_volume




    def steal(self, other_box):
        """
        What comes in:
          -- self
          -- Another Box
        What goes out: Nothing (i.e., None).
        Side effects:
          -- 1. Sets this Box's contents to what is was, but with the
                  other Box's contents appended to this Box's contents
                  (but clipped as needed to fit within this Box)
             2. Sets the other Box's contents to whatever this Box
                  was unable to "steal" (so the empty string if the
                  other Box's entire contents fit within this Box)
        Examples:
          See the TEST cases for examples.
        Type hints:
          :type other_box: Box
        """
        # --------------------------------------------------------------
        # DONE: 8. Implement and test this function.
        #     The testing code is already written for you (above).
        # --------------------------------------------------------------
        # --------------------------------------------------------------
        # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
        #    DIFFICULTY:      7
        #    TIME ESTIMATE:   5 minutes.
        # --------------------------------------------------------------
        ################################################################
        # FOR FULL CREDIT, YOUR SOLUTION MUST BE NO MORE THAN
        #    ** TWO **   LINES OF CODE.
        ################################################################

        self.append_string(other_box.contents)
        other_box.contents = self.stuff




    def get_history(self):
        """
        What comes in:
          -- self
        What goes out:
          Returns a list that contains the contents of this Box
          just before each time that the   reset  method is called.
        Examples:
          b = Box('Good', 20)
          h = b.get_history()
          #   h is now []    since there have been no calls to  reset  yet

          b.double()  # So now b.contents is 'GoodGood'
          b.shrink(6)  # So now b.contents is 'GoodGo'
          h = b.get_history()
          #   h is still []

          b.reset()  # So now b.contents is 'Good' again and its volume is 20 again
          h = b.get_history()
          #   h is now ['GoodGo']

          b.append_string('Bye')  # So now b.contents is 'GoodBye'
          h = b.get_history()
          #   h is still ['GoodGo']

          b.reset()
          h = b.get_history()
          #   h is now ['GoodGo', 'GoodBye']
        """
        # --------------------------------------------------------------
        # DONE: 9. Implement and test this function.
        #     The testing code is already written for you (above).
        # --------------------------------------------------------------
        # --------------------------------------------------------------
        # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
        #    DIFFICULTY:      6
        #    TIME ESTIMATE:   5 minutes.
        # --------------------------------------------------------------

        return self.history



    def combined_box(self, other_box):
        """
        What comes in:
          -- self
          -- Another Box
        What goes out:
          Returns a new Box whose:
            -- Contents is the contents of this Box plus (i.e., followed by)
               the contents of the given other_box
            -- Volume is the sum of the volumes of this Box and the given other_box
        Side effects: None.
        Examples:
          See the TEST cases for examples.
        Type hints:
          :type other_box: Box
        """
        # --------------------------------------------------------------
        # DONE: 10. Implement and test this function.
        #     The testing code is already written for you (above).
        # --------------------------------------------------------------
        # --------------------------------------------------------------
        # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
        #    DIFFICULTY:      4
        #    TIME ESTIMATE:   5 minutes.
        # --------------------------------------------------------------
        b_contents = self.contents + other_box.contents
        b_volume = other_box.volume + self.volume
        b = Box(b_contents, b_volume)

        return b


########################################################################
# The TEST functions for the  Box  class begin here.
########################################################################
def run_test_init():
    """ Tests the   __init__   method of the Box class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   __init__   method of the Box class.')
    print('-----------------------------------------------------------')

    # Test 1:  Contents fit in the Box easily.
    box = Box('Good morning', 20)
    expected_contents = 'Good morning'
    expected_volume = 20
    print("Expected:", expected_contents, expected_volume)
    print("Actual:  ", box.contents, box.volume)
    if (expected_contents == box.contents) and (expected_volume == box.volume):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test 2: Contents barely fit in the Box.
    box = Box('Good morning', 12)
    expected_contents = 'Good morning'
    expected_volume = 12
    print("Expected:", expected_contents, expected_volume)
    print("Actual:  ", box.contents, box.volume)
    if (expected_contents == box.contents) and (expected_volume == box.volume):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test 3: Contents do not fit in the Box, so are "rejected".
    box = Box('Good morning', 11)
    expected_contents = ''
    expected_volume = 11
    print("Expected:", expected_contents, expected_volume)
    print("Actual:  ", box.contents, box.volume)
    if (expected_contents == box.contents) and (expected_volume == box.volume):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()


def run_test_append_string():
    """ Tests the   append_string   method of the Box class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   append_string   method of the Box class.')
    print('-----------------------------------------------------------')

    # Test 1: Appending fits, empty string returned.
    box = Box('Hello', 20)
    clipped = box.append_string('Goodbye')
    #   b1.contents is now 'HelloGoodbye'
    #   b1.volume is still 20
    #   s is ''  [since the entire additional_contents
    #             fit in this Box]
    expected_contents = 'HelloGoodbye'
    expected_volume = 20
    expected_clipped = ''
    print("Expected:", expected_contents, expected_volume, expected_clipped)
    print("Actual:  ", box.contents, box.volume, clipped)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_clipped == clipped)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test 2: Appending does NOT fit, clipped portion returned
    box = Box('Hello', 8)
    clipped = box.append_string('Goodbye')
    #   b2.contents is now 'HelloGoo'
    #   b2.volume is still 8
    #   s is 'dbye'   [this is the part of the additional_contents
    #                  that did NOT fit in this Box]
    expected_contents = 'HelloGoo'
    expected_volume = 8
    expected_clipped = 'dbye'
    print("Expected:", expected_contents, expected_volume, expected_clipped)
    print("Actual:  ", box.contents, box.volume, clipped)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_clipped == clipped)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()


def run_test_double():
    """ Tests the   double   method of the Box class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   double   method of the Box class.')
    print('-----------------------------------------------------------')

    # Test 1: Doubling fits easily, empty string returned.
    initial_contents = 'Good morning'
    box = Box(initial_contents, 30)
    clipped = box.double()

    expected_contents = initial_contents + initial_contents
    expected_volume = 30
    expected_clipped = ''
    print("Expected:", expected_contents, expected_volume, expected_clipped)
    print("Actual:  ", box.contents, box.volume, clipped)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_clipped == clipped)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test 2: Doubling fits barely, empty string returned.
    initial_contents = 'Good morning'
    box = Box(initial_contents, 24)
    clipped = box.double()

    expected_contents = initial_contents + initial_contents
    expected_volume = 24
    expected_clipped = ''
    print("Expected:", expected_contents, expected_volume, expected_clipped)
    print("Actual:  ", box.contents, box.volume, clipped)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_clipped == clipped)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test 3: Doubling almost fits, one-character string returned.
    initial_contents = 'Good morning'
    box = Box(initial_contents, 23)
    clipped = box.double()

    expected_contents = initial_contents + 'Good mornin'
    expected_volume = 23
    expected_clipped = 'g'
    print("Expected:", expected_contents, expected_volume, expected_clipped)
    print("Actual:  ", box.contents, box.volume, clipped)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_clipped == clipped)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test 4: Doubling does not fit, multiple-character string returned.
    initial_contents = 'Good morning'
    box = Box(initial_contents, 20)
    clipped = box.double()

    expected_contents = initial_contents + 'Good mor'
    expected_volume = 20
    expected_clipped = 'ning'
    print("Expected:", expected_contents, expected_volume, expected_clipped)
    print("Actual:  ", box.contents, box.volume, clipped)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_clipped == clipped)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test 5: mutiple doubles
    initial_contents = 'Good morning'
    expected_contents = initial_contents * 4
    expected_volume = 100
    box = Box(initial_contents, expected_volume)
    box.double()
    box.double()
    print("Expected:", expected_contents, expected_volume)
    print("Actual:  ", box.contents, box.volume)
    if (expected_contents == box.contents) and (expected_volume == box.volume):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()


def run_test_shrink():
    """ Tests the   shrink   method of the Box class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   shrink   method of the Box class.')
    print('-----------------------------------------------------------')

    # Test 1:  Volume is small, shrinking occurs
    initial_contents = 'Good morning'
    initial_volume = 20
    box = Box(initial_contents, initial_volume)
    clipped = box.shrink(4)

    expected_contents = 'Good'
    expected_volume = 4
    expected_clipped = ' morning'
    print("Expected:", expected_contents, expected_volume, expected_clipped)
    print("Actual:  ", box.contents, box.volume, clipped)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_clipped == clipped)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test 2:  Volume is big, no shrinking.
    initial_contents = 'Good morning'
    initial_volume = 30
    box = Box(initial_contents, initial_volume)
    clipped = box.shrink(15)

    expected_contents = initial_contents
    expected_volume = 15
    expected_clipped = ''
    print("Expected:", expected_contents, expected_volume, expected_clipped)
    print("Actual:  ", box.contents, box.volume, clipped)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_clipped == clipped)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()


def run_test_double_then_shrink():
    """ Tests the   double_then_shrink   method of the Box class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   double_then_shrink   method of the Box class.')
    print('-----------------------------------------------------------')

    # Test 1: No clipping
    box = Box('Goodbye', 20)
    number_clipped = box.double_then_shrink(17)
    #   box.contents is now 'GoodbyeGoodbye'
    #   box.volume is now 17
    #   n is 0  [since no characters were discarded during the doubling
    #            and no characters were discarded during the shrinking]
    expected_contents = 'GoodbyeGoodbye'
    expected_volume = 17
    expected_clipped = 0
    print("Expected:", expected_contents, expected_volume, expected_clipped)
    print("Actual:  ", box.contents, box.volume, number_clipped)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_clipped == number_clipped)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test 2: Clipping from doubling
    box = Box('Goodbye', 10)
    number_clipped = box.double_then_shrink(17)
    #   box.contents is now 'GoodbyeGoo'
    #   box.volume is now 17
    #   n is 4  [since 4 characters were discarded during the doubling
    #            and 0 characters were discarded during the shrinking]
    expected_contents = 'GoodbyeGoo'
    expected_volume = 17
    expected_clipped = 4
    print("Expected:", expected_contents, expected_volume, expected_clipped)
    print("Actual:  ", box.contents, box.volume, number_clipped)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_clipped == number_clipped)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test 3: Clipping from shrinking
    box = Box('Goodbye', 20)
    number_clipped = box.double_then_shrink(13)
    #   box.contents is now 'GoodbyeGoodby'
    #   box.volume is now 13
    #   n is 1  [since 0 characters were discarded during the doubling
    #            and 1 character was discarded during the shrinking]
    expected_contents = 'GoodbyeGoodby'
    expected_volume = 13
    expected_clipped = 1
    print("Expected:", expected_contents, expected_volume, expected_clipped)
    print("Actual:  ", box.contents, box.volume, number_clipped)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_clipped == number_clipped)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test 4: Clipping from doubling and shrinking
    box = Box('Goodbye', 10)
    number_clipped = box.double_then_shrink(3)
    #   box.contents is now 'Goo'
    #   box.volume is now 3
    #   n is 11  [since 4 characters were discarded during the doubling
    #             and 7 characters were discarded during the shrinking]
    expected_contents = 'Goo'
    expected_volume = 3
    expected_clipped = 11
    print("Expected:", expected_contents, expected_volume, expected_clipped)
    print("Actual:  ", box.contents, box.volume, number_clipped)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_clipped == number_clipped)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()


def run_test_reset():
    """ Tests the   reset   method of the Box class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   reset   method of the Box class.')
    print('-----------------------------------------------------------')

    # Test 1: Reset to contents that fit in original volume
    initial_contents = 'Good morning'
    initial_volume = 100

    expected_contents = initial_contents
    expected_volume = initial_volume

    box = Box(initial_contents, initial_volume)
    box.double()
    box.double_then_shrink(2)
    box.reset()

    print("Expected:", expected_contents, expected_volume)
    print("Actual:  ", box.contents, box.volume)
    if (expected_contents == box.contents) and (expected_volume == box.volume):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test 2: Reset to contents that did not fit in original volume
    initial_contents = 'Good morning'
    initial_volume = 5

    expected_contents = ''
    expected_volume = initial_volume

    box = Box(initial_contents, initial_volume)
    box.double()
    box.double_then_shrink(2)
    box.reset()

    print("Expected:", expected_contents, expected_volume)
    print("Actual:  ", box.contents, box.volume)
    if (expected_contents == box.contents) and (expected_volume == box.volume):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()


def run_test_steal():
    """ Tests the   steal   method of the Box class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   steal   method of the Box class.')
    print('-----------------------------------------------------------')

    # Test 1:  Box 1 steals from 2, where Box 1 has room for all of Box 2's
    # content
    initial_contents_1 = 'Good morning'
    initial_volume_1 = 100
    initial_contents_2 = 'Hello'
    initial_volume_2 = 10
    box1 = Box(initial_contents_1, initial_volume_1)
    box2 = Box(initial_contents_2, initial_volume_2)
    box1.steal(box2)

    expected_contents_1 = initial_contents_1 + initial_contents_2
    expected_volume_1 = initial_volume_1
    expected_contents_2 = ''
    expected_volume_2 = initial_volume_2
    print("Expected 1:", expected_contents_1, expected_volume_1)
    print("Actual   1:", box1.contents, box1.volume)

    print("\nExpected 2:", expected_contents_2, expected_volume_2)
    print("Actual   2:", box2.contents, box2.volume)
    if (expected_contents_1 == box1.contents) and \
            (expected_volume_1 == box1.volume) \
            and (expected_contents_2 == box2.contents) \
            and (expected_volume_2 == box2.volume):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test 2:  Box 1 steals from 2, where Box 1 does NOT have room
    #          for all of Box 2's content
    initial_contents_1 = 'Good morning'
    initial_volume_1 = 15
    initial_contents_2 = 'Hello'
    initial_volume_2 = 10

    expected_contents_1 = initial_contents_1 + 'Hel'
    expected_volume_1 = initial_volume_1

    expected_contents_2 = 'lo'
    expected_volume_2 = initial_volume_2

    box1 = Box(initial_contents_1, initial_volume_1)
    box2 = Box(initial_contents_2, initial_volume_2)
    box1.steal(box2)

    print("Expected 1:", expected_contents_1, expected_volume_1)
    print("Actual   1:", box1.contents, box1.volume)

    print("\nExpected 2:", expected_contents_2, expected_volume_2)
    print("Actual   2:", box2.contents, box2.volume)
    if (expected_contents_1 == box1.contents) \
            and (expected_volume_1 == box1.volume) \
            and (expected_contents_2 == box2.contents) \
            and (expected_volume_2 == box2.volume):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()


def run_test_get_history():
    """ Tests the   get_history   method of the Box class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   get_history   method of the Box class.')
    print('-----------------------------------------------------------')

    # Step 1 of the test:
    box = Box('Good', 20)
    h = box.get_history()
    #   h is now []    since there have been no calls to  reset  yet
    expected_contents = 'Good'
    expected_volume = 20
    expected_h = []
    print("Expected:", expected_contents, expected_volume, expected_h)
    print("Actual:  ", box.contents, box.volume, h)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_h == h)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Step 2 of the test:
    box.double()  # So now box.contents is 'GoodGood'
    box.shrink(6)  # So now box.contents is 'GoodGo'
    h = box.get_history()
    #   h is still []
    expected_contents = 'GoodGo'
    expected_volume = 6
    expected_h = []
    print("Expected:", expected_contents, expected_volume, expected_h)
    print("Actual:  ", box.contents, box.volume, h)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_h == h)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Step 3 of the test:
    # So now box.contents is 'Good' again and box.volume is 20 again
    box.reset()
    h = box.get_history()
    #   h is now ['GoodGo']
    expected_contents = 'Good'
    expected_volume = 20
    expected_h = ['GoodGo']
    print("Expected:", expected_contents, expected_volume, expected_h)
    print("Actual:  ", box.contents, box.volume, h)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_h == h)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Step 4 of the test:
    box.append_string('Bye')  # So now box.contents is 'GoodBye'
    h = box.get_history()
    #   h is still ['GoodGo']
    expected_contents = 'GoodBye'
    expected_volume = 20
    expected_h = ['GoodGo']
    print("Expected:", expected_contents, expected_volume, expected_h)
    print("Actual:  ", box.contents, box.volume, h)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_h == h)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Step 5 of the test:
    box.reset()
    h = box.get_history()
    #   h is now ['GoodGo', 'GoodBye']
    expected_contents = 'Good'
    expected_volume = 20
    expected_h = ['GoodGo', 'GoodBye']
    print("Expected:", expected_contents, expected_volume, expected_h)
    print("Actual:  ", box.contents, box.volume, h)
    if ((expected_contents == box.contents) and
            (expected_volume == box.volume) and
            (expected_h == h)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()


def run_test_combined_box():
    """ Tests the   combined_box   method of the Box class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   combined_box   method of the Box class.')
    print('-----------------------------------------------------------')
    b1 = Box('Roses', 8)
    b2 = Box('Violets', 20)
    b1.double()  # So now 'RosesRos' with volume 8
    b2.shrink(5)  # So now 'Viole' with volume 5

    new_box1 = b1.combined_box(b2)
    new_box2 = b2.combined_box(b1)

    # Test results for new_box1:
    expected_contents = 'RosesRosViole'
    expected_volume = 13
    print("Expected:", expected_contents, expected_volume)
    print("Actual:  ", new_box1.contents, new_box1.volume)
    if ((expected_contents == new_box1.contents) and
            (expected_volume == new_box1.volume)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()

    # Test results for new_box2:
    expected_contents = 'VioleRosesRos'
    expected_volume = 13
    print("Expected:", expected_contents, expected_volume)
    print("Actual:  ", new_box2.contents, new_box2.volume)
    if ((expected_contents == new_box2.contents) and
            (expected_volume == new_box2.volume)):
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()
    print()


def print_failure_message(message='  *** FAILED the above test. ***',
                          flush_time=1.0):
    """ Prints a message onto stderr, hence in RED. """
    time.sleep(flush_time)
    print(message,
          file=sys.stderr, flush=True)
    time.sleep(flush_time)

# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
