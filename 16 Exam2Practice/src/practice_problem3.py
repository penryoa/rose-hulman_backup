"""
PRACTICE Test 2, practice_problem 3.

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Amanda Stouder,
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
    run_test_practice_problem3a()
    run_test_practice_problem3b()
    run_test_practice_problem3c()
    run_test_practice_problem3d()
    run_test_practice_problem3e()


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
    #   Do NOT touch the above  is_prime  function - it has no to do.
    #   Do NOT copy code from this function.
    #
    # Instead, ** CALL ** this function as needed in the problems below.
    # ------------------------------------------------------------------


# ----------------------------------------------------------------------
# Students: Some of the testing code below uses SimpleTestCase objects,
#           from the imported   simple_testing (st)   module.
#           See details in the  test  code below.
# ----------------------------------------------------------------------


def run_test_practice_problem3a():
    """ Tests the    practice_problem3a    function. """
    # ------------------------------------------------------------------
    # 6 tests.
    # They use the imported   simple_testing (st)   module.
    # Each test is a SimpleTestCase with 3 arguments:
    #   -- the function to test,
    #   -- a list containing the argument(s) to send to the function,
    #   -- the correct returned value.
    # For example, the first test below will call
    #   practice_problem3a((rg.Circle(rg.Point(5, 10), 20),
    #                       rg.Circle(rg.Point(2, 20), 20),
    #                       rg.Circle(rg.Point(7, 30), 10),
    #                       rg.Circle(rg.Point(10, 40), 20),
    #                       rg.Circle(rg.Point(2, 50), 10)))
    # and compare the returned value against 1400 (the correct answer).
    # ------------------------------------------------------------------
    tests = [st.SimpleTestCase(practice_problem3a,
                               [(rg.Circle(rg.Point(5, 10), 20),
                                 rg.Circle(rg.Point(2, 20), 20),
                                 rg.Circle(rg.Point(7, 30), 10),
                                 rg.Circle(rg.Point(10, 40), 20),
                                 rg.Circle(rg.Point(2, 50), 10))],
                               5 * 2 * 7 * 10 * 2),
             st.SimpleTestCase(practice_problem3a,
                               [(rg.Circle(rg.Point(58, 10), 20),)],
                               58),
             st.SimpleTestCase(practice_problem3a,
                               [(rg.Circle(rg.Point(84, 100), 200),
                                 rg.Circle(rg.Point(28, 200), 200),
                                 rg.Circle(rg.Point(10005, 300), 100))],
                               84 * 28 * 10005),
             st.SimpleTestCase(practice_problem3a,
                               [()],
                               1),
             st.SimpleTestCase(practice_problem3a,
                               [(rg.Circle(rg.Point(5, 10), 20),
                                 rg.Circle(rg.Point(0, 20), 20),
                                 rg.Circle(rg.Point(7, 30), 10),
                                 rg.Circle(rg.Point(10, 40), 20),
                                 rg.Circle(rg.Point(2, 50), 10))],
                               5 * 0 * 7 * 10 * 2),
             ]

    circles = []
    for k in range(1, 101):
        circles.append(rg.Circle(rg.Point(k, k + 20), 5 * k))
    answer = math.factorial(100)
    tests.append(st.SimpleTestCase(practice_problem3a,
                                   [circles],
                                   answer))

    # ------------------------------------------------------------------
    # Run the 6 tests in the   tests   list constructed above.
    # ------------------------------------------------------------------
    st.SimpleTestCase.run_tests('practice_problem3a', tests)


def practice_problem3a(circles):
    """
    What comes in:  A sequence of rg.Circles.
    What goes out:  Returns the product of the x-coordinates
      of the centers of the rg.Circles.
      Returns 1 if the given sequence is empty.
    Side effects: None.
    Examples:
      If the sequence is a list containing these 5 rg.Circles:
        rg.Circle(rg.Point(5, 10), 20)
        rg.Circle(rg.Point(2, 20), 20)
        rg.Circle(rg.Point(7, 30), 10)
        rg.Circle(rg.Point(10, 40), 20)
        rg.Circle(rg.Point(2, 50), 10)
      then this function returns:
        5 x 2 x 7 x 10 x 2, which is 1400.
    Type hints:
      :type sequence: [rg.Circle]
    """
    ####################################################################
    # DONE: 2. Implement and test this function.
    #     The testing code is already written for you (above).
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      7
    #    TIME ESTIMATE:   10 minutes.
    ####################################################################
    product = 1

    if circles == '':
        return product
    else:
        for k in range(len(circles)):
            c = circles[k]
            x = c.center.x

            product = product * x

        return product


def run_test_practice_problem3b():
    """ Tests the    practice_problem3b    function. """
    # ------------------------------------------------------------------
    # 13 tests.  They use the imported   simple_testing (st)   module.
    # Each test is a SimpleTestCase with 3 arguments:
    #   -- the function to test,
    #   -- a list containing the argument(s) to send to the function,
    #   -- the correct returned value.
    # For example, the first test below will call
    #   practice_problem3b([12, 33, 18, 'hello', 9, 13, 3, 9])
    # and compare the returned value against True (the correct answer).
    # ------------------------------------------------------------------
    tests = [st.SimpleTestCase(practice_problem3b,
                               [[12, 33, 18, 'hello', 9, 13, 3, 9]],
                               True),
             st.SimpleTestCase(practice_problem3b,
                               [[12, 12, 33, 'hello', 5, 33, 5, 9]],
                               False),
             st.SimpleTestCase(practice_problem3b,
                               [(77, 112, 33, 'hello', 0, 43, 5, 77)],
                               True),
             st.SimpleTestCase(practice_problem3b,
                               [[1, 1, 1, 1, 1, 1, 2]],
                               False),
             st.SimpleTestCase(practice_problem3b,
                               [['aa', 'a']],
                               False),
             st.SimpleTestCase(practice_problem3b,
                               ['aaa'],
                               True),
             st.SimpleTestCase(practice_problem3b,
                               [['aa', 'a', 'b', 'a', 'b', 'a']],
                               True),
             st.SimpleTestCase(practice_problem3b,
                               [[9]],
                               False),
             st.SimpleTestCase(practice_problem3b,
                               [(12, 33, 8, 'hello', 99, 'hello')],
                               True),
             st.SimpleTestCase(practice_problem3b,
                               [['hello there', 'he', 'lo', 'hello']],
                               False),
             st.SimpleTestCase(practice_problem3b,
                               [((8,), '8', (4 + 4, 4 + 4), [8, 8], 7, 8)],
                               False),
             st.SimpleTestCase(practice_problem3b,
                               [[(8,), '8', [4 + 4, 4 + 4],
                                 (8, 8), 7, [8, 8]]],
                               True),
             st.SimpleTestCase(practice_problem3b,
                               [[(8,), '8', [4 + 4, 4 + 4],
                                 [8, 8], 7, (8, 8)]],
                               False),
             ]

    # Run the 13 tests in the   tests   list constructed above.
    st.SimpleTestCase.run_tests('practice_problem3b', tests)


def practice_problem3b(sequence):
    """
    What comes in: A non-empty sequence.
    What goes out: Returns True if the last item of the sequence
      appears again somewhere else in the sequence.  Returns False
      if the last item of the sequence does NOT appear somewhere
      else in the sequence.
    Side effects: None.
    Examples:
      If the sequence is [12, 33, 18, 'hello', 9, 13, 3, 9],
      this function returns True because the last item (9)
      DOES appear elsewhere in the sequence (namely, at index 4).

      If the sequence is [12, 12, 33, 'hello', 5, 33, 5, 9],
      this function returns False because the last item (9)
      does NOT appear elsewhere in the sequence.

      If the sequence is (77, 112, 33, 'hello', 0, 43, 5, 77),
      this function returns True because the last item (77)
      DOES appear elsewhere in the sequence (namely, at index 0).

      If the sequence is [9], this function returns False
      because the last item (9) does NOT appear elsewhere
      in the sequence.

      If the sequence is [12, 33, 8, 'hello', 99, 'hello'],
      this function returns True since the last item ('hello')
      DOES appear elsewhere in the sequence
      (namely, at indices 3 and 5).

      If the sequence is ['hello there', 'he', 'lo', 'hello'],
      this function returns False because the last item ('hello')
      does NOT appear elsewhere in the sequence.

      If the sequence is 'hello there',
      this function returns True since the last item ('e') DOES
      appear elsewhere in the sequence (namely, at indices 1 and 8).

    Type hints:
      :type: sequence: list    or tuple or string
    """
    ####################################################################
    # DONE: 3. Implement and test this function.
    #     The testing code is already written for you (above).
    #
    # IMPLEMENTATION REQUIREMENT:  You are NOT allowed to use the
    #    'count' or 'index' methods for sequences in this problem
    #    (because here we want you to demonstrate your ability
    #    to use explicit looping).
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      5
    #    TIME ESTIMATE:   8 minutes.
    ####################################################################


    last_item = sequence[len(sequence)-1]

    for k in range(len(sequence)-1):
        if sequence[k] == last_item:
            return True
    return False






def run_test_practice_problem3c():
    """ Tests the    practice_problem3c    function. """
    # ------------------------------------------------------------------
    # 4 tests.  They use the imported   simple_testing (st)   module.
    # Each test is a SimpleTestCase with 3 arguments:
    #   -- the function to test,
    #   -- a list containing the argument(s) to send to the function,
    #   -- the correct returned value.
    # For example, the first test below will call
    #   practice_problem3c((9, 0, 8, 0, 0, 4, 4, 0))
    # and compare the returned value against [1, 3, 4, 7]
    # (the correct answer).
    # ------------------------------------------------------------------
    tests = [st.SimpleTestCase(practice_problem3c,
                               [(9, 0, 8, 0, 0, 4, 4, 0)],
                               [1, 3, 4, 7]),
             st.SimpleTestCase(practice_problem3c,
                               [(9, 9, 9, 9, 0, 9, 9, 9)],
                               [4]),
             st.SimpleTestCase(practice_problem3c,
                               [(4, 5, 4, 5, 4, 5, 4)],
                               []),
             st.SimpleTestCase(practice_problem3c,
                               [[0, 0, 0]],
                               [0, 1, 2]),
             st.SimpleTestCase(practice_problem3c,
                               [[0, 0]],
                               [0, 1]),
             st.SimpleTestCase(practice_problem3c,
                               [[0, 77]],
                               [0]),
             st.SimpleTestCase(practice_problem3c,
                               [[-40, 0]],
                               [1]),
             st.SimpleTestCase(practice_problem3c,
                               [[-40, 67]],
                               []),
             st.SimpleTestCase(practice_problem3c,
                               [(1, 0, 2, 0, 0, 0, 0, 6, 9, 0, 0, 12)],
                               [1, 3, 4, 5, 6, 9, 10]),
             ]

    # Run the tests in the   tests   list constructed above.
    st.SimpleTestCase.run_tests('practice_problem3c', tests)


def practice_problem3c(sequence):
    """
    What comes in: A non-empty sequence of integers.
    What goes out: Returns a list of integers,
      where the integers are the places (indices)
      for which the item at that place equals 0.
    Side effects: None.
    Examples:
      Given sequence (9, 0, 8, 0, 0, 4, 4, 0)
         -- this function returns [1, 3, 4, 7]
              since 0 appears at indices 1, 3, 4, and 7.

      Given sequence [9, 9, 9, 9, 0, 9, 9, 9]
         -- this function returns [4]
              since 0 appears only at index 4.

      Given sequence (4, 5, 4, 5, 4, 5, 4)
         -- this function returns []
              since none of the items are 0.

      Given sequence [0, 0, 0]
         -- this function returns [0, 1, 2]
              since 0 appears at indices 0, 1 and 2.

    Type hints:
      :type: sequence: list    or tuple or string
    """
    ####################################################################
    # DONE: 4. Implement and test this function.
    #     The testing code is already written for you (above).
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      5
    #    TIME ESTIMATE:   8 minutes.
    ####################################################################

    list_zeroes = []
    for k in range(len(sequence)):
        if sequence[k] == 0:
            list_zeroes = list_zeroes + [k]

    return list_zeroes






def run_test_practice_problem3d():
    """ Tests the    practice_problem3d    function. """
    # ------------------------------------------------------------------
    # 4 tests.  They use the imported   simple_testing (st)   module.
    # Each test is a SimpleTestCase with 3 arguments:
    #   -- the function to test,
    #   -- a list containing the argument(s) to send to the function,
    #   -- the correct returned value.
    # For example, the first test below will call
    #   practice_problem3d((9, 0, 8, 0, 0, 4, 4, 0))
    # and compare the returned value against 1 (the correct answer).
    # ------------------------------------------------------------------
    tests = [st.SimpleTestCase(practice_problem3d,
                               [(9, 0, 8, 0, 0, 4, 4, 0)],
                               1),
             st.SimpleTestCase(practice_problem3d,
                               [(9, 9, 9, 9, 0, 9, 9, 9)],
                               4),
             st.SimpleTestCase(practice_problem3d,
                               [(4, 5, 4, 5, 4, 5, 4)],
                               - 1),
             st.SimpleTestCase(practice_problem3d,
                               [[0, 0, 0]],
                               0),
             st.SimpleTestCase(practice_problem3d,
                               [[0, 0]],
                               0),
             st.SimpleTestCase(practice_problem3d,
                               [[0, 77]],
                               0),
             st.SimpleTestCase(practice_problem3d,
                               [[-40, 0]],
                               1),
             st.SimpleTestCase(practice_problem3d,
                               [[-40, 67]],
                               - 1),
             st.SimpleTestCase(practice_problem3d,
                               [(1, 0, 2, 0, 0, 0, 0, 6, 9, 0, 0, 12)],
                               1),
             ]

    # Run the tests in the   tests   list constructed above.
    st.SimpleTestCase.run_tests('practice_problem3d', tests)


def practice_problem3d(sequence):
    """
    What comes in: A sequence of integers.
    What goes out: Returns the first (leftmost) place (index)
      for which the item at that place equals 0.
      Returns -1 if the sequence contains no items equal to 0.
    Side effects: None.
    Examples:
      Given sequence (9, 0, 8, 0, 0, 4, 4, 0)
         -- this function returns 1
              since 0 first appears at index 1

      Given sequence [9, 9, 9, 9, 0, 9, 9, 9]
         -- this function returns 4
              since 0 first appears at index 4

      Given sequence (4, 5, 4, 5, 4, 5, 4)
         -- this function returns -1
              since none of the items are 0.

      Given sequence [0, 0, 0]
         -- this function returns 0
              since 0 first appears at index 0

    Type hints:
      :type: sequence: list    or tuple or string
    """
    ####################################################################
    # DONE: 5. Implement and test this function.
    #     The testing code is already written for you (above).
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      5
    #    TIME ESTIMATE:   8 minutes for each part of this problem.
    ####################################################################


    for k in range(len(sequence)):
        if sequence[k] == 0:
            return k

    return -1



    ####################################################################
    # DONE: 6. Just ABOVE this to do, you should have implemented
    #     a solution for the   practice_problem3d   function.
    #     Here, put ANOTHER solution, as follows:
    #
    #       -- Your FIRST solution (ABOVE this to do)
    #            should be a solution that IGNORES
    #              practice_problem3c (the previous problem).
    #
    #       -- Your SECOND solution (BELOW this to do)
    #            should be a solution that USES (calls)
    #              practice_problem3c.
    #
    #          This solution should *** HAVE NO LOOP (no FOR). ***
    ####################################################################


    a = practice_problem3c(sequence)
    if a == []:
        return -1
    else:
        return a[0]



def run_test_practice_problem3e():
    """ Tests the    practice_problem3e    function. """
    # ------------------------------------------------------------------
    # 5 tests.  They use the imported   simple_testing (st)   module.
    # Each test is a SimpleTestCase with 3 arguments:
    #   -- the function to test,
    #   -- a list containing the argument(s) to send to the function,
    #   -- the correct returned value.
    # For example, the first test below will call
    #   practice_problem3e((12, 33, 18, 9, 13, 3, 9, 20, 19, 20))
    # and compare the returned value against 161 (the correct answer).
    # ------------------------------------------------------------------
    tests = [st.SimpleTestCase(practice_problem3e,
                               [(12, 33, 18, 9, 13, 3, 99, 20, 19, 20)],
                               161),
             st.SimpleTestCase(practice_problem3e,
                               [(3, 12, 10, 8, 8, 9, 8, 11)],
                               29),
             st.SimpleTestCase(practice_problem3e,
                               [(-9999999999, 8888888888)],
                               - 9999999999),
             st.SimpleTestCase(practice_problem3e,
                               [(8888888888, -9999999999)],
                               8888888888),
             st.SimpleTestCase(practice_problem3e,
                               [(-77, 20000, -33, 40000, -55,
                                 60000, -11)],
                               - 176),
             st.SimpleTestCase(practice_problem3e,
                               [()],
                               0),
             st.SimpleTestCase(practice_problem3e,
                               [[]],
                               0),
             st.SimpleTestCase(practice_problem3e,
                               [[8]],
                               8),
             st.SimpleTestCase(practice_problem3e,
                               [(-77, 8)],
                                 - 77),
             st.SimpleTestCase(practice_problem3e,
                               [(-77, 8, 77)],
                                 0),
             st.SimpleTestCase(practice_problem3e,
                               [(-77, 8, 78)],
                                 1),
             st.SimpleTestCase(practice_problem3e,
                               [(-77, 8, 78, 100)],
                                 1),
             ]

    # ------------------------------------------------------------------
    # Run the 5 tests in the   tests   list constructed above.
    # ------------------------------------------------------------------
    st.SimpleTestCase.run_tests('practice_problem3e', tests)


def practice_problem3e(sequence):
    """
    What comes in:
      A sequence of numbers.
    What goes out:
      Returns the sum of the numbers at EVEN INDICES of the sequence.
    Side effects: None.
    Examples:
      If the sequence is:
          (12, 33, 18, 9, 13, 3, 99, 20, 19, 20)
      then this function returns
           12 + 18 + 13 + 99 + 19, which is 161.
    Type hints:
      :type sequence: list(float)    or tuple(float)
    """
    # ------------------------------------------------------------------
    # DONE: 7. Implement and test this function.
    #     The testing code is already written for you (above).
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      5
    #    TIME ESTIMATE:   8 minutes.
    ####################################################################

    sum = 0
    for k in range(len(sequence)):
        if k%2 == 0:
            sum = sum + sequence[k]
    return sum





# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
