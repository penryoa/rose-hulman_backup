"""
This project demonstrates NESTED LOOPS (i.e., loops within loops)
in the context of PRINTING on the CONSOLE.

Authors: David Mutchler, Valerie Galluzzi, Mark Hays, Amanda Stouder,
         their colleagues, and many others before them.
"""
# ----------------------------------------------------------------------
# Students: READ and RUN this program.  There is nothing else for you
#           to do in here. But DO study these examples carefully,
#           and refer back to them as necessary.
# ----------------------------------------------------------------------


def main():
    """ Calls the other functions to demonstrate them. """
    classic_example_1(3, 9)
    classic_example_2(4)
    classic_example_3(5, 9)


def classic_example_1(n, m):
    """
    Prints a multiplication table for numbers from 1 to n multiplied
    by numbers from 1 to m, where n and m are the given parameters.

    Preconditions: n and m are positive integers.
    """
    # ------------------------------------------------------------------
    # Classic nested-loops, type 1:
    #    The number of inner-loop iterations does NOT depend
    #    on the current value of the outer-loop variable.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    header = 'Complete multiplication table for 1 x 1 through'
    print(header, n, 'x', m)
    print('-----------------------------------------------------------')

    for i in range(n):
        for j in range(m):
            print(i + 1, j + 1, '=', (i + 1) * (j + 1))
        print()


def classic_example_2(n):
    """
    Prints PART of a multiplication table.
    Precondition: n is a positive integer.
    """
    # ------------------------------------------------------------------
    # Classic nested-loops, type 2:
    #    The number of inner-loop iterations DOES depend
    #    on the current value of the outer-loop variable.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    header = 'Partial multiplication table for 1 x 1 through'
    print(header, n, 'x', n)
    print('-----------------------------------------------------------')

    for i in range(n):  # The ONLY difference from the previous
        for j in range(i + 1):  # example is   i+1   on this line.
            print(i + 1, j + 1, '=', (i + 1) * (j + 1))
        print()


def classic_example_3(n, m):
    """
    Prints a multiplication table for numbers from 1 to n multiplied
    by numbers from 1 to m, where n and m are the given parameters.
    Prints only the products (unlike classic example 1),
    and prints each "chunk" of the table on a single row.

    Preconditions: n and m are positive integers.
    """
    # ------------------------------------------------------------------
    # Same as classic example 1, but shows how to keep successive
    # print statements on the same line, then go to the next line.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    header = 'Complete multiplication table for 1 x 1 through'
    print(header, n, 'x', m)
    print('but with just products shown')
    print('-----------------------------------------------------------')

    for i in range(n):
        for j in range(m):
            print((i + 1) * (j + 1), end=' ')
        print()


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
