"""
This module demonstrates that  TUPLES  and  STRINGS  are IMMUTABLE:
  -- Attempts to mutate them cause run-time errors.

Authors: David Mutchler, Amanda Stouder, Chandan Rupakheti, Katie Dion,
         Claude Anderson, Delvin Defoe, Curt Clifton, Matt Boutell,
         and their colleagues.
"""
# ----------------------------------------------------------------------
# Students: Read and run this program in the debugger to watch attemps
#           to mutate a TUPLE and a STRING cause run-time errors.
# ----------------------------------------------------------------------


def main():
    # ------------------------------------------------------------------
    # 1. Constructs a tuple, assigning it a value.
    # 2. Constructs a string, assigning it a value.
    # ------------------------------------------------------------------
    numbers = (45, 100, 8)
    s = 'Hello'

    print(numbers, s)

    # ------------------------------------------------------------------
    # 3. Assigns the tuple a new value -- NO PROBLEM!
    # 4. Assigns the string a new value -- NO PROBLEM!
    # ------------------------------------------------------------------
    numbers = (55, 20)
    s = 'Goodbye'

    print(numbers, s)

    # ------------------------------------------------------------------
    # 5. Attempts to change the INSIDES of the tuple,
    #       that is, attempts to MUTATE the tuple.
    # 6. Attemps to change the INSIDES of the string,
    #       that is, attempts to MUTATE the string.
    # These cause RUN-TIME errors.
    # ------------------------------------------------------------------
    numbers[2] = 77
    s[0] = 'X'

# ----------------------------------------------------------------------
# If this module is running at the top level (as opposed to being
# imported by another module), then call the 'main' function.
# ----------------------------------------------------------------------
if __name__ == '__main__':
    main()
