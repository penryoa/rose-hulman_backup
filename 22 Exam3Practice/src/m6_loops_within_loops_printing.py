"""
PRACTICE Test 3.

This problem provides practice at:
  ***  LOOPS WITHIN LOOPS in PRINTING-TO-CONSOLE problems.  ***

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


def main():
    """ Calls the   TEST   functions in this module. """
    run_test_shape()


def run_test_shape():
    """ Tests the    shape    function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   SHAPE   function:')
    print('--------------------------------------------------')

    print()
    print('Test 1 of shape: r=7')
    shape(7)

    print()
    print('Test 2 of shape: r=4')
    shape(4)

    print()
    print('Test 3 of shape: r=2')
    shape(2)


def shape(r):
    """
    Prints a shape with r rows that looks like this example where r=7:
    +++++++!7654321
     ++++++!654321-
      +++++!54321--
       ++++!4321---
        +++!321----
         ++!21-----
          +!1------

    Another example, where r=4:
    ++++!4321
     +++!321-
      ++!21--
       +!1---

    Preconditions:  r is a positive number.
    For purposes of "lining up", assume r is a single digit.
    """
    # ------------------------------------------------------------------
    # DONE: 2. Implement and test this function.
    #          Some tests are already written for you (above).
    #
    ####################################################################
    # IMPLEMENTATION RESTRICTION:
    #   You may NOT use string multiplication in this problem.
    ####################################################################
    # ------------------------------------------------------------------
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      7
    #    TIME ESTIMATE:  15 minutes.
    # ------------------------------------------------------------------


    for k in range(r):
        for m in range(k):
            print(' ', end = '')
        for h in range(r-k):
            print('+', end = '')
        print('!', end = '')
        for o in range(r-k):
            print(r-o-k, end = '')
        for p in range(k):
            print('-', end = '')

        print()



# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
