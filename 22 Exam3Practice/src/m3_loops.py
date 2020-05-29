"""
PRACTICE Test 3.

This problem provides practice at:
  ***  FOR and WHILE loops.  ***

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


def main():
    """ Calls the   TEST   functions in this module. """
    run_test_practice_problem3()

# ----------------------------------------------------------------------
# Students: Some of the testing code below uses SimpleTestCase objects,
#           from the imported   simple_testing (st)   module.
# ----------------------------------------------------------------------


def run_test_practice_problem3():
    """ Tests the   practice_problem3  function. """
    ####################################################################
    # DONE: 2. Implement this TEST function.
    #   It TESTS the  practice_problem3  function defined below.
    #   Include at least ** 2 ** ADDITIONAL tests beyond those we wrote.
    #
    #   Try to choose tests that might expose errors in your code!
    #
    #   As usual, include both EXPECTED and ACTUAL results in your tests
    #   and compute the latter BY HAND (not by running your program).
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      3
    #    TIME ESTIMATE:   10 minutes.
    ####################################################################

    # ------------------------------------------------------------------
    # 13 tests, plus a 14th after these.
    # They use the imported   simple_testing (st)   module.
    # Each test is a SimpleTestCase with 3 arguments:
    #   -- the function to test,
    #   -- a list containing the argument(s) to send to the function,
    #   -- the correct returned value.
    # For example, the first test below will call
    #   practice_problem3(-2, 2, 1.3)
    # and compare the returned value against [1, 7] (the correct answer).
    # ------------------------------------------------------------------
    tests = [st.SimpleTestCase(practice_problem3,
                               [-2, 2, 1.3],
                               [1, 7]),
             st.SimpleTestCase(practice_problem3,
                               [-5, 3, 0.25],
                               [-5, 0, 1]),
             st.SimpleTestCase(practice_problem3,
                               [-5, 4, 0.25],
                               [-5, 0, 1, 2]),
             st.SimpleTestCase(practice_problem3,
                               [-5, 5, 0.25],
                               [-5, 0, 1, 2, 6]),
             st.SimpleTestCase(practice_problem3,
                               [-5, 6, 0.25],
                               [-5, 0, 1, 2, 6, 7]),
             st.SimpleTestCase(practice_problem3,
                               [-5, 7, 0.25],
                               [-5, 0, 1, 2, 6, 7, 8]),
             st.SimpleTestCase(practice_problem3,
                               [-3, 3, -1.0],
                               [-1, 0, 1]),
             st.SimpleTestCase(practice_problem3,
                               [-3, 4, -1.0],
                               [-1, 0, 1, 2]),
             st.SimpleTestCase(practice_problem3,
                               [-3, 5, -1.0],
                               [-1, 0, 1, 2, 3]),
             st.SimpleTestCase(practice_problem3,
                               [-3, 6, -1.0],
                               [-1, 0, 1, 2, 3, 5]),
             st.SimpleTestCase(practice_problem3,
                               [30, 0, -1000],
                               []),
             st.SimpleTestCase(practice_problem3,
                               [100, 5, 1.414],
                               [139, 183, 516, 560, 849]),
             st.SimpleTestCase(practice_problem3,
                               [0, 1, 1.414213562373],
                               [286602]),
             ]
    # 14th test:
    big_list = []
    for k in range(888, 1888):
        big_list.append(k)
    tests.append(st.SimpleTestCase(practice_problem3,
                                   [888, 1000,
                                    - math.sqrt(2) - 0.00000000001],
                                   big_list))

    # ------------------------------------------------------------------
    # Run the 14 tests in the   tests   list constructed above.
    # ------------------------------------------------------------------
    st.SimpleTestCase.run_tests('practice_problem3', tests)

    ####################################################################
    # TO DO 2 continued:  More tests:
    #      YOU add at least **   2   ** additional tests here.
    #
    # You can use the   SimpleTestCase  class as above, or use
    # the ordinary   expected/actual   way, your choice.
    #
    # SUGGESTION: Ask an assistant to CHECK your tests to confirm
    #             that they are adequate tests!
    ####################################################################

    start = -4
    n = 2
    threshold = 0.1

    expected = [-4, 0]
    actual = practice_problem3(start, n, threshold)

    print()
    print('My test 1:')
    print('expected:    ', expected)
    print('actual:      ', actual)




    start = 0
    n = 3
    threshold = 0.25

    expected = [0, 1, 2]
    actual = practice_problem3(start, n, threshold)

    print()
    print('My test 2:')
    print('expected:    ', expected)
    print('actual:      ', actual)


def practice_problem3(start, n, threshold):
    """
    What comes in:
      -- An integer:  start
      -- An nonnegative integer:  n
      -- A number:  threshold
    What goes out:  Returns a list of the first n integers,
      starting at start, for which the sum of the integer's
      sine and cosine is bigger than the given threshold.
    Side effects: None.
    Examples:
       practice_problem3(-2, 2, 1.3)  returns  [1, 7]
    as you can see if you work through this example using
    the numbers presented below. (Do so!)

    For these examples, the following (and more) numbers
    (each is rounded to 2 decimal places for the sake of brevity)
    are relevant:
        -5:  sin =  0.96,  cos =  0.28,  sum =  1.24
        -4:  sin =  0.76,  cos = -0.65,  sum =  0.10
        -3:  sin = -0.14,  cos = -0.99,  sum = -1.13
        -2:  sin = -0.91,  cos = -0.42,  sum = -1.33
        -1:  sin = -0.84,  cos =  0.54,  sum = -0.30
         0:  sin =  0.00,  cos =  1.00,  sum =  1.00
         1:  sin =  0.84,  cos =  0.54,  sum =  1.38
         2:  sin =  0.91,  cos = -0.42,  sum =  0.49
         3:  sin =  0.14,  cos = -0.99,  sum = -0.85
         4:  sin = -0.76,  cos = -0.65,  sum = -1.41
         5:  sin = -0.96,  cos =  0.28,  sum = -0.68
         6:  sin = -0.28,  cos =  0.96,  sum =  0.68
         7:  sin =  0.66,  cos =  0.75,  sum =  1.41
         8:  sin =  0.99,  cos = -0.15,  sum =  0.84
         9:  sin =  0.41,  cos = -0.91,  sum = -0.50
        10:  sin = -0.54,  cos = -0.84,  sum = -1.38
        11:  sin = -1.00,  cos =  0.00,  sum = -1.00
        12:  sin = -0.54,  cos =  0.84,  sum =  0.31
        13:  sin =  0.42,  cos =  0.91,  sum =  1.33

    So if start is -5 and threshold is 0.25 and:
       -- n is 3, then this function returns [-5, 0, 1]
             because sin(-5) + cos(-5) IS > 0.25 and
                     sin(-4) + cos(-4) is NOT > 0.25 and
                     sin(-3) + cos(-3) is NOT > 0.25 and
                     sin(-2) + cos(-2) is NOT > 0.25 and
                     sin(-1) + cos(-1) is NOT > 0.25 and
                     sin(0) + cos(0) IS > 0.25 and
                     sin(1) + cos(1) IS > 0.25 and
             and that makes the required  3  such numbers.
       -- n is 4, then this function returns [-5, 0, 1, 2]
       -- n is 5, then this function returns [-5, 0, 1, 2, 6]
       -- n is 6, then this function returns [-5, 0, 1, 2, 6, 7]
       -- n is 7, then this function returns [-5, 0, 1, 2, 6, 7, 8]

    while if start is -3 and the threshold is -1.0 and:
       -- n is 3, then this function returns [-1, 0, 1]
       -- n is 4, then this function returns [-1, 0, 1, 2]
       -- n is 5, then this function returns [-1, 0, 1, 2, 3]
       -- n is 6, then this function returns [-1, 0, 1, 2, 3, 5]

    and if n is 0 (regardless of what start is),
       this function returns []

    and if threshold is more than the square root of 2,
       this function returns (regardless of what start and n are):
          [start, start + 1, start + 2, ... start + n - 1].

    Type hints:
      :type start: int
      :type n:     int
      :type threshold: float
    """
    ####################################################################
    # DONE: 3. Implement and test this function.
    #          Some tests are already written for you (above),
    #          but you are required to write ADDITIONAL tests (above).
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      5
    #    TIME ESTIMATE:   < 15 minutes.
    ####################################################################

    list = []
    k = start
    while True:
        if len(list) >= n:
            break

        if (math.sin(k) + math.cos(k)) > threshold:
            list = list + [k]
        k = k +1

    return list



# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
