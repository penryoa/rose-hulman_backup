"""
PRACTICE Test 3.

This problem provides practice at:
  ***  SEQUENCES.  ***

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

import simple_testing as st
import math
import rosegraphics as rg


def main():
    """ Calls the   TEST   functions in this module. """
    run_test_practice_problem4a()
    run_test_practice_problem4b()
    run_test_practice_problem4c()
    run_test_practice_problem4d()


def is_prime(n):
    """
    What comes in:   An integer.
    What goes out:  Returns True if the given integer is prime.
                    Returns False if the given integer is NOT prime.
    Side effects: None.
    Examples:
      This function returns True or False, depending on whether
      the given integer is prime or not.  Since the smallest prime is 2,
      this function returns False on all integers < 2.
      It returns True on 2, 3, 5, 7, and other primes.
    Note: The algorithm used here is simple and clear but slow.
    Type hints:
      :type n: int
    """
    if n < 2:
        return False

    for k in range(2, int(math.sqrt(n) + 0.1) + 1):
        if n % k == 0:
            return False

    return True
    # ------------------------------------------------------------------
    # Students:
    #   Do NOT touch the above  is_prime  function - it has no TO DO.
    #   Do NOT copy code from this function.
    #
    # Instead, ** CALL ** this function as needed in the problems below.
    # ------------------------------------------------------------------


# ----------------------------------------------------------------------
# Students: Some of the testing code below uses SimpleTestCase objects,
#           from the imported   simple_testing (st)   module.
#           See details in the  test  code below.
# ----------------------------------------------------------------------
def run_test_practice_problem4a():
    """ Tests the    practice_problem4a    function. """
    # ------------------------------------------------------------------
    # 4 tests.  They use the imported   simple_testing (st)   module.
    # Each test is a SimpleTestCase with 3 arguments:
    #   -- the function to test,
    #   -- a list containing the argument(s) to send to the function,
    #   -- the correct returned value.
    # For example, the first test below will call
    #   practice_problem4v((9, 33, 8, 8, 0, 4, 4, 8))
    # and compare the returned value against [2, 5] (the correct answer).
    # ------------------------------------------------------------------
    tests = [st.SimpleTestCase(practice_problem4a,
                               [(9, 33, 8, 8, 0, 4, 4, 8)],
                               [2, 5]),
             st.SimpleTestCase(practice_problem4a,
                               [(9, 9, 9, 9, 0, 9, 9, 9)],
                               [0, 1, 2, 5, 6]),
             st.SimpleTestCase(practice_problem4a,
                               [(4, 5, 4, 5, 4, 5, 4)],
                               []),
             st.SimpleTestCase(practice_problem4a,
                               ['abbabbb'],
                               [1, 4, 5]),
             ]

    # Run the 4 tests in the   tests   list constructed above.
    st.SimpleTestCase.run_tests('practice_problem4a', tests)


def practice_problem4a(sequence):
    """
    What comes in: A non-empty sequence.
    What goes out: Returns a list of integers,
      where the integers are the places (indices)
      where an item in the given sequence appears twice in a row.
    Side effects: None.
    Examples:
      Given sequence (9, 33, 8, 8, 0, 4, 4, 8)
         -- this function returns [2, 5]
              since 8 appears twice in a row starting at index 2
              and 4 appears twice in a row starting at index 5

      Given sequence (9, 9, 9, 9, 0, 9, 9, 9)
         -- this function returns [0, 1, 2, 5, 6]

      Given sequence (4, 5, 4, 5, 4, 5, 4)
         -- this function returns []

      Given sequence 'abbabbb'
         -- this function returns [1, 4, 5]

    Type hints:
      :type sequence: list | tuple | string
    """
    ####################################################################
    # DONE: 2. Implement and test this function.
    #     The testing code is already written for you (above).
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      7
    #    TIME ESTIMATE:   15 minutes.
    ####################################################################

    list = []
    for k in range(len(sequence)-1):
        if sequence[k+1] == sequence[k]:
            list = list + [k]

    return list



def run_test_practice_problem4b():
    """ Tests the    practice_problem4b    function. """
    # ------------------------------------------------------------------
    # 5 tests.  They use the imported   simple_testing (st)   module.
    # Each test is a SimpleTestCase with 3 arguments:
    #   -- the function to test,
    #   -- a list containing the argument(s) to send to the function,
    #   -- the correct returned value.
    # For example, the first test below will call
    #   practice_problem4b((12, 33, 18, 9, 13, 3, 9, 20, 19, 20))
    # and compare the returned value against 19 (the correct answer).
    # ------------------------------------------------------------------
    tests = [st.SimpleTestCase(practice_problem4b,
                               [(12, 33, 18, 9, 13, 3, 9, 20, 19, 20)],
                               19),
             st.SimpleTestCase(practice_problem4b,
                               [(3, 12, 10, 8, 8, 9, 8, 11)],
                               10),
             st.SimpleTestCase(practice_problem4b,
                               [(-9999999999, 8888888888)],
                               - 9999999999),
             st.SimpleTestCase(practice_problem4b,
                               [(8888888888, -9999999999)],
                               8888888888),
             st.SimpleTestCase(practice_problem4b,
                               [(-77, 20000, -33, 40000, -55,
                                 60000, -11)],
                               - 11),
             ]

    # ------------------------------------------------------------------
    # Run the 5 tests in the   tests   list constructed above.
    # ------------------------------------------------------------------
    st.SimpleTestCase.run_tests('practice_problem4b', tests)


def practice_problem4b(sequence):
    """
    What comes in:
      A sequence of numbers, where the length of the sequence >= 2.
    What goes out:
      Returns the largest of the numbers at EVEN INDICES of the sequence.
    Side effects: None.
    Examples:
      If the sequence is:
          (12, 33, 18, 9, 13, 3, 99, 20, 19, 20)
      then the largest of the numbers at EVEN indices is the largest of
           12      18     13     99      19        which is 99.
      So the function returns 99 in this example.

    Type hints:
      :type sequence: (list | tuple) of (float | int)
    """
    # ------------------------------------------------------------------
    # DONE: 3. Implement and test this function.
    #     The testing code is already written for you (above).
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      5
    #    TIME ESTIMATE:   10 minutes.
    ####################################################################


    list = []
    for k in range(0, len(sequence), 2):
        list = list + [sequence[k]]

    largest = list[0]
    for j in range(len(list)):
        if list[j] > largest:
            largest = list[j]

    return largest


def run_test_practice_problem4c():
    """ Tests the    practice_problem4c    function. """
    # ------------------------------------------------------------------
    # 3 tests.  They use the imported   simple_testing (st)   module.
    # ------------------------------------------------------------------
    argument1 = (rg.Point(5, 12),
                 rg.Point(20, 20),
                 rg.Point(1, 13),
                 rg.Point(10, 40),
                 rg.Point(13, 5),
                 rg.Point(10, 3),
                 rg.Point(3, 7),
                 rg.Point(2, 2))
    answer1 = rg.Point(5, 13)

    argument2 = (rg.Point(5, 12),
                 rg.Point(20, 20),
                 rg.Point(27, 13),
                 rg.Point(10, 40),
                 rg.Point(13, 4),
                 rg.Point(1, 1),
                 rg.Point(3, 7))
    answer2 = rg.Point(7, 3)

    argument3 = (rg.Point(5, 2),
                 rg.Point(20, 20),
                 rg.Point(27, 13),
                 rg.Point(10, 40),
                 rg.Point(13, 4),
                 rg.Point(1, 1),
                 rg.Point(3, 7))
    answer3 = rg.Point(2, 5)

    argument4 = (rg.Point(5, 12),
                 rg.Point(20, 20),
                 rg.Point(27, 13))
    answer4 = 'Not found'

    tests = [st.SimpleTestCase(practice_problem4c, [argument1], answer1),
             st.SimpleTestCase(practice_problem4c, [argument2], answer2),
             st.SimpleTestCase(practice_problem4c, [argument3], answer3),
             st.SimpleTestCase(practice_problem4c, [argument4], answer4),
             ]
    # ------------------------------------------------------------------
    # Run the 3 tests in the   tests   list constructed above.
    # ------------------------------------------------------------------
    st.SimpleTestCase.run_tests('practice_problem4c', tests)

    if argument1[4] != answer1:
        print()
        print('*** WARNING, WARNING, WARNING ***')
        print('If your code DID pass the above tests')
        print('but you get this message,')
        print('then you have missed an important concept about mutation.')
        print('  *** SEE YOUR INSTRUCTOR for an important explanation!')
        print()


def practice_problem4c(points):
    """
    What comes in:  A tuple of rg.Points, each of whose coordinates
      is an integer.
    What goes out:
      AFTER doing the side effect below, this function
      returns the rg.Point to which it did the side effect.
      If there is no point to which to do the side effect,
      returns 'Not found'.
    Side effects:
      Swaps the x and y coordinates of the first occurrence of an rg.Point
      in the given list whose x and y coordinates are both primes.
      Has no side effect if there are no such rg.Points
      in the given list.
    Examples:
      If the given tuple is: (rg.Point(5, 12),
                              rg.Point(20, 20),
                              rg.Point(1, 13),
                              rg.Point(10, 40),
                              rg.Point(13, 5),
                              rg.Point(10, 3),
                              rg.Point(3, 7),
                              rg.Point(2, 2))
      then after this function the rg.Point in the given tuple
      whose x and y were (13, 5) will have x and y (5, 13)
      and the function returns that rg.Point.
    Type hints:
      :type points: tuple of rg.Point
      :rtype: rg.Point | string
    """
    ####################################################################
    # DONE: 4. Implement and test this function.
    #     The testing code is already written for you (above).
    #
    # IMPORTANT: This problem is your LOWEST PRIORITY for preparing
    #  for Test 2.  It is a great problem but WAY more subtle
    #  than anything that you will see on Test 2.
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      9
    #    TIME ESTIMATE:   15 minutes.
    ####################################################################

    return_value = 'Not found'

    for k in range(len(points)):
        point = points[k]
        if is_prime(point.x) == True:
            if is_prime(point.y) == True:
                b = point.x
                point.x = point.y
                point.y = b
                return_value = point
                return return_value

    return return_value


def run_test_practice_problem4d():
    """ Tests the    practice_problem4d    function. """
    # ------------------------------------------------------------------
    # 5 tests.  They use the imported   simple_testing (st)   module.
    # Each test is a SimpleTestCase with 3 arguments:
    #   -- the function to test,
    #   -- a list containing the argument(s) to send to the function,
    #   -- the correct returned value.
    # For example, the first test below will call
    #   practice_problem4d((6, 80, 17, 13, 40, 3, 3, 7, 13, 7, 12, 5))
    # and compare the returned value against 40 (the correct answer).
    # ------------------------------------------------------------------
    tests = [st.SimpleTestCase(practice_problem4d,
                               [(6, 80, 17, 13, 40, 3, 3, 7, 13, 7, 12, 5)],
                               17 + 3 + 7 + 13),
             st.SimpleTestCase(practice_problem4d,
                               [(7, 7, 7, 7, 7, 4, 4, 8, 5, 5, 6)],
                               0),
             st.SimpleTestCase(practice_problem4d,
                               [(2, 3, 5, 7, 5, 3, 2)],
                               2 + 3 + 5 + 7 + 5 + 3),
             st.SimpleTestCase(practice_problem4d,
                               [(11, 3, 17, 13, 40, 3, 3, 7, 13, 7, 12, 5)],
                               11 + 3 + 17 + 3 + 7 + 13),
             st.SimpleTestCase(practice_problem4d,
                               [(6, 80, 17, 13, 40, 3, 3, 7, 13, 7, 11, 5)],
                               17 + 3 + 7 + 13 + 7 + 11),
             ]

    # Run the 5 tests in the   tests   list constructed above.
    st.SimpleTestCase.run_tests('practice_problem4d', tests)


def practice_problem4d(sequence):
    """
    What comes in: A non-empty sequence of integers.
    What goes out: An integer that is the sum of all the items
      in the given sequence such that:
        -- the item is a prime number, AND
        -- the immediate successor of the item
             is a DIFFERENT prime number.
    Side effects: None.
    Examples:
      Given sequence (6, 80, 17, 13, 40, 3, 3, 7, 13, 7, 12, 5)
         -- this function returns  17 + 3 + 7 + 13,  which is 40,
            because:
            6 (at index 0) is NOT prime - do NOT include 6 in the sum
            80 (at index 1) is NOT prime - do NOT include 80 in the sum

            17 (at index 2) IS prime AND the next item (13, at index 3)
              is a DIFFERENT prime - ** DO ** include 17 in the sum

            13 (at index 3) IS prime but the next item (40, at index 4)
              is NOT prime - do NOT include 13 in the sum
            40 (at index 4) is NOT prime - do NOT include 40 in the sum
            3 (at index 5) IS prime AND the next item (3, at index 6)
              IS prime but is NOT a DIFFERENT prime -
              do NOT include 3 in the sum

            3 (at index 6) IS prime AND the next item (7, at index 7)
              is a DIFFERENT prime - ** DO ** include 3 in the sum
            7 (at index 7) IS prime AND the next item (13, at index 8)
              is a DIFFERENT prime - ** DO ** include 7 in the sum
            13 (at index 8) IS prime AND the next item (7, at index 9)
              is a DIFFERENT prime - ** DO ** include 13 in the sum

            7 (at index 9) IS prime but the next item (12, at index 10)
              is NOT prime - do NOT include 7 in the sum
            12 (at index 10) is NOT prime - do NOT include 12 in the sum
            5 (at index 11) IS prime but there is NO item after it
               - do NOT include 5 in the sum

      Given sequence (7, 7, 7, 7, 7, 4, 4, 8, 5, 5, 6)
         -- this function returns 0

      Given sequence (2, 3, 5, 7, 5, 3, 2)
         -- this function returns 2 + 3 + 5 + 7 + 5 + 3, which is 25

    Type hints:
      :type sequence: (list | tuple) of int
      :rtype: int
    """
    ####################################################################
    # DONE: 5. Implement and test this function.
    #     The testing code is already written for you (above).
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      7
    #    TIME ESTIMATE:   15 minutes.
    ####################################################################

    sum = 0
    for k in range(len(sequence)-1):
        if sequence[k+1] != sequence[k]:
            if is_prime(sequence[k]) == True:
                if is_prime(sequence[k+1]) == True:
                    sum = sum + sequence[k]
    return sum

# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
