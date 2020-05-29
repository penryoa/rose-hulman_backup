"""
This project demonstrates NESTED LOOPS (i.e., loops within loops)
in the context of SEQUENCES OF SUB-SEQUENCES.

Authors: David Mutchler, Valerie Galluzzi, Mark Hays, Amanda Stouder,
         their colleagues and Olivia Penry.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.


def main():
    """ Calls the other functions to test them. """
    run_test_multiply_numbers()
    run_test_sum_numbers()
    run_test_print_characters()
    run_test_print_characters_slanted()


def run_test_multiply_numbers():
    """ Tests the   multiply_numbers   function. """
    # ------------------------------------------------------------------
    # We have supplied tests for you. No additional tests are required,
    # although you are welcome to supply more tests if you choose.
    # ------------------------------------------------------------------
    print()
    print('--------------------------------------------------')
    print('Testing the   multiply_numbers   function:')
    print('The   multiply_numbers   function MUTATES each SUB-sequence')
    print('by multiplying:')
    print('  -- the 1st sub-sequence by 1,')
    print('  -- the 2nd by 2,')
    print('  -- the 3rd by 3,')
    print('     ...')
    print('-----------------------------------------------------------')

    # Test 1:
    tuple_of_lists = ([4, 0, 100],
                      [1, 2, 3],
                      [100, 100, 20, 30])
    tuple_of_lists_after_multipy = ([4, 0, 100],
                                    [2, 4, 6],
                                    [300, 300, 60, 90])
    print()
    print('The sequences BEFORE and AFTER the call, respectively, are:')
    print('   ', tuple_of_lists)

    multiply_numbers(tuple_of_lists)

    print('   ', tuple_of_lists)
    print('Look at the above.  The second should be:')
    print('   ', tuple_of_lists_after_multipy)
    if tuple_of_lists == tuple_of_lists_after_multipy:
        print('Your code  PASSED  Test 1.')
    else:
        print('Your code  FAILED  Test 1.')

    # Test 2:
    tuple_of_lists = (['the ', 'rain '],
                      ['in spain ', 'falls ', 'mainly on the '],
                      ['plain.'])
    tuple_of_lists_after_multipy = (['the ', 'rain '],
                                    ['in spain in spain ',
                                     'falls falls ',
                                     'mainly on the mainly on the '],
                                    ['plain.plain.plain.'])
    print()
    print('The sequences BEFORE and AFTER the call, respectively, are:')
    print('   ', tuple_of_lists)

    multiply_numbers(tuple_of_lists)

    print('   ', tuple_of_lists)
    print('Look at the above.  The second should be:')
    print('   ', tuple_of_lists_after_multipy)
    if tuple_of_lists == tuple_of_lists_after_multipy:
        print('Your code  PASSED  Test 2.')
    else:
        print('Your code  FAILED  Test 2.')

    # Test 3:
    tuple_of_lists = ([10, 5],
                      [10, 5, 5, 8, 20],
                      ['a', 'b', 'c'],
                      ['d'],
                      ['e', 'f'])
    tuple_of_lists_after_multipy = ([10, 5],
                                    [20, 10, 10, 16, 40],
                                    ['aaa', 'bbb', 'ccc'],
                                    ['dddd'],
                                    ['eeeee', 'fffff'])
    print()
    print('The sequences BEFORE and AFTER the call, respectively, are:')
    print('   ', tuple_of_lists)

    multiply_numbers(tuple_of_lists)

    print('   ', tuple_of_lists)
    print('Look at the above.  The second should be:')
    print('   ', tuple_of_lists_after_multipy)
    if tuple_of_lists == tuple_of_lists_after_multipy:
        print('Your code  PASSED  Test 3.')
    else:
        print('Your code  FAILED  Test 3.')


def multiply_numbers(sequence_of_lists):
    """
    In the given sequence of lists,
      -- multiplies each element of the first list by 1,
      -- multiplies each element of the second list by 2,
      -- multiplies each element of the third list by 3,
      -- and so forth.
    MUTATES (changes the "insides" of) the given lists
    (but NOT the given sequence).  See the test cases for examples.

    Preconditions: The argument is a sequence of lists,
       and the elements of the lists can be multiplied by an integer.
       [FYI: This 'can be multiplied ...' is an example of DUCK TYPING.]
    """
    # ------------------------------------------------------------------
    # DONE: 2. Implement and test this function.
    #  ** READ THE TESTS that have been written for you (ABOVE).
    #  ** ASK QUESTIONS if you do not understand the TESTS (ABOVE).
    #
    # NOTE: This is a classic SEQUENCE of SEQUENCES:
    #        -- Each loop is simply the pattern you have seen many times.
    #        -- But INSIDE the OUTER loop and BEFORE the INNER loop,
    #             you can 'extract' the current (OUTER loop) SUB-list
    #             to loop through it in the INNER loop.
    #        -- See   m2e_nested_loops_in_sequences   as needed.
    # ------------------------------------------------------------------


    for j in range(len(sequence_of_lists)):

        sublist = sequence_of_lists[j]

        for k in range(len(sublist)):
            sublist[k] = sublist[k] * (j + 1)





def run_test_sum_numbers():
    """ Tests the    sum_numbers    function. """
    # ------------------------------------------------------------------
    # DONE: 3. Implement this TEST function.
    #   It TESTS the  sum_numbers  function defined below.
    #   Include at least **   3   ** tests (we wrote two for you).
    # ------------------------------------------------------------------
    print()
    print('-------------------------------------')
    print('Testing the   SUM_NUMBERS   function:')
    print('-------------------------------------')

    # Test 1:
    expected = 38
    answer = sum_numbers([(3, 1, 4), (10, 10), [1, 2, 3, 4]])
    print('Expected and actual are:', expected, answer)

    # Test 2:
    expected = 5
    answer = sum_numbers(([], [5], []))
    print('Expected and actual are:', expected, answer)

    # TO DO 3 (continued): Add your ADDITIONAL test(s) here:
    expected = 9
    answer = sum_numbers(([2,4], [2], [1]))
    print('Expected and actual are:', expected, answer)

def sum_numbers(seq_seq):
    """
    Returns the sum of the numbers in the given sequence
    of subsequences.  For example, if the given argument is:
        [(3, 1, 4), (10, 10), [1, 2, 3, 4]]
    then this function returns    38
    (which is 3 + 1 + 4 + 10 + 10 + 1 + 2 + 3 + 4).
    Preconditions:  the given argument is a sequences of sequences,
                    and each item in the subsequences is a number.
    """
    # ------------------------------------------------------------------
    # DONE: 4. Implement and test this function.
    #   Note that you should write its TEST function first (above).
    # ------------------------------------------------------------------
    sum = 0
    for k in range(len(seq_seq)):
        sublist = seq_seq[k]
        for j in range(len(sublist)):
            sum = sum + sublist[j]

    return sum



def run_test_print_characters():
    """ Tests the    print_characters    function. """
    # ------------------------------------------------------------------
    # We have supplied tests for you. No additional tests are required,
    # although you are welcome to supply more tests if you choose.
    # ------------------------------------------------------------------
    print()
    print('------------------------------------------')
    print('Testing the   PRINT_CHARACTERS   function:')
    print('------------------------------------------')

    # Test 1:
    print()
    print('The following should be:')
    print('  hibyea tie!')
    print('but printed in a COLUMN, one character per line:')
    print_characters(['hi', 'bye', 'a tie!'])

    # Test 2:
    print()
    print('The following should be:')
    print('  9876abc67 89')
    print('but printed in a COLUMN, one character per line:')
    print_characters(['9876', 'abc', '', '67 89'])


def print_characters(sequence_of_strings):
    """
    Prints all the characters in the sequence of strings,
    but each character on ITS OWN LINE.  For example,
    if the given argument is ['hi', 'bye', 'a tie!'],
    then this function prints:
       h
       i
       b
       y
       e
       a

       t
       i
       e
       !
    Precondition:  the given argument is a sequence of strings.
    """
    # ------------------------------------------------------------------
    # DONE: 5. Implement and test this function.
    #  ** READ THE TESTS that have been written for you (ABOVE).
    #  ** ASK QUESTIONS if you do not understand the TESTS (ABOVE).
    # ------------------------------------------------------------------

    for k in range(len(sequence_of_strings)):
        sublist = sequence_of_strings[k]
        for j in range(len(sublist)):
            print(sublist[j])



def run_test_print_characters_slanted():
    """ Tests the    print_characters_slanted    function. """
    # ------------------------------------------------------------------
    # We have supplied tests for you. No additional tests are required,
    # although you are welcome to supply more tests if you choose.
    # ------------------------------------------------------------------
    print()
    print('--------------------------------------------------')
    print('Testing the   PRINT_CHARACTERS_SLANTED   function:')
    print('--------------------------------------------------')

    # Test 1:
    print()
    print('The following should be:')
    print('  h  *i  b  *y  **e  a  *_  **t  ***i  ****e  *****!')
    print('but printed in a COLUMN with line breaks at the SPACES above')
    print('and with SPACES replacing the *s:')
    print_characters_slanted(['hi', 'bye', 'a_tie!'])

    # Test 2:
    print()
    print('The following should be:')
    print('  a  *b  **c  ***d  ****e  x  y  z  *z  **z')
    print('but printed in a COLUMN with line breaks at the SPACES above')
    print('and with SPACES replacing the *s:')
    print_characters_slanted(['abcde', 'x', 'y', 'zzz'])


def print_characters_slanted(sequence_of_strings):
    """
    Same as the previous problem, but each string 'slants'.
    For example, if the given argument is ['hi', 'bye', 'a_tie!'],
    then this function prints:
       h
        i
       b
        y
         e
       a
        _
         t
          i
           e
            !
    Precondition:  the given argument is a sequence of strings.
    """
    # ------------------------------------------------------------------
    # DONE: 6. Implement and test this function.
    #  ** READ THE TESTS that have been written for you (ABOVE).
    #  ** ASK QUESTIONS if you do not understand the TESTS (ABOVE).
    #
    # ** HINT: ** Consider using string multiplication for the spaces
    #             and string addition to stitch the spaces to the character.
    # ------------------------------------------------------------------


    for k in range(len(sequence_of_strings)):
        sublist = sequence_of_strings[k]
        for j in range(len(sublist)):
            print(' '*j, sublist[j])



# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
