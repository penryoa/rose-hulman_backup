"""
Exam 2, problem 1.

Authors: David Mutchler, Dave Fisher, Matt Boutell, their colleagues,
         and Olivia Penry.  Oct 2017.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

import time
import sys


########################################################################
# Problem 1a uses the following very simple   Point3d   class.
# READ but do NOT modify this  Point3d   class.
########################################################################
class Point3d(object):
    """ Represents a point in 3-dimensional space. """

    def __init__(self, x, y, z):
        """ Sets instance variables x, y and z to the given numbers. """
        self.x = x
        self.y = y
        self.z = z

    def __repr__(self):
        """ Returns a string representation of this Point3d. """
        return 'Point3d({}, {}, {})'.format(self.x, self.y, self.z)

    def __eq__(self, other_point):
        """
        Two Point3d objects are equal if they have the same x, y and z
        values.  For example:
          Point3d(10, 33, 150)  ==   Point3d(10, 33, 150)
        evaluates to True, just as you would expect.

        Numbers are rounded to 6 decimal places before comparisons,
        to allow for floating-point arithmetic.
        """
        try:
            return ((round(self.x, 6) == round(other_point.x, 6))
                    and (round(self.y, 6) == round(other_point.y, 6))
                    and (round(self.z, 6) == round(other_point.z, 6)))
        except Exception:
            return False


########################################################################
# The  main  function and the TODOs for you are after this:
########################################################################
def main():
    """ Calls the   TEST   functions in this module. """

    # STUDENTS:  UN-comment these tests as you work the problems.
    run_test_problem1a()
    run_test_problem1b()
    run_test_problem1c()
    run_test_problem1d()


def run_test_problem1a():
    """ Tests the   problem1a   function. """

    print()
    print('--------------------------------------------------')
    print('Testing the   problem1a   function:')
    print('--------------------------------------------------')

    # Test 1:
    print('\n' + 'Test 1:')
    expected = Point3d((30 + 50 + 20 + 30) / 4,
                       (20 + 13 + 22 + 20) / 4,
                       (5 + 0 + 8 + 5) / 4)
    actual = problem1a([Point3d(30, 20, 5),
                        Point3d(50, 13, 0),
                        Point3d(20, 22, 8),
                        Point3d(30, 20, 5)])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 2 (same as Test 1, but using a tuple instead of list):
    print('\n' + 'Test 2:')
    expected = Point3d((30 + 50 + 20 + 30) / 4,
                       (20 + 13 + 22 + 20) / 4,
                       (5 + 0 + 8 + 5) / 4)
    actual = problem1a((Point3d(30, 20, 5),
                        Point3d(50, 13, 0),
                        Point3d(20, 22, 8),
                        Point3d(30, 20, 5)))
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 3:
    print('\n' + 'Test 3:')
    expected = Point3d(0.0, 0.0, 0.0)
    actual = problem1a([Point3d(0, 0, 0)])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 4:
    print('\n' + 'Test 4:')
    expected = Point3d(2.5, 3.5, 4.5)
    actual = problem1a([Point3d(1, 2, 3),
                        Point3d(4, 5, 6)])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 5:
    print('\n' + 'Test 5:')
    expected = Point3d(5.0, 5.0, 5.0)
    actual = problem1a([Point3d(1, 1, 1),
                        Point3d(2, 2, 2),
                        Point3d(3, 3, 3),
                        Point3d(4, 4, 4),
                        Point3d(5, 5, 5),
                        Point3d(6, 6, 6),
                        Point3d(7, 7, 7),
                        Point3d(8, 8, 8),
                        Point3d(9, 9, 9)])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 6:
    print('\n' + 'Test 6:')
    expected = Point3d(1.3333333333333333, 2.5, 5.3333333333333333)
    actual = problem1a([Point3d(10, 20, 30),
                        Point3d(-2, -5, 2),
                        Point3d(0, 0, 0),
                        Point3d(0, 0, 0),
                        Point3d(0, 0, 0),
                        Point3d(0, 0, 0)])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 7:
    print('\n' + 'Test 7:')
    expected = Point3d(5.0, 0.0, 4.0)
    actual = problem1a([Point3d(5, 0, 4)])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 8:
    print('\n' + 'Test 8:')
    expected = Point3d(0.0, 0.0, 0.0)
    actual = problem1a([Point3d(5, 0, 4),
                        Point3d(-5, 0, -4)])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)


def problem1a(points):
    """
    What comes in:  a non-empty sequence of Point3d objects
      (where the   Point3d   class is defined above).
    What goes out:  returns a Point3d object whose:
      -- x coordinate is the   average   of the x-coordinates
           of the Point3d objects in the given sequence
      -- y coordinate is the   average   of the y-coordinates
           of the Point3d objects in the given sequence
      -- z coordinate is the   average   of the z-coordinates
           of the Point3d objects in the given sequence
    Side effects: None.
    Example:
      If   points  is a list that contains:
        -- a Point3d at (30, 20, 5)
        -- a Point3d at (50, 13, 0)
        -- a Point3d at (20, 22, 8)
        -- a Point3d at (30, 20, 5)

      then this function returns a Point3d whose:
         x-coordinate is (30 + 50 + 20 + 30) / 4, that is, 130 / 4 = 32.5
         y-coordinate is (20 + 13 + 22 + 20) / 4, that is, 75 / 4 = 18.75
         z-coordinate is (5 + 0 + 8 + 5) / 4,     that is, 18 / 4 = 4.5

    Type hints:
      :type points: list of Point3d | tuple of Point3d
      :rtype: Point3d
    """
    # ------------------------------------------------------------------
    # DONE: 2. Implement and test this function.
    #          Tests have been written for you (above).
    # ------------------------------------------------------------------
    x_sum = 0
    y_sum = 0
    z_sum = 0

    for k in range(len(points)):
        p = points[k]
        x_sum = x_sum + p.x
        y_sum = y_sum + p.y
        z_sum = z_sum + p.z

    return Point3d(x_sum/len(points), y_sum/len(points), z_sum/len(points))









def run_test_problem1b():
    """ Tests the   problem1b   function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   problem1b   function:')
    print('--------------------------------------------------')

    # Test 1:
    print('\n' + 'Test 1:')
    expected = ['www']
    sequence = ['hello', 'ok', 'what time is it?', 'www', 'nevermore']
    actual = problem1b(sequence)
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 2:
    print('\n' + 'Test 2:')
    expected = ['hhello', 'ook', 'www']
    sequence = ['hhello', 'ook', 'what time is it?', 'www', 'neevermore']
    actual = problem1b(sequence)
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 3:
    print('\n' + 'Test 3:')
    expected = []
    sequence = ['H']
    actual = problem1b(sequence)
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 4:
    print('\n' + 'Test 4:')
    expected = ['yyes']
    sequence = ['H', 'no', 'yyes', 'yes']
    actual = problem1b(sequence)
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 5:
    print('\n' + 'Test 5:')
    expected = []
    sequence = []
    actual = problem1b(sequence)
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 6:
    print('\n' + 'Test 6:')
    expected = ['bbbbbb', 'xxx', 'yyyy']
    sequence = ['one', 'two', 'four', 'a', 'bbbbbb', 'c', 'xxx', 'yyyy']
    actual = problem1b(sequence)
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)


def problem1b(strings):
    """
    What comes in:
      -- a sequence of strings
    What goes out:
      Returns a list of the strings in the given sequence that have this
      characteristic:
         The string's first and second character are the same.

      Note: The order of the strings in the returned list should be in the same
            as in the given list of strings.
    Side effects: None.
    Examples:
      If the given sequence of strings is:
          ['hello', 'ok', 'what time is it?', 'www', 'nevermore']
      then this function returns:
          ['www']
        since that is the only string in the given sequence
        whose 1st and 2nd characters are the same.

      If the given sequence of strings is:
          ['hhello', 'ook', 'what time is it?', 'www', 'neevermore']
      then this function returns:
          ['hhello', 'ook', 'www']

      If the given sequence of strings is:
          ['H']
      then this function returns:
          []
      since no string in the sequence has its 1st and 2nd characters the same.

      If the given sequence of strings is:
          ['H', 'no', 'yyes', 'yes']
      then this function returns:
          ['yyes']

      If the given sequence of strings is:
          []
      then this function returns:
          []

    Type hints:
      :type strings: list of str
      :rtype: list of str
    """
    # ------------------------------------------------------------------
    # DONE: 3. Implement and test this function.
    #          Tests have been written for you (above).
    # ------------------------------------------------------------------

    seq = []
    for k in range(len(strings)):
        s = strings[k]
        if len(s) >= 2:
            if s[1] == s[0]:
                seq = seq + [strings[k]]

    return seq




def run_test_problem1c():
    """ Tests the   problem1c   function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   problem1c   function:')
    print('--------------------------------------------------')

    # Test 1
    print('\n' + 'Test 1:')
    expected = 3
    actual = problem1c([4, 3, 12, 10, 6])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 2
    print('\n' + 'Test 2:')
    expected = 1
    actual = problem1c([7, 5, 10, 6, 7])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 3
    print('\n' + 'Test 3:')
    expected = 0
    actual = problem1c((10, 10, 10, 10, 10, 10, 10, 10, 10))
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 4
    print('\n' + 'Test 4:')
    expected = 8
    actual = problem1c([1, 23, 6, 5, 5, 23, -10000, 34, 8, 20])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 5
    print('\n' + 'Test 5:')
    expected = 0
    actual = problem1c(())
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 6:
    print('\n' + 'Test 6:')
    expected = 0
    actual = problem1c([])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 7:
    print('\n' + 'Test 7:')
    expected = 6
    actual = problem1c([-1000, 100, -150, 110, 120, -100, 99])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 8:
    print('\n' + 'Test 8:')
    expected = 0
    actual = problem1c([])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 9:
    print('\n' + 'Test 9:')
    expected = 0
    actual = problem1c([1])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 10:
    print('\n' + 'Test 10:')
    expected = 1
    actual = problem1c([0, 1])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 11:
    print('\n' + 'Test 11:')
    expected = 1
    actual = problem1c([0.00000000000000000000000000000000001,
                        0.0000000000000000000000000000000000100000001])
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)


def problem1c(numbers):
    """
    What comes in: a sequence of numbers
    What goes out: Returns the number of values in the given sequence
      that are strictly greater than the FIRST value in the given sequence.

      Note:  Returns 0 if the given sequence has fewer than 2 items.
    Side effects: None.
    Examples:
      If the given sequence is [4, 3, 12, 10, 6]
      then:
         -- this function returns 3
              since 12, 10, and 6 are the only numbers in the given sequence
              that are strictly greater than the first value 4.

      If the given sequence is [7, 5, 10, 6, 7]
      then:
         -- this function returns 1
              since 10 is the only number in the given list
              that is STRICTLY greater than the first value of 7.

      If the given sequence is the tuple
         (10, 10, 10, 10, 10, 10, 10, 10, 10)
      then:
         -- and so this function returns 0
              since no numbers in the given list are STRICTLY
              greater than the first value.

      If the given sequence is [42]
      then:
         -- this function returns 0
              since there are no numbers beyond the first for comparisons.

      If the sequence is  []   (that is, the empty list)
      then:
         -- this function returns 0
              since there isn't even a first value for comparisons.

    Type hints:
      :type numbers: (list | tuple) of (float | int)
      :rtype: int
    """
    # ------------------------------------------------------------------
    # DONE: 4. Implement and test this function.
    #          Tests have been written for you (above).
    # ------------------------------------------------------------------


    count = 0
    for k in range(len(numbers)):
        if numbers[k] > numbers[0]:
            count = count + 1

    return count






def run_test_problem1d():
    """ Tests the   problem1d   function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   problem1d   function:')
    print('--------------------------------------------------')

    # Test 1:
    print('\n' + 'Test 1:')
    expected = 'what time is it?'
    sequence = ['hello', 'ok', 'what time is it?', 'www', 'nevermore']
    actual = problem1d(sequence)
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 2:
    print('\n' + 'Test 2:')
    expected = 'justok'
    sequence = ['hello', 'notme', 'norme', '', 'justok', 'not', 'not this one']
    actual = problem1d(sequence)
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 3:
    print('\n' + 'Test 3:')
    expected = 'a'
    sequence = ['', 'a', 'bb', 'ccc']
    actual = problem1d(sequence)
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)

    # Test 4:
    print('\n' + 'Test 4:')
    expected = 'a'
    sequence = ['a', 'a', 'b', 'c', '', 'd']
    actual = problem1d(sequence)
    print("  Expected:", expected)
    print("  Actual:  ", actual)
    print_result_of_test(expected, actual)


def problem1d(strings):
    """
    What comes in:
      -- a non-empty sequence of strings
    What goes out:
      Returns the first string in the given sequence of strings
        whose length is strictly greater than the length of the first string
        in the given sequence of strings.
      Returns the first string in the given sequence of strings
        if NO string in the sequence has length strictly greater than
        the length of the first string in the given sequence of strings.
    Side effects: None.
    Examples:
      If the given sequence of strings is:
          ['hello', 'ok', 'what time is it?', 'www', 'nevermore']
      then this function returns:
          'what time is it?'
        since the first string has length 5, the second one has length 2,
        and the third string ('what time is it?') has length 16,
        which is greater than 5.

      If the given sequence of strings is:
          ['hello', 'notme', 'norme', '', 'justok', 'not', 'not this one']
      then this function returns:
          'justok' since its length is 6, which is greater than the length
          of the first string ('hello', length 5), and it is the first string
          in the given sequence whose length is greater than 5.

      If the given sequence of strings is:
          ['', 'a', 'bb', 'ccc']
      then this function returns:
          'a'

      If the given sequence of strings is:
          ['a', 'a', 'b', 'c', '', 'd']
      then this function returns:
          'a'
      since no string has length greater than 1 (the length of the first
      string in the sequence).

    Type hints:
      :type strings: (list | tuple) of str
      :rtype: str
    """
    # ------------------------------------------------------------------
    # DONE: 5. Implement and test this function.
    #          Tests have been written for you (above).
    # ------------------------------------------------------------------


    for k in range(len(strings)-1):
            if len(strings[0]) < len(strings[k+1]):
                return strings[k+1]

    return strings[0]






########################################################################
# Students: Our tests use the following to print error messages in red.
# Do NOT change it.  You do NOT have to do anything with it.
########################################################################
def print_result_of_test(expected, actual):
    try:
        if expected == actual:
            print("  PASSED the above test -- good!")
        else:
            print_failure_message()
    except Exception:
        print_failure_message()


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