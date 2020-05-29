"""
This module lets you practice the WAIT-FOR-EVENT pattern.

See your instructor for whether you should use:
The ITCH pattern:

   Initialize as needed so that the CONDITION can be TESTED.
   while <some CONDITION>: # Test the CONDITION, continue WHILE it is true.
       ...
       ...
       CHange something that (eventually) affects the CONDITION.
         (else otherwise you will be in an infinite loop)

or

The WHILE TRUE pattern:

     while True:
       ...
       if <event has occurred>:
           break
       ...

Ultimately you should be comfortable with both approaches.

Authors: David Mutchler, Valerie Galluzzi, Mark Hays, Amanda Stouder,
         their colleagues and PUT_YOUR_NAME_HERE.
"""  # TODO: 1. PUT YOUR NAME IN THE ABOVE LINE.


def main():
    """ Calls the   TEST   functions in this module. """
    run_test_sum_until_prime_input()
    run_test_next_prime()
    run_test_prime_gap()
    run_test_wait_for_sum_of_cubes()


def is_prime(n):
    """
    What comes in:  An integer n >= 2.
    What goes out:  Returns True if the given integer is prime,
       else returns False.
    Side effects:   None.
    Examples:
      -- is_prime(11) returns  True
      -- is_prime(12) returns  False
      -- is_prime(2)  returns  True
    Note: The algorithm used here is simple and clear but slow.
    """
    for k in range(2, (n // 2) + 1):
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


def run_test_sum_until_prime_input():
    """ Tests the   wait_for_prime_input   function by calling it. """
    print()
    print('--------------------------------------------------')
    print('Testing the   sum_until_prime_input   function:')
    print('--------------------------------------------------')

    sum_until_prime_input()


def sum_until_prime_input():
    """
    What comes in:  Nothing.
    What goes out: Nothing (i.e., None).
    Side effects:
      -- Repeatedly prompts the user for and inputs an integer
            that is at least 2.
      -- Stops when the input integer is prime.
      -- Prints the sum of the input integers (including the prime one).
    Example:
    Here is a sample run, where the user input is to the right
    of the colons:
         Enter an integer greater than 1: 6
         Enter an integer greater than 1: 100
         Enter an integer greater than 1: 50
         Enter an integer greater than 1: 11
         The sum of the input integers is: 167
    """
    # ------------------------------------------------------------------
    # TODO: 2. Implement and test this function.
    #   The testing code is already written for you (above).
    # ------------------------------------------------------------------


def run_test_next_prime():
    """ Tests the   next_prime    function. """
    # ------------------------------------------------------------------
    # TODO: 3. Implement this TEST function.
    #   It TESTS the  wait_for_prime  function defined below.
    #   Include at least  ** 6 **  tests. (We supplied 5 tests for you.)
    #
    #   As usual, include both EXPECTED and ACTUAL results in your test
    #   and compute the latter BY HAND (not by running your program).
    # ------------------------------------------------------------------
    print()
    print('--------------------------------------------------')
    print('Testing the   next_prime   function:')
    print('--------------------------------------------------')

    # Test 1:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 7
    actual = next_prime(7)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 2:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 11
    actual = next_prime(8)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 3:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 83
    actual = next_prime(80)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 4:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 155921 + 86
    actual = next_prime(155922)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 5:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 2
    actual = next_prime(2)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # TO DO 3 (continued):
    #   PUT YOUR TEST   ** IN THE SPACE BETWEEN **   the
    #   print('TEST STARTED!' ...) and print('TEST ENDED') lines below.

    # Test 6:
    print()
    print('TEST STARTED!  Has it ended?')

    print('TEST ENDED!')


def next_prime(m):
    """
    What comes in:  An integer   m   that is at least 2.
    What goes out:  Returns the smallest integer greeater than
       or equal to   m   that is prime.
    Side effects:   None.
    Examples:
      -- next_prime(7)  returns  7
      -- next_prime(8)  returns  11
      -- next_prime(80)  returns  83
      -- next_prime(155921)  returns  156007  [trust me!]
    Type hints:
      :type m: int
    """
    # ------------------------------------------------------------------
    # TODO: 4. Implement and test this function.
    #   Note that you should write its TEST function first (above).
    #
    # IMPLEMENTATION REQUIREMENT:
    #    -- Use (call) the   is_prime   function above appropriately.
    # ------------------------------------------------------------------


def run_test_prime_gap():
    """ Tests the   prime_gap    function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   prime_gap   function:')
    print('--------------------------------------------------')

    # Test 1:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 2
    actual = prime_gap(1)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 2:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 3
    actual = prime_gap(2)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 3:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 7
    actual = prime_gap(4)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 4:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 7
    actual = prime_gap(3)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 5:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 23
    actual = prime_gap(6)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 6:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 23
    actual = prime_gap(5)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 7:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 89
    actual = prime_gap(8)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 8:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 89
    actual = prime_gap(7)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 9:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 19609
    actual = prime_gap(52)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 10:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 19609
    actual = prime_gap(45)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')


def prime_gap(m):
    """
    What comes in:  An integer   m   that is at least 2.
    What goes out:
       Returns the smallest prime number whose "gap" is at least m,
       where the "gap" of a prime number is the difference between
       that prime number and the next-smallest prime number.
       to   m   that is prime.
    Side effects:   None.
    Examples:
      -- prime_gap(1) returns 2, because the gap for 2
           is 3 - 2 = 1, and 2 is the smallest prime with gap 1.
      -- prime_gap(2) returns 3, because the gap for 3
           is 5 - 3 = 2, and 3 is the smallest prime with gap 2.
      -- prime_gap(3) returns 7, because the gap for 7
           is 11 - 7 = 4, and 7 is the smallest prime with gap
           3 or more.
      -- prime_gap(4) returns 7 for similar reasons.
      -- prime_gap(6) returns 23, because the gap for 23
           is 29 - 23 = 6, and 23 is the smallest prime with gap 6.
      -- prime_gap(8) returns 89, because the gap for 89
           is 97 - 89 = 8, and 89 is the smallest prime with gap 8.
      -- prime_gap(52)  returns  19609  [trust me!]
    Type hints:
      :type m: int
    """
    # ------------------------------------------------------------------
    # TODO: 5. Implement and test this function.
    #   The testing code is already written for you (above).
    #
    # IMPLEMENTATION REQUIREMENT:
    #    -- Use (call) the   *** next_prime ***   function
    #       (that you implemented) appropriately.
    # ------------------------------------------------------------------


def run_test_wait_for_sum_of_cubes():
    """ Tests the   wait_for_sum_of_cubes    function. """
    # ------------------------------------------------------------------
    # TODO: 6. Implement this TEST function.
    #   It TESTS the  wait_for_sum_of_cubes  function defined below.
    #   Include at least  ** 8 **  tests. (We supplied 6 tests for you.)
    #
    #   As usual, include both EXPECTED and ACTUAL results in your test
    #   and compute the latter BY HAND (not by running your program).
    # ------------------------------------------------------------------
    print()
    print('--------------------------------------------------')
    print('Testing the   wait_for_sum_of_cubes   function:')
    print('--------------------------------------------------')

    # Test 1:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 2
    actual = wait_for_sum_of_cubes(4.3)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 2:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 4
    actual = wait_for_sum_of_cubes(58)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 3:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 8
    actual = wait_for_sum_of_cubes(1000)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 4:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 8
    actual = wait_for_sum_of_cubes(1296)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 5:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 9
    actual = wait_for_sum_of_cubes(1296.000001)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # Test 6:
    print()
    print('TEST STARTED!  Has it ended?')
    expected = 1
    actual = wait_for_sum_of_cubes(-5.2)
    print('Expected:', expected)
    print('Actual:  ', actual)
    print('TEST ENDED!')

    # TO DO 6 (continued):
    #   PUT YOUR TEST   ** IN THE SPACE BETWEEN **   the
    #   print('TEST STARTED!' ...) and print('TEST ENDED') lines below.
    #
    # Use   wait_for_sum_of_cubes(30.33)   as your test here.
    # Compute the expected answer BY HAND, as always.

    # Test 7:
    print()
    print('TEST STARTED!  Has it ended?')

    print('TEST ENDED!')

    # TO DO 6 (continued):
    #   PUT YOUR TEST   ** IN THE SPACE BETWEEN **  the
    #   print('TEST STARTED!' ...) and print('TEST ENDED') lines below.

    # Test 8:
    print()
    print('TEST STARTED!  Has it ended?')

    print('TEST ENDED!')


def wait_for_sum_of_cubes(x):
    """
    What comes in:  A number x.
    What goes out:  Returns the smallest positive integer n
      such that the sum
           1 cubed  +  2 cubed  +  3 cubed  +  ...  + n cubed
      is greater than or equal to x.
    Side effects:   None.
    Examples:
      -- If x is 4.3, this function returns   2   because:
            1 cubed = 1 which is less than 4.3
         but
            1 cubed + 2 cubed = 9 which is greater than or equal to 4.3
      -- For similar reasons, if x is any number in the range (1, 9]
         (that is, numbers greater than 1 but less than or equal to 9),
         this function returns 2.
      -- If x is 58, this function returns   4   because:
            1 cubed + 2 cubed + 3 cubed = 36   which is less than 58
         but
            1 cubed + 2 cubed + 3 cubed + 4 cubed = 100
              which is greater than or equal to 58
      -- For similar reasons, if x is any number in the range (36, 100],
         this function returns 4.
      -- If x is 1000, this function returns 8 because:
              1 + 8 + 27 + 64 + ... + (7**3) = 784
            but
              1 + 8 + 27 + 64 + ... + (8**3) = 1296
      -- For similar reasons, f x is 1296, this function returns 8.
      -- if x is -5.2 (or any number less than or equal to 1),
           this function returns 1.
    Type hints:
      :type x: float  [or an int]
    """
    # ------------------------------------------------------------------
    # TODO: 7. Implement and test this function.
    #   Note that you should write its TEST function first (above).
    #
    # IMPLEMENTATION REQUIREMENT:
    #   -- Solve this using the   wait-until-event   pattern.
    #
    # Note for the mathematically inclined:  One could figure out
    # (or look up) a formula that would allow a faster computation.
    # But no fair using that in this function.
    # ------------------------------------------------------------------


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
