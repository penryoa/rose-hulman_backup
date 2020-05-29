"""
This project demonstrates NESTED LOOPS (i.e., loops within loops)
in the context of PRINTING on the CONSOLE.

Authors: David Mutchler, Valerie Galluzzi, Mark Hays, Amanda Stouder,
         their colleagues and Olivia Penry.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.


def main():
    """ Calls the other functions to test them. """
    run_test_rectangle_of_stars()
    run_test_triangle_of_stars()
    run_test_decreasing_exclamation_marks()
    run_test_alternating_brackets()
    run_test_triangle_same_number_in_each_row()
    run_test_triangle_all_numbers_in_each_row()


def run_test_rectangle_of_stars():
    """ Tests the    rectangle_of_stars    function. """
    print()
    print('--------------------------------------------')
    print('Testing the   RECTANGLE_OF_STARS   function:')
    print('--------------------------------------------')

    print('Test 1 of rectangle_of_stars: (3, 5)')
    rectangle_of_stars(3, 5)

    print('Test 2 of rectangle_of_stars: (4, 11)')
    rectangle_of_stars(4, 11)

    print('Test 3 of rectangle_of_stars: (6, 2)')
    rectangle_of_stars(6, 2)


def rectangle_of_stars(r, c):
    """
    Prints a rectangle of stars (asterisks), with r rows and c columns.
    For example, when r = 3 and c = 5:
       *****
       *****
       *****
    Preconditions:  r and c are non-negative integers.
    """
    # ------------------------------------------------------------------
    # DONE: 2. Implement and test this function.
    #          Some tests are already written for you (above).
    #
    #  *** Unless your instructor directs you otherwise,
    #      see the video
    #          nested_loops_in_PRINTING.mp4
    #      in Preparation for Session 18
    #          ** NOW **
    #      and follow along in that video as you do this problem.
    #      (Pause the video when it completes this problem.)
    #  ***
    #
    # IMPLEMENTATION RESTRICTION:
    #   ** You may NOT use string multiplication **
    #   in this or the other problems in this module, as doing so
    #   would defeat the goal of providing practice at loops within loops.
    # ------------------------------------------------------------------

    for k in range(r):
        for j in range(c):
            print('*', end='')
        print()




def run_test_triangle_of_stars():
    """ Tests the    triangle_of_stars    function. """
    print()
    print('-------------------------------------------')
    print('Testing the   TRIANGLE_OF_STARS   function:')
    print('-------------------------------------------')

    print('Test 1 of triangle_of_stars: (5)')
    triangle_of_stars(5)

    print('Test 2 of triangle_of_stars: (1)')
    triangle_of_stars(1)

    print('Test 3 of triangle_of_stars: (3)')
    triangle_of_stars(3)

    print('Test 4 of triangle_of_stars: (6)')
    triangle_of_stars(6)


def triangle_of_stars(r):
    """
    Prints a triangle of stars (asterisks), with r rows.
      -- The first row is 1 star,
         the second is 2 stars,
         the third is 3 stars, and so forth.
    For example, when r = 5:
       *
       **
       ***
       ****
       *****
    Precondition:  r is a non-negative integer.
    """
    # ------------------------------------------------------------------
    # DONE: 3. Implement and test this function.
    #          Some tests are already written for you (above).
    #
    #  *** Unless your instructor directs you otherwise,
    #      see the video
    #          nested_loops_in_PRINTING.mp4
    #      in Preparation for Session 18
    #          ** NOW **
    #      and follow along in that video as you do this problem.
    #      (Continue the video from where you paused it
    #      in the previous problem.)
    #  ***
    #
    # IMPLEMENTATION RESTRICTION:
    #   ** You may NOT use string multiplication **
    #   in this or the other problems in this module, as doing so
    #   would defeat the goal of providing practice at loops within loops.
    # ------------------------------------------------------------------

    for k in range(r):
        for j in range(k+1):
            print('*', end ='')
        print()




def run_test_decreasing_exclamation_marks():
    """ Tests the    decreasing_exclamation_marks    function. """
    print()
    print('----------------------------------------------------------')
    print('Testing the   DECREASING_EXCLAMATION_MARKS   function:')
    print('----------------------------------------------------------')

    print('Test 1 of decreasing_exclamation_marks: (5, 2)')
    decreasing_exclamation_marks(5, 2)

    print('Test 2 of decreasing_exclamation_marks: (3, 1)')
    decreasing_exclamation_marks(3, 1)

    print('Test 3 of decreasing_exclamation_marks: (4, 4)')
    decreasing_exclamation_marks(4, 4)

    print('Test 4 of decreasing_exclamation_marks: (8, 6)')
    decreasing_exclamation_marks(8, 6)


def decreasing_exclamation_marks(m, n):
    """
    Prints exclamation marks:  m on the first row,
    m-1 on the next row, m-2 on the next, etc, until n on the last row.
    For example, when m = 5 and n = 2:
       !!!!!
       !!!!
       !!!
       !!
    Precondition:  m and n are positive integers with m >= n.
    """
    # ------------------------------------------------------------------
    # DONE: 4. Implement and test this function.
    #          Some tests are already written for you (above).
    #
    # IMPLEMENTATION RESTRICTION:
    #   ** You may NOT use string multiplication **
    #   in this or the other problems in this module, as doing so
    #   would defeat the goal of providing practice at loops within loops.
    # ------------------------------------------------------------------

    for k in range(m-n+1):
        for j in range(m-k):
            print('!', end='')
        print()



def run_test_alternating_brackets():
    """ Tests the    alternating_brackets    function. """
    print()
    print('----------------------------------------------------------')
    print('Testing the   ALTERNATING_BRACKETS   function:')
    print('----------------------------------------------------------')

    print('Test 1 of alternating_brackets: (5, 2)')
    alternating_brackets(5, 2)

    print('Test 2 of alternating_brackets: (3, 1)')
    alternating_brackets(3, 1)

    print('Test 3 of alternating_brackets: (4, 4)')
    alternating_brackets(4, 4)

    print('Test 4 of alternating_brackets: (8, 6)')
    alternating_brackets(8, 6)


def alternating_brackets(m, n):
    """
    Prints alternating left/right square brackets:  m on the first row,
    m-1 on the next row, m-2 on the next, etc, until n on the last row.
    For example, when m = 5 and n = 2:
       [][][
       [][]
       [][
       []
    Precondition:  m and n are positive integers with m >= n.
    """
    # ------------------------------------------------------------------
    # DONE: 5. Implement and test this function.
    #          Some tests are already written for you (above).
    #
    # IMPLEMENTATION RESTRICTION:
    #   ** You may NOT use string multiplication **
    #   in this or the other problems in this module, as doing so
    #   would defeat the goal of providing practice at loops within loops.
    # ------------------------------------------------------------------


    for k in range(m-n+1):
        for j in range(m-k):
            if j%2 == 0:
                print('[', end='')
            else:
                print(']',end='')
        print()








def run_test_triangle_same_number_in_each_row():
    """ Tests the    triangle_same_number_in_each_row    function. """
    print()
    print('----------------------------------------------------------')
    print('Testing the   TRIANGLE_SAME_NUMBER_IN_EACH_ROW   function:')
    print('----------------------------------------------------------')

    print('Test 1 of triangle_same_number_in_each_row: (5)')
    triangle_same_number_in_each_row(5)

    print('Test 2 of triangle_same_number_in_each_row: (1)')
    triangle_same_number_in_each_row(1)

    print('Test 3 of triangle_same_number_in_each_row: (3)')
    triangle_same_number_in_each_row(3)

    print('Test 4 of triangle_same_number_in_each_row: (6)')
    triangle_same_number_in_each_row(6)


def triangle_same_number_in_each_row(r):
    """
    Prints a triangle of numbers, with r rows.
    The first row is 1, the 2nd is 22, the 3rd is 333, etc.
    For example, when r = 5:
       1
       22
       333
       4444
       55555
    Precondition:  r is a non-negative integer.
    """
    # ------------------------------------------------------------------
    # DONE: 6. Implement and test this function.
    #          Some tests are already written for you (above).
    #
    # IMPLEMENTATION RESTRICTION:
    #   ** You may NOT use string multiplication **
    #   in this or the other problems in this module, as doing so
    #   would defeat the goal of providing practice at loops within loops.
    # ------------------------------------------------------------------

    for k in range(r):
        for j in range(k+1):
            print(k+1,end='')

        print()




def run_test_triangle_all_numbers_in_each_row():
    """ Tests the    triangle_all_numbers_in_each_row    function. """
    print()
    print('----------------------------------------------------------')
    print('Testing the   TRIANGLE_ALL_NUMBERS_IN_EACH_ROW   function:')
    print('----------------------------------------------------------')

    print('Test 1 of triangle_all_numbers_in_each_row: (5)')
    triangle_all_numbers_in_each_row(5)

    print('Test 2 of triangle_all_numbers_in_each_row: (1)')
    triangle_all_numbers_in_each_row(1)

    print('Test 3 of triangle_all_numbers_in_each_row: (3)')
    triangle_all_numbers_in_each_row(3)

    print('Test 4 of triangle_all_numbers_in_each_row: (6)')
    triangle_all_numbers_in_each_row(6)


def triangle_all_numbers_in_each_row(r):
    """
    Prints a triangle of numbers, with r rows.
    The first row is 1, the 2nd is 12, the 3rd is 123, etc.
    For example, when r = 5:
       1
       12
       123
       1234
       12345
    Precondition:  r is a non-negative integer.
    """
    # ------------------------------------------------------------------
    # DONE: 7. Implement and test this function.
    #          Some tests are already written for you (above).
    #
    # IMPLEMENTATION RESTRICTION:
    #   ** You may NOT use string multiplication **
    #   in this or the other problems in this module, as doing so
    #   would defeat the goal of providing practice at loops within loops.
    # ------------------------------------------------------------------


    for k in range(r):
        for j in range(k+1):
            print(j+1,end='')

        print()




# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
