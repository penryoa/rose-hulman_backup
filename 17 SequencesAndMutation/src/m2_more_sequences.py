"""
This module lets you practice various patterns
for ITERATING through SEQUENCES, including selections from:
  -- Beginning to end
  -- Other ranges (e.g., backwards and every-3rd-item)
  -- The COUNT/SUM/etc pattern
  -- The FIND pattern (via LINEAR SEARCH)
  -- The MAX/MIN pattern
  -- Looking two places in the sequence at once
  -- Looking at two sequences in parallel

Authors: David Mutchler, Valerie Galluzzi, Mark Hays, Amanda Stouder,
         their colleagues and Olivia Penry.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.


def main():
    """ Calls the   TEST   functions in this module. """
    run_test_shortest_string()
    run_test_index_of_largest_number()
    run_test_number_of_stutters()
    run_test_is_palindrome()
    run_test_count_same()


# ----------------------------------------------------------------------
# Some problems iterate (loop) through the sequence to find the LARGEST
# (or SMALLEST) item in the sequence, returning its INDEX (or possibly
# the item itself), as in the following problems:
# ----------------------------------------------------------------------
def run_test_shortest_string():
    """ Tests the   shortest_string   function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   shortest_string   function:')
    print('--------------------------------------------------')

    sequence1 = ('all', 'we', 'are', 'saying',
                 'is', 'give', 'peace', 'a', 'chance')
    sequence2 = ('all', 'we', 'are', 'saying',
                 'is', 'give', 'peace', 'a chance')
    sequence3 = ('all we', 'are saying',
                 'is', 'give', 'peace', 'a chance')
    sequence4 = ('all we are saying is give peace a chance',)
    sequence5 = ('a', '', 'a')

    expected = 'a'
    answer = shortest_string(sequence1)
    print('Expected and actual are:', expected, answer)
    if expected != answer:
        print('  Your answer is WRONG.')

    expected = 'we'
    answer = shortest_string(sequence2)
    print('Expected and actual are:', expected, answer)
    if expected != answer:
        print('  Your answer is WRONG.')

    expected = 'is'
    answer = shortest_string(sequence3)
    print('Expected and actual are:', expected, answer)
    if expected != answer:
        print('  Your answer is WRONG.')

    expected = 'all we are saying is give peace a chance'
    answer = shortest_string(sequence4)
    print('Expected is:', expected)
    print('Actual is:  ', answer)
    if expected != answer:
        print('  Your answer is WRONG.')

    expected = ''
    answer = shortest_string(sequence5)
    print('Expected and actual are:', expected, answer)
    print('The expected and actual should both be the empty string.')
    if expected != answer:
        print('  Your answer is WRONG.')


def shortest_string(strings):
    """
    What comes in:
      -- a non-empty sequence of strings
    What goes out: Returns the shortest string in the given sequence
    of strings.  If there is a tie for shortest string, returns the one
    (among the ties) whose index is smallest.
    Side effects: None.
    Examples:
      If the argument is:
        ['all', 'we',  'are saying', 'is', 'give', 'peace', 'a chance']
      then this function returns  'we'

      If the argument is:
        ['all we',  'are saying', 'is give', 'peace', 'a chance']
      then this function returns  'peace'

      If the argument is:
        ['all we are saying', 'is give', 'peace a chance']
      then this function returns  'is give'

      If the argument is ['abc'], then this function returns  'abc'.
    Type hints:
      :type strings: list[str]   or tuple(str)
    """
    # ------------------------------------------------------------------
    # DONE: 2. Implement and test this function.
    #     The testing code is already written for you (above).
    # ------------------------------------------------------------------
    short = strings[0]

    for k in range(len(strings)-1):
        if len(strings[k+1]) < len(short):
            short = strings[k+1]

    return short






def run_test_index_of_largest_number():
    """ Tests the   index_of_largest_number   function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   index_of_largest_number   function:')
    print('--------------------------------------------------')

    expected = 2
    answer = index_of_largest_number([90, 0, 100, -5, 100, -10, 15], 3)
    print('Expected and actual are:', expected, answer)

    expected = 0
    answer = index_of_largest_number([90, 0, 95, -5, 95, -10, 15], 2)
    print('Expected and actual are:', expected, answer)

    expected = 2
    answer = index_of_largest_number([90, 0, 93, -5, 93, -10, 15], 7)
    print('Expected and actual are:', expected, answer)

    expected = 5
    answer = index_of_largest_number([5, 30, 10, 15, 1, 60], 6)
    print('Expected and actual are:', expected, answer)

    expected = 0
    answer = index_of_largest_number([-5, 30, 10, 15, 1, 60], 1)
    print('Expected and actual are:', expected, answer)

    expected = 1
    answer = index_of_largest_number([-500000000000000000000000000000,
                                      - 400000000000000000000000000000],
                                     2)
    print('Expected and actual are:', expected, answer)

    expected = 0
    answer = index_of_largest_number([-40000000000000000000000000000000000,
                                      - 50000000000000000000000000000000000],
                                     2)
    print('Expected and actual are:', expected, answer)


def index_of_largest_number(numbers, n):
    """
    What comes in:
      -- a sequence of numbers
      -- a positive integer  n  that is less than or equal to
           the length of the given sequence
    What goes out:  INDEX of the largest number in the first n numbers
      of the given sequence of numbers.  If there is a tie for largest
      number, returns the smallest of the indices of the tied numbers.
    Side effects: None.
    Examples:
      If the first argument is:
         [90, 0, 100, 200, -5, 100, -10, 200, 15]
      and the second argument  n  is 3,
      then this function returns  2  (because 100, at index 2,
      is the largest of the first 3 numbers in the list).

      Another example: for the same list as above, but with n = 2,
      this function returns  0  (because 90, at index 0,
      is the largest of the first 2 numbers in the list).

      Yet another example:  For the same list as above, but with n = 9,
      this function returns  3  (because 200, at indices 3 and 7,
      is the largest of the first 9 numbers in the list,
      and we break the tie in favor of the smaller index).

    Type hints:
      :type numbers: list[float]   or tuple[float]
      :type n:       int

    """
    # ------------------------------------------------------------------
    # DONE: 3. Implement and test this function.
    #     The testing code is already written for you (above).
    # ------------------------------------------------------------------

    largest_index = 0
    largest = numbers[0]

    for k in range(1, n):
        if numbers[k] > largest:
            largest_index = k
            largest = numbers[k]


    return largest_index



# ----------------------------------------------------------------------
# Some problems iterate (loop) through the sequence accessing TWO
# (or more) places in the sequence AT THE SAME ITERATION, like these:
# ----------------------------------------------------------------------
def run_test_number_of_stutters():
    """ Tests the   number_of_stutters   function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   number_of_stutters   function:')
    print('--------------------------------------------------')

    expected = 2
    answer = number_of_stutters('xhhbrrs')
    print('Expected and actual are:', expected, answer)

    expected = 3
    answer = number_of_stutters('xxxx')
    print('Expected and actual are:', expected, answer)

    expected = 0
    answer = number_of_stutters('xaxaxa')
    print('Expected and actual are:', expected, answer)

    expected = 7
    answer = number_of_stutters('xxx yyy xxxx')
    print('Expected and actual are:', expected, answer)

    expected = 7
    answer = number_of_stutters('xxxyyyxxxx')
    print('Expected and actual are:', expected, answer)


def number_of_stutters(s):
    """
    What comes in:
      -- a string s
    What goes out: Returns the number of times a letter is repeated
      twice-in-a-row in the given string s.
    Side effects: None.
    Examples:
      -- number_of_stutters('xhhbrrs')  returns 2
      -- number_of_stutters('xxxx')     returns 3
      -- number_of_stutters('xaxaxa')   returns 0
      -- number_of_stutters('xxx yyy xxxx')  returns 7
      -- number_of_stutters('xxxyyyxxxx')    returns 7
      -- number_of_stutters('')  returns 0
    Type hints:
       :type s: str
    """
    # ------------------------------------------------------------------
    # DONE: 4. Implement and test this function.
    #     The testing code is already written for you (above).
    # ------------------------------------------------------------------

    count = 0
    for k in range(1, len(s)):
        if s[k] == s[k-1]:
            count = count + 1
    return count






def run_test_is_palindrome():
    """ Tests the   is_palindrome   function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   is_palindrome   function:')
    print('--------------------------------------------------')

    # Five tests.
    answer1 = is_palindrome('bob')
    answer2 = is_palindrome('obbo')
    answer3 = is_palindrome('nope')
    answer4 = is_palindrome('almosttxomla')
    answer5 = is_palindrome('abbz')

    # The next would normally be written:
    #      Murder for a jar of red rum
    # It IS a palindrome (ignoring spaces and punctuation).
    answer6 = is_palindrome('murderforajarofredrum')

    print('Test is_palindrome: ',
          answer1, answer2, answer3, answer4, answer5, answer6)
    print('The above should be: True True False False False True')

    # Explicit checks, to help students who return STRINGS that LOOK
    # like    True    False.
    if answer1 is not True:
        print('Your code failed the 1st test for   is_palindrome.')
    if answer2 is not True:
        print('Your code failed the 2nd test for   is_palindrome.')
    if answer3 is not False:
        print('Your code failed the 3rd test for   is_palindrome.')
    if answer4 is not False:
        print('Your code failed the 4th test for   is_palindrome.')
    if answer5 is not False:
        print('Your code failed the 5th test for   is_palindrome.')
    if answer6 is not True:
        print('Your code failed the 6th test for   is_palindrome.')


def is_palindrome(s):
    """
    What comes in:
      -- a string s that (in this simple version of the palindrome
           problem) contains only lower-case letters
           (no spaces, no punctuation, no upper-case characters)
    What goes out: Returns  True  if the given string s is a palindrome,
      i.e., reads the same backwards as forwards.
      Returns  False  if the given string s is not a palindrome.
    Side effects: None.
    Examples:
       abba  reads backwards as   abba   so it IS a palindrome
    but
       abbz  reads backwards as   zbba   so it is NOT a palindrome

    Here are two more examples:  (Note: I have put spaces into the
    strings for readability; the real problem is the string WITHOUT
    the spaces.)
       a b c d e x x e d c b a  reads backwards as
       a b c d e x x e d c b a
         so it IS a palindrome
     but
       a b c d e x y e d c b a  reads backwards as
       a b c d e y x e d c b a
         so it is NOT a palindrome
    Type hints:
      :type s: str
    """
    # ------------------------------------------------------------------
    # DONE: 5. Implement and test this function.
    #     The testing code is already written for you (above).
    #
    ####################################################################
    # IMPORTANT:  As with ALL problems, work a concrete example BY HAND
    #   to figure out how to solve this problem.  The last two examples
    #   above are particularly good examples to work by hand.
    ####################################################################
    # ------------------------------------------------------------------
    count = 0
    for k in range(len(s)):
        if s[k] == s[len(s)-k-1]:
            count = count + 1

    if count == len(s):
        return True
    else:
        return False





# ----------------------------------------------------------------------
# Some problems loop (iterate) through two or more sequences
#    IN PARALLEL, as in the   count_same   problem below.
# ----------------------------------------------------------------------
def run_test_count_same():
    """ Tests the   count_same   function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   count_same   function:')
    print('--------------------------------------------------')

    expected = 1
    answer = count_same([1, 44, 55],
                        [0, 44, 77])
    print('Expected and actual are:', expected, answer)

    expected = 3
    answer = count_same([1, 44, 55, 88, 44],
                        [0, 44, 77, 88, 44])
    print('Expected and actual are:', expected, answer)

    expected = 0
    answer = count_same([1, 44, 55, 88, 44],
                        [0, 43, 77, 8, 4])
    print('Expected and actual are:', expected, answer)


def count_same(sequence1, sequence2):
    """
    What comes in:
      -- two sequences that have the same length
    What goes out: Returns  the number of indices at which the two
      given sequences have the same item at that index.
    Side effects: None.
    Examples:
      If the sequences are:
          (11, 33, 83, 18, 30, 55)
          (99, 33, 83, 19, 30, 44)
      then this function returns  3
      since the two sequences have the same item at:
        -- index 1 (both are 33)
        -- index 2 (both are 83)
        -- index 4 (both are 30)

      Another example:  if the sequences are:
          'how are you today?'
          'HOW? r ex u tiday?'
      then this function returns  8  since the sequences are the same
      at indices 5 (both are 'r'), 10 (both are 'u'), 11 (both are ' '),
      12 (both are 't'), 14 (both are 'd'), 15 (both are 'a'),
      16 (both are 'y') and 17 (both are '?') -- 8 indices.
    Type hints:
      type: sequence1: tuple or list or string
      type: sequence2: tuple or list or string
    """
    # ------------------------------------------------------------------
    # DONE: 6. Implement and test this function.
    #     The testing code is already written for you (above).
    # ------------------------------------------------------------------

    count = 0
    for k in range(len(sequence1)):
        if sequence1[k] == sequence2[k]:
            count = count + 1

    return count






# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
