"""
PRACTICE Test 2, practice_problem 2.

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


def main():
    """ Calls the   TEST   functions in this module. """
    run_test_practice_problem2a()
    run_test_practice_problem2b()


# ----------------------------------------------------------------------
# Students: Some of the testing code below uses SimpleTestCase objects,
#           from the imported   simple_testing (st)   module.
# ----------------------------------------------------------------------

def run_test_practice_problem2a():
    """ Tests the   practice_problem2a  function. """
    # ------------------------------------------------------------------
    # DONE: 2. Implement this TEST function.
    #   It TESTS the  practice_problem2a  function defined below.
    #   Include at least **   4 reasonable   ** tests.
    #
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      3
    #    TIME ESTIMATE:   5 minutes.
    ####################################################################
    print()
    print('--------------------------------------------------')
    print('Testing the   practice_problem2a   function:')
    print('--------------------------------------------------')

    sequence = [2, 10, 5, -20, 8]
    delta = 6
    expected = [8, 16, 11, -14, 14]
    actual = practice_problem2a(sequence, delta)
    print()
    print('--> Test 1')
    print('Expected:    ', expected)
    print('Actual:      ', actual)

    sequence = [2, 10, 5, -20, 8]
    delta = 2
    expected = [4, 12, 7, -18, 10]
    actual = practice_problem2a(sequence, delta)
    print()
    print('--> Test 2')
    print('Expected:    ', expected)
    print('Actual:      ', actual)

    sequence = [1, 2, 3, 4, 5]
    delta = 1
    expected = [2, 3, 4, 5, 6]
    actual = practice_problem2a(sequence, delta)
    print()
    print('--> Test 3')
    print('Expected:    ', expected)
    print('Actual:      ', actual)

    sequence = [2, 10, 5, -20, 8]
    delta = 10
    expected = [12, 20, 15, -10, 18]
    actual = practice_problem2a(sequence, delta)
    print()
    print('--> Test 4')
    print('Expected:    ', expected)
    print('Actual:      ', actual)


def practice_problem2a(sequence, delta):
    """
    What comes in:
      -- A sequence of integers, e.g. ([2, 10, 5, -20, 8])
      -- A number  delta
    What goes out:
      -- Returns a new list that is the same as the given list,
           but with each number in the list having had the given
             delta
           added to it (see example below)
    Side effects: None.
    Example:
       Given the list  [2, 10, 5, -20, 8]  and the number  6,
         this problem returns  [8, 16, 11, -14, 14]
    Type hints:
      :type sequence: [int]
      :type delta:    int
    """
    ####################################################################
    # DONE: 3. Implement and test this function.
    #     The testing code is already written for you (above).
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      5
    #    TIME ESTIMATE:   5 minutes.
    ####################################################################

    for k in range(len(sequence)):
        sequence[k] = sequence[k] + delta

    return sequence


def run_test_practice_problem2b():
    """ Tests the   practice_problem2b  function. """
    # ------------------------------------------------------------------
    # 4 tests, plus a 5th after these.
    # They use the imported   simple_testing (st)   module.
    # Each test is a SimpleTestCase with 3 arguments:
    #   -- the function to test,
    #   -- a list containing the argument(s) to send to the function,
    #   -- the correct returned value.
    # For example, the first test below will call
    #   practice_problem2b(('hello', 'Bye', 'ok joe'))
    # and compare the returned value against 'hBo' (the correct answer).
    # ------------------------------------------------------------------
    tests = [st.SimpleTestCase(practice_problem2b,
                               [('hello', 'Bye', 'ok joe')],
                               'hBo'),
             st.SimpleTestCase(practice_problem2b,
                               [('Alice', 'Bob', 'Carson', 'Devi')],
                               'ABCD'),
             st.SimpleTestCase(practice_problem2b,
                               [('', 'tricky', '', 'one, no?', '!')],
                               'to!'),
             st.SimpleTestCase(practice_problem2b,
                               [('my very long string', 'ok', 'mmmm')],
                               'mom'),
             ]
    jokes = """
    Q: What is it called when a cat wins a dog show?
    A: A CAT-HAS-TROPHY!

    Q: What do you call a pile of kittens?
    A: a meowntain

    Q: Why don't cats like online shopping?
    A: They prefer a cat-alogue.

    Q: What did the cat say when he lost all his money?
    A: I'm paw!

    Q: Did you hear about the cat who swallowed a ball of yarn?
    A: She had a litter of mittens.

    Q: What do you call a lion who has eaten your mother's sister?
    A: An aunt-eater!

    Q. How do you know when your cat's done cleaning herself?
    A. She's smoking a cigarette.

    source: http://www.jokes4us.com/animaljokes/catjokes.html
    """
    # 5th test: Split  jokes  at spaces to get a list of strings.
    sequence = jokes.split()
    answer = ('QWiicwacwadsAACQWdycapokAamQWdclosAT' +
              'pacQWdtcswhlahmAIpQDyhatcwsaboyAShalom' +
              'QWdycalwheymsAAaQHdykwycdchASsacsh')
    tests.append(st.SimpleTestCase(practice_problem2b,
                                   [sequence],
                                   answer))

    # ------------------------------------------------------------------
    # Run the 5 tests in the   tests   list constructed above.
    # ------------------------------------------------------------------
    st.SimpleTestCase.run_tests('practice_problem2b', tests)


def practice_problem2b(sequence):
    """
    What comes in:
      -- A sequence of strings, e.g. ('hello', 'Bye', 'ok joe')
    What goes out:
      -- Returns the string that contains the first letter in
           each of the strings in the given sequence,
           in the order in which they appear in the sequence.
           (So 'hBo' for the example sequence above).
    Side effects: None.
    Examples:
       Given ['hello', 'Bye', 'ok joe']          returns 'hBo'.
       Given ('Alice, 'Bob', 'Carson', 'Devi')   returns 'ABCD'.
       Given ('', 'tricky', '', 'one, no?', '!') returns 'to!'
       Given [] returns ''
       Given ('my very long string', 'ok', 'mmmm') returns 'mom'
    Type hints:
      :type sequence [str]
    """
    ####################################################################
    # DONE: 4. Implement and test this function.
    #     The testing code is already written for you (above).
    ####################################################################
    # DIFFICULTY AND TIME RATINGS (see top of this file for explanation)
    #    DIFFICULTY:      7
    #    TIME ESTIMATE:   10 minutes.
    ####################################################################
    first_letters = ''
    for k in range(len(sequence)):
        s = sequence[k]
        if s != '':
            first_letters = first_letters + s[0]

    return first_letters


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
