"""
PRACTICE Test 3.

This problem provides practice at:
  ***  MUTATING  and  RETURNING-NEW  LISTS.  ***

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
    run_test_doubler()


def run_test_doubler():
    """ Tests the    doubler    function. """
    # ------------------------------------------------------------------
    # DONE: 2. Implement this TEST function.
    #   It TESTS the  doubler  function defined below.
    #   Include at least ** 1 ** ADDITIONAL test beyond those we wrote.
    #
    #   Try to choose tests that might expose errors in your code!
    #
    #   As usual, include both EXPECTED and ACTUAL results in your tests
    #   and compute the latter BY HAND (not by running your program).
    # ------------------------------------------------------------------
    # ------------------------------------------------------------------
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      3
    #    TIME ESTIMATE:   10 minutes.
    # ------------------------------------------------------------------

    print()
    print('--------------------------------------------------')
    print('Testing the   doubler   function:')
    print('--------------------------------------------------')

    # Test 1:
    arg1 = [10, -3, 20, 4]
    arg2 = [5, 0, 8]
    correct_arg1_after = [20, -6, 40, 8]
    correct_arg2_after = [5, 0, 8]
    expected = [10, 0, 16]

    print()
    print('BEFORE the function call:')
    print('  Argument 1 is:', arg1)
    print('  Argument 2 is:', arg2)

    answer = doubler(arg1, arg2)

    print('AFTER the function call:')
    print('  Argument 1 is:       ', arg1)
    print('  Argument 1 should be:', correct_arg1_after)
    print('  Argument 2 is:       ', arg2)
    print('  Argument 2 should be:', correct_arg2_after)
    print('The returned value is:       ', answer)
    print('The returned value should be:', expected)

    # ------------------------------------------------------------------
    # TO DO 2 (continued): Add your ADDITIONAL test(s) here:
    # ------------------------------------------------------------------


    arg1 = [3, -4, 5, 6]
    arg2 = [0, 1, 2, -3, -4, -5, 6]
    correct_arg1_after = [6, -8, 10, 12]
    correct_arg2_after = [0, 1, 2, -3, -4, -5, 6]
    expected = [0, 2, 4, -6, -8, -10, 12]

    print()
    print('BEFORE the function call:')
    print('  Argument 1 is:', arg1)
    print('  Argument 2 is:', arg2)

    answer = doubler(arg1, arg2)

    print('AFTER the function call:')
    print('  Argument 1 is:       ', arg1)
    print('  Argument 1 should be:', correct_arg1_after)
    print('  Argument 2 is:       ', arg2)
    print('  Argument 2 should be:', correct_arg2_after)
    print('The returned value is:       ', answer)
    print('The returned value should be:', expected)



def doubler(list1, list2):
    """
    Both arguments are lists of integers.  This function:
      -- MUTATEs the first list by doubling each number in the list
    and
      -- RETURNs a new list that is the same as list2 but with each
           number in the list doubled.

    For example, if the two arguments are:
       [10, -3, 20, 4]  and  [5, 0, 8]
    then this method MUTATEs the first argument to [20, -6, 40, 8]
    and RETURNs the list [10, 0, 16]

    Preconditions:
        :type list1: list of int
        :type list2: list of int
    """
    # ------------------------------------------------------------------
    # DONE: 3. Implement and test this function.
    #   Note that you should write its TEST function first (above).
    # ------------------------------------------------------------------
    # ------------------------------------------------------------------
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      4
    #    TIME ESTIMATE:   5 minutes.
    # ------------------------------------------------------------------

    for k in range(len(list1)):
        list1[k] = 2 * list1[k]

    list = []
    for j in range(len(list2)):
        list = list + [2*list2[j]]

    return list

# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
