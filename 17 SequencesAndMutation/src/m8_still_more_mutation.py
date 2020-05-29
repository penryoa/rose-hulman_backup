"""
This module lets you practice   MUTATION   of lists.
In this module, you mutate by DELETING elements of a list.

Authors: David Mutchler, Amanda Stouder, Chandan Rupakheti, Katie Dion,
         Claude Anderson, Delvin Defoe, Curt Clifton, their colleagues,
         and Olivia Penry.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

import m6_mutation


def main():
    run_test_RETURN_delete_negatives()
    run_test_MUTATE_delete_negatives()


def run_test_RETURN_delete_negatives():
    """ Tests the   RETURN_delete_negatives   function. """
    print()
    print('--------------------------------')
    print('Testing RETURN_delete_negatives:')
    print('--------------------------------')

    # ------------------------------------------------------------------
    # Test 1:
    # ------------------------------------------------------------------
    run_test_number = 1
    original_argument = [-30.2, 50, 12.5, -1, -5, 8, 0]
    correct_argument_value_after_function_call = original_argument.copy()
    correct_returned_value = [50, 12.5, 8, 0]

    m6_mutation.run_test(RETURN_delete_negatives,
                         original_argument,
                         run_test_number,
                         correct_returned_value,
                         correct_argument_value_after_function_call)

    # ------------------------------------------------------------------
    # Test 2:
    # ------------------------------------------------------------------
    run_test_number = 2
    original_argument = [2, 0, -9, 1, -30]
    correct_argument_value_after_function_call = original_argument.copy()
    correct_returned_value = [2, 0, 1]

    m6_mutation.run_test(RETURN_delete_negatives,
                         original_argument,
                         run_test_number,
                         correct_returned_value,
                         correct_argument_value_after_function_call)


def RETURN_delete_negatives(numbers):
    """
    Returns a NEW list that is the same as the given list of numbers,
    but with each negative number in the list DELETED from the list.

    For example, if the given list is [-30.2, 50, 12.5, -1, -5, 8, 0].
    then the returned list is the NEW list [50, 12.5, 8, 0].

    This function must NOT mutate the given list.

    Precondition:
      :type numbers: list
    where the list is a list of numbers.
    """
    # DONE: 2. First, READ THE ABOVE TEST CODE.
    #          Make sure that you understand it.
    #          In particular, note how it calls the   run_test   function
    #          from the module   m6_mutation   by using the notation:
    #             m6_mutation.run_test(...)
    #          Then, IMPLEMENT and test THIS FUNCTION
    #          (using the above code for testing).

    list = []
    for k in range(len(numbers)):
        if numbers[k] >= 0:
            list = list + [numbers[k]]

    return list






def run_test_MUTATE_delete_negatives():
    """ Tests the   MUTATE_delete_negatives   function. """
    print()
    print('--------------------------------')
    print('Testing MUTATE_delete_negatives:')
    print('--------------------------------')

    # ------------------------------------------------------------------
    # Test 1:
    # ------------------------------------------------------------------
    run_test_number = 1
    original_argument = [-30.2, 50, 12.5, -1, -5, 8, 0]
    correct_argument_value_after_function_call = [50, 12.5, 8, 0]
    correct_returned_value = None

    m6_mutation.run_test(MUTATE_delete_negatives,
                         original_argument,
                         run_test_number,
                         correct_returned_value,
                         correct_argument_value_after_function_call)

    # ------------------------------------------------------------------
    # Test 2:
    # ------------------------------------------------------------------
    run_test_number = 2
    original_argument = [2, 0, -9, 1, -30]
    correct_argument_value_after_function_call = [2, 0, 1]
    correct_returned_value = None

    m6_mutation.run_test(MUTATE_delete_negatives,
                         original_argument,
                         run_test_number,
                         correct_returned_value,
                         correct_argument_value_after_function_call)


def MUTATE_delete_negatives(numbers):
    """
    MUTATES the given list of numbers so that each negative number
    in the list is DELETED from the list.

    For example, if the given list is [-30.2, 50, 12.5, -1, -5, 8, 0].
    then that list is MUTATED to become [50, 12.5, 8, 0].

    This function MAY use ONE additional list beyond the given list
    (but see if you can solve the problem WITHOUT any additional lists).
    The function must NOT return anything (other than the default None).

    Precondition: The argument is a list of numbers.
    :type numbers: object
    """
    # DONE: 3. First, READ THE ABOVE TEST CODE.
    #          Make sure that you understand it.
    #          In particular, note how it calls the   run_test   function
    #          from the module   m6_mutation   by using the notation:
    #             m6_mutation.run_test(...)
    #          Then, IMPLEMENT and test THIS FUNCTION
    #          (using the above code for testing).
    #
    # HINT: This problem is MUCH harder than it would appear,
    #       for various quite-subtle reasons.
    #       Take a stab at this problem,
    #       then see the solutions posted on Piazza.
    #       See those solutions even if your approach passes the tests.

    list = []
    for k in range(len(numbers)):
        if numbers[k] >= 0:
            list = list + [numbers[k]]

    for k in range(len(numbers) - len(list) + 1):
        numbers[k] = list[k]

    x = len(numbers)

    for k in range(x - len(list)):
        numbers.pop(x - 1 - k)



# ----------------------------------------------------------------------
# If this module is running at the top level (as opposed to being
# imported by another module), then call the 'main' function.
# ----------------------------------------------------------------------
if __name__ == '__main__':
    main()
