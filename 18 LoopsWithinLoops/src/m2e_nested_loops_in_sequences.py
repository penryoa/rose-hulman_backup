"""
This project demonstrates NESTED LOOPS (i.e., loops within loops)
in the context of SEQUENCES OF SUB-SEQUENCES.

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
    demonstrate_sequence_of_sequences()


def demonstrate_sequence_of_sequences():
    """
    Demonstrates     seqeunce_of_sequence_example
       -- a classic example of manipulating a SEQUENCE of SUB-SEQUENCES.
    Also shows mutating those sub-sequences.
    """
    tuple_of_lists1 = ([4, 0, 100],
                       [1, 2, 3],
                       [100, 100, 20, 30])

    tuple_of_lists2 = (['the', 'rain'],
                       ['in spain', 'falls', 'mainly on the'],
                       ['plain.'])

    tuple_of_lists3 = ([10, 5],
                       [10, 5, 5, 8, 20],
                       ['a', 'b', 'c'],
                       ['d'],
                       ['e', 'f'])

    tuple_of_lists3b = ([10, 5],
                        [10, 5, 5, 8, 20],
                        ['a', 'b', 'c'],
                        ['d'],
                        ['e', 'f'])

    print()
    print('-----------------------------------------------------------')
    print('This example MUTATES each SUB-sequence, by multiplying')
    print('the 1st sub-sequence by 1, the 2nd by 2, the 3rd by 3, ...')
    print('-----------------------------------------------------------')
    print()
    print('The sequences BEFORE and AFTER the call, respectively, are:')
    print('   ', tuple_of_lists1)

    sequence_of_sequence_example(tuple_of_lists1)
    print('   ', tuple_of_lists1)

    print()
    print('The sequences BEFORE and AFTER the call, respectively, are:')
    print('   ', tuple_of_lists2)

    sequence_of_sequence_example(tuple_of_lists2)
    print('   ', tuple_of_lists2)

    print()
    print('The sequences BEFORE and AFTER the call, respectively, are:')
    print('   ', tuple_of_lists3)

    sequence_of_sequence_example(tuple_of_lists3)
    print('   ', tuple_of_lists3)

    print()
    print('Same as previous, but using [xx][yy] notation in the code:')
    print('   ', tuple_of_lists3b)

    sequence_of_sequence_example2(tuple_of_lists3b)
    print('   ', tuple_of_lists3b)


def sequence_of_sequence_example(sequence_of_lists):
    """
    In the given sequence of lists,
      -- multiplies each element of the first list by 1,
      -- multiplies each element of the second list by 2,
      -- multiplies each element of the third list by 3,
      -- and so forth.
    MUTATES the given lists (but NOT the given sequence).

    Preconditions: The argument is a sequence of lists,
       and the elements of the lists can be multiplied by an integer.
       [FYI: This 'can be multiplied ...' is an example of DUCK TYPING.]
    """
    # ------------------------------------------------------------------
    # Classic sequence of sequences:
    #    - Each loop is simply the pattern you have seen many times.
    #    - But INSIDE the OUTER loop and BEFORE the INNER loop,
    #         you can 'extract' the current (OUTER loop) SUB-list
    #         to loop through it in the INNER loop.
    # ------------------------------------------------------------------
    for j in range(len(sequence_of_lists)):

        sublist = sequence_of_lists[j]

        for k in range(len(sublist)):
            sublist[k] = sublist[k] * (j + 1)


def sequence_of_sequence_example2(sequence_of_lists):
    """ Same as previous example, but slightly different notation. """
    # Same as previous, but without the sublist variable.
    for j in range(len(sequence_of_lists)):
        for k in range(len(sequence_of_lists[j])):
            sequence_of_lists[j][k] = sequence_of_lists[j][k] * (j + 1)


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
