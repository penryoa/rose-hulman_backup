"""
This module demonstrates   MUTATION   of an OBJECT in two ways:
  -- From an assignment in main.
  -- From within a function call.

Authors: David Mutchler, Amanda Stouder, Chandan Rupakheti, Katie Dion,
         Claude Anderson, Delvin Defoe, Curt Clifton, Matt Boutell,
         and their colleagues.
"""
# ----------------------------------------------------------------------
# Students: Read and run this program in the debugger to watch
#           assignment and mutation in action.  This is a contrived
#           example that we are using only to show the elements
#           of mutation with simple code.  It is the same example as
#           the one in the previous module, but using OBJECTS this time.
# ----------------------------------------------------------------------

import rosegraphics as rg


def main():
    # ------------------------------------------------------------------
    # 1. Constructs a zg.Point, assigning its instance variables values.
    # 2. Assigns an instance variable in the object a new value,
    #      thus MUTATING the OBJECT (because one of
    #      its INSTANCE VARIABLES was ASSIGNED a new value).
    # ------------------------------------------------------------------
    point = rg.Point(45, 100)
    point.y = 33
    print(point)  # To see that the INSIDES of   point   has changed

    # ------------------------------------------------------------------
    # 3. Mutates the object again, this time from within a function call
    # ------------------------------------------------------------------

    mutate_point(point)
    print(point)  # To see that the INSIDES of   point   has changed

    # ------------------------------------------------------------------
    # 4. Assigns another variable to refer to the same zg.Point
    #       to which the    point   variable refers.
    # 5. Re-assigns the   point   variable to refer to another zg.Point.
    # This is ASSIGNMENT and NOT mutation.
    # ------------------------------------------------------------------
    point2 = point
    point = rg.Point(10, 6)

    print(point, point2)  # Prints the two DIFFERENT zg.Points

    # ------------------------------------------------------------------
    # Shows the difference betwee the   is   operator
    #    (two things refer to the same place in memory)
    # and the   ==   operator (two things contain the same data).
    # ------------------------------------------------------------------
    point3 = rg.Point(100, 20)
    point4 = rg.Point(100, 20)
    point5 = point3

    print(point3 is point4)
    print(point3 == point4)

    point3.fill_color = 'blue'
    print(point3 == point4)

    print(point3 is point5)
    print(point3 is point4)


def mutate_point(point):
    point.y = 77

# ----------------------------------------------------------------------
# If this module is running at the top level (as opposed to being
# imported by another module), then call the 'main' function.
# ----------------------------------------------------------------------
if __name__ == '__main__':
    main()
