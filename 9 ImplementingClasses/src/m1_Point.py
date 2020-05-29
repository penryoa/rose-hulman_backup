"""
A simple   Point   class.
NOTE: This is NOT rosegraphics -- it is your OWN Point class.

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Amanda Stouder,
         their colleagues and Olivia Penry.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.


def main():
    """ Calls the   TEST   functions in this module. """
    run_test_init()
    run_test_repr()
    run_test_clone()
    run_test_move_to()
    run_test_move_by()
    run_test_get_number_of_moves_made()
    run_test_get_distance_from()
    run_test_get_distance_from_start()
    run_test_get_distance_traveled()
    run_test_closer_to()
    run_test_halfway_to()

########################################################################
# IMPORTANT:
#   Your instructor will help you get started on this exercise.
########################################################################

# ----------------------------------------------------------------------
# DONE: 2. With your instructor, READ THE INSTRUCTIONS
#   in file  m0_INSTRUCTIONS.txt, asking questions as needed.
#
#   Then implement a class called   Point   that has NO METHODS yet,
#   just the lines that start the definition of any class:
#
#      class NAME_OF_CLASS(object):
#          """ Brief description of what objects of the class 'are'."""
#
#   Run the program and correct any syntax (notational) errors.
# ----------------------------------------------------------------------

########################################################################
# NOTE: For ALL of the methods that you implement, the method is allowed
# to have additional side effects as needed by it and/or other methods.
########################################################################

import math


class Point(object):
    """Models a 2D point; has x and y coordinates."""

    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.start_x = x
        self.start_y = y
        self.total_moves = 0
        self.dis_traveled = 0

    def __repr__(self):
        return "Point ({}, {})".format(self.x, self.y)

    def clone(self):
        p1 = Point(self.x,self.y)
        return p1

    def move_to(self, x, y):
        self.dis_traveled = self.dis_traveled + math.sqrt((self.x - x)**2 + (self.y - y)**2)
        self.x = x
        self.y = y
        self.total_moves = self.total_moves + 1
        return self

    def move_by(self, dx, dy):
        self.dis_traveled = self.dis_traveled + math.sqrt((dx)**2 + (dy)**2)
        self.x = self.x + dx
        self.y = self.y + dy
        self.total_moves = self.total_moves + 1
        return self

    def get_number_of_moves_made(self,):
        return self.total_moves

    def get_distance_from(self, point):
        dis_x = self.x - point.x
        dis_y = self.y - point.y
        dis = math.sqrt(dis_x**2 + dis_y**2)
        return dis

    def get_distance_from_start(self):
        dis_x = (self.x - self.start_x)
        dis_y = (self.y - self.start_y)
        dis_from_st = math.sqrt(dis_x**2 + dis_y**2)
        return dis_from_st

    def get_distance_traveled(self):
        return self.dis_traveled

    def closer_to(self, p2, p3):
        p2_dis = self.get_distance_from(p2)
        p3_dis = self.get_distance_from(p3)
        if p2_dis >= p3_dis:
            return p2
        else:
            return p3

    def halfway_to(self, p1):
        p3_x = (self.x + p1.x)/2
        p3_y = (self.y + p1.y)/2
        p3 = Point(p3_x, p3_y)
        return p3

def run_test_init():
    """
    Tests the   __init__   method of the Point class.
      -- IMPORTANT:  There are   TWO  underscores on each side.
      -- Note:  the   __init__  method runs when one constructs
         a Point.  See examples below.

    Here is the specification for the   __init__   method:
    What comes in:
       -- self
       -- an integer x
       -- an integer y
       where (x, y) is to be the initial position of this Point.
    What goes out:  Nothing (i.e., None).
    Side effects:  Sets two instance variables named:
           x
           y
       to the given coordinate (i.e., to the given x and y).
       Other methods should modify the instance variables
           x
           y
       as needed so that they always indicate the CURRENT position
       of the Point.

    EXAMPLE: The following shows   __init__   in action.
    You may also use this example to test this method.
    """
    # ------------------------------------------------------------------
    # DONE: 3.
    #   a. Read the above specification of the   __init__   method.
    #        Do NOT proceed until you understand WHAT it should do
    #        (but not necessarily HOW it will do it).
    #        NO CODE YET.  Ask questions as needed.
    #
    #   b. Examine the EXAMPLE code in the doc-string above.
    #        Make sure that you see how that code works, and how it
    #        TESTS the __init__ method.  ** ASK QUESTIONS AS NEEDED. **
    #
    #   c. Select the code in the EXAMPLE in the doc-string above.
    #        Copy-and-paste it into this  RUN_TEST_INIT  function, putting
    #        the copied code just below the PRINT statements below.
    #
    #      Use the Tab and Shift-Tab keystrokes as needed to fix the
    #        indentation of the pasted code.
    #
    #      You cannot RUN the copy-pasted tests because you
    #        have not (yet) implemented the  __init__  method.
    #
    #   d. Implement and test the  __init__  method.
    #        Make sure that you UNDERSTAND your code and are not just
    #        "pattern matching" from examples.
    #        ASK QUESIONS AS NEEDED.  COMMIT YOUR WORK.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   __init__   method of the Point class.')
    print('-----------------------------------------------------------')


    p1 = Point(30, 18)
    print()
    print('Expected for p1: 30 18')
    print('Actual for p1:  ', p1.x, p1.y)

    p2 = Point(100, -40)
    print()
    print('Expected for p2: 100 -40')
    print('Actual for p2:  ', p2.x, p2.y)
    print('Expected for p1: 30 18')
    print('Actual for p1:  ', p1.x, p1.y)

    p1.y = 999
    print()
    print('Expected for p1: 30 999')
    print('Actual for p1:  ', p1.x, p1.y)
    print('Expected for p2: 100 -40')
    print('Actual for p2:  ', p2.x, p2.y)

# SELECT, then TAB will indent it; SHIFT+TAB makes it more back one indentation.



def run_test_repr():
    """
    Tests the   __repr__   method of the Point class.
      -- IMPORTANT:  There are   TWO  underscores on each side.
      -- Note:  the   __repr__  method is called by the PRINT
           function and other functions that DISPLAY a Point object.
           See examples below.

    Here is the specification for the   __repr__   method:
    What comes in:
       -- self
    What goes out:
      Returns a string that represents a Point like this:
         'Point(x, y)'
      where   x   and   y   are replaced by this Point's
      x and y coordinates.
    Side effects: None.

    EXAMPLE: The following shows   __repr__   in action.
    You may also use this example to test this method.
    """
    # ------------------------------------------------------------------
    # DONE: 4.  Follow the same instructions as in DONE 3 above,
    #           but for the  __repr__  method specified above.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   __repr__   method of the Point class.')
    print('-----------------------------------------------------------')

    p1 = Point(30, 18)
    print()
    print('Expected for p1: Point(30, 18)')
    print('Actual for p1:  ', p1)

    p2 = Point(100, -40)
    print()
    print('Expected for p2: Point(100, -40)')
    print('Actual for p2:  ', p2)
    print('Expected for p1: Point(30, 18)')
    print('Actual for p1:  ', p1)

    p1.y = 999
    print()
    print('Expected for p1: Point(30, 999)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(100, -40)')
    print('Actual for p2:  ', p2)


def run_test_clone():
    """
    Tests the   clone   method of the Point class.

    Here is the specification for the   clone   method:
    What comes in:
       -- self
    What goes out:
      Returns a new Point whose x and y coordinates are the same
      as the x and y coordinates of this Point.
    Side effects: None.

    EXAMPLE: The following shows   clone   in action.
    You may also use this example to test this method.

   """
    # ------------------------------------------------------------------
    # DONE: 5.  Follow the same instructions as in DONE 3 above,
    #           but for the  clone  method specified above.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   clone   method of the Point class.')
    print('-----------------------------------------------------------')

    p1 = Point(10, 8)
    print()
    print('Expected for p1: Point(10, 8)')
    print('Actual for p1:  ', p1)

    p2 = p1.clone()
    p3 = p2.clone()
    print()
    print('Expected for p1: Point(10, 8)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(10, 8)')
    print('Actual for p2:  ', p2)
    print('Expected for p3: Point(10, 8)')
    print('Actual for p3:  ', p3)

    p1.x = 999
    print()
    print('Expected for p1: Point(999, 8)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(10, 8)')
    print('Actual for p2:  ', p2)
    print('Expected for p3: Point(10, 8)')
    print('Actual for p3:  ', p3)

    p1.y = 333
    p2 = Point(11, 22)
    p3.x = 777
    p3.y = 555
    print()
    print('Expected for p1: Point(999. 333)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(11, 22)')
    print('Actual for p2:  ', p2)
    print('Expected for p3: Point(777, 555)')
    print('Actual for p3:  ', p3)


def run_test_move_to():
    """
    Tests the   move_to   method of the Point class.

    Here is the specification for the   move_to   method:
    What comes in:
       -- self
       -- an integer x
       -- an integer y
    What goes out:  Nothing (i.e., None).
    Side effects:  Changes the instance variables
          x
          y
       that store the position of this Point to the given x and y.
       This has the effect of "moving" this Point TO the given (x, y).

    EXAMPLE: The following shows   move_to   in action.
    You may also use this example to test this method.
    """
    # ------------------------------------------------------------------
    # DONE: 6.  Follow the same instructions as in DONE 3 above,
    #           but for the  move_to  method specified above.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   move_to   method of the Point class.')
    print('-----------------------------------------------------------')

    p1 = Point(10, 8)
    p2 = Point(50, 20)
    print()
    print('Expected for p1: Point(10, 8)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(50, 20)')
    print('Actual for p2:  ', p2)

    p1.move_to(5, -1)
    p2.move_to(0, 0)
    print()
    print('Expected for p1: Point(5, -1)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(0, 0)')
    print('Actual for p2:  ', p2)

    p2.y = 99
    print()
    print('Expected for p1: Point(5, -1)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(0, 99)')
    print('Actual for p2:  ', p2)

    p2.move_to(0, 222)
    print()
    print('Expected for p1: Point(5, -1)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(0, 222)')
    print('Actual for p2:  ', p2)


def run_test_move_by():
    """
    Tests the   move_by   method of the Point class.

    Here is the specification for the   move_by   method:
    What comes in:
       -- self
       -- an integer dx
       -- an integer dy
    What goes out:  Nothing (i.e., None).
    Side effects:  Adds the given   dx   and   dy
    to the instance variables
          x
          y
       that store the position of this Point.
       This has the effect of "moving" this Point BY the given (dx, dy).

    EXAMPLE: The following shows   move_by   in action.
    You may also use this example to test this method.
    """
    # ------------------------------------------------------------------
    # DONE: 7.  Follow the same instructions as in DONE 3 above,
    #           but for the  move_by  method specified above.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   move_by   method of the Point class.')
    print('-----------------------------------------------------------')

    p1 = Point(10, 8)
    p2 = Point(50, 20)
    print()
    print('Expected for p1: Point(10, 8)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(50, 20)')
    print('Actual for p2:  ', p2)

    p1.move_by(5, -1)
    p2.move_by(0, 0)
    print()
    print('Expected for p1: Point(15, 7)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(50, 20)')
    print('Actual for p2:  ', p2)

    p2.move_by(200, 0)
    print()
    print('Expected for p1: Point(15, 7)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(250, 20)')
    print('Actual for p2:  ', p2)

    p2.move_by(-100, 300)
    print()
    print('Expected for p1: Point(15, 7)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(150, 320)')
    print('Actual for p2:  ', p2)

def run_test_get_number_of_moves_made():
    """
    Tests the   get_number_of_moves_made   method of the Point class.

    Here is the specification for the  get_number_of_moves_made  method:
    What comes in:
       -- self
    What goes out:  Returns an integer that is the number of times that
       this Point has "moved" via calls to   move_to  and/or   move_by.
    Side effects:
       ** You figure out what side effect(s) MUST happen! **

    EXAMPLE: The following shows   get_number_of_moves_made   in action.
    You may also use this example to test this method.
    """
    # ------------------------------------------------------------------
    # DONE: 8.  Follow the same instructions as in DONE 3 above,
    #    but for the  get_number_of_moves_made  method specified above.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   get_number_of_moves_made   method.')
    print('-----------------------------------------------------------')

    p1 = Point(10, 8)
    p2 = Point(50, 20)
    print()
    print('Expected for p1 moves made: 0')
    print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
    print('Expected for p2 moves made: 0')
    print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

    p1.move_by(5, -1)
    p2.move_by(0, 0)
    print()
    print('Expected for p1 moves made: 1')
    print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
    print('Expected for p2 moves made: 1')
    print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

    p2.move_by(200, 0)
    p2.move_by(-100, 300)
    p2.move_to(-100, 300)
    p1.move_to(3, 3)
    print()
    print('Expected for p1 moves made: 2')
    print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
    print('Expected for p2 moves made: 4')
    print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

    p1.move_by(200, 0)
    p1.move_by(-100, 300)
    p1.move_to(-100, 300)
    p1.move_to(3, 3)
    print()
    print('Expected for p1 moves made: 6')
    print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
    print('Expected for p2 moves made: 4')
    print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

    p1.x = 400
    print()
    print('Expected for p1 moves made: 6')
    print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
    print('Expected for p2 moves made: 4')
    print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

    p1.move_to(3, 3)
    p2.move_by(0, 0)
    print()
    print('Expected for p1 moves made: 7')
    print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
    print('Expected for p2 moves made: 5')
    print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

def run_test_get_distance_from():
    """
    Tests the   get_distance_from   method of the Point class.

    Here is the specification for the   get_distance_from   method:
    What comes in:
       -- self
       -- another Point object
    What goes out:
       Returns the distance from this Point to the given Point.
    Side effects:
       ** You figure out WHETHER OR NOT side effect(s) MUST happen! **

    EXAMPLE: The following shows   get_distance_from   in action.
    You may also use this example to test this method.
    """
    # ------------------------------------------------------------------
    # DONE: 9.  Follow the same instructions as in DONE 3 above,
    #    but for the  get_distance_from  method specified above.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the  get_distance_from  method of the Point class.')
    print('-----------------------------------------------------------')

    p1 = Point(1, 5)
    p2 = Point(10, 5)
    p3 = Point(13, 9)

    print()
    print('Expected p1 to p2: 9.0')
    print('Actual   p1 to p2:', p1.get_distance_from(p2))

    print()
    print('Expected p2 to p3: 5.0')
    print('Actual   p2 to p3:', p2.get_distance_from(p3))
    print('Expected p3 to p2: 5.0')
    print('Actual   p3 to p2:', p3.get_distance_from(p2))

    print()
    print('Expected p1 to p3: about 12.65')
    print('Actual   p1 to p3:', p1.get_distance_from(p3))
    print('Expected p3 to p1: about 12.65')
    print('Actual   p3 to p1:', p3.get_distance_from(p1))

    print()
    print('Expected p1 to p1: 0.0')
    print('Actual   p1 to p1:', p1.get_distance_from(p1))
    print('Expected p2 to p2: 0.0')
    print('Actual   p2 to p2:', p2.get_distance_from(p2))
    print('Expected p3 to p3: 0.0')
    print('Actual   p3 to p3:', p3.get_distance_from(p3))

    p4 = p1.clone()
    print()
    print('Expected p1 to p4: 0.0')
    print('Actual   p1 to p4:', p1.get_distance_from(p4))
    print('Expected p4 to p1: 0.0')
    print('Actual   p4 to p1:', p4.get_distance_from(p1))
    print('Expected p4 to p2: 9.0')
    print('Actual   p4 to p2:', p4.get_distance_from(p2))
    print('Expected p2 to p4: 9.0')
    print('Actual   p2 to p4:', p2.get_distance_from(p4))


def run_test_get_distance_from_start():
    """
    Tests the   get_distance_from_START   method of the Point class.

    Here is the specification for the   get_distance_from_start  method:
    What comes in:
       -- self
    What goes out:
       Returns the distance from this Point's current position
       to the position that the Point was at when it was constructed.
    Side effects:
       ** You figure out WHETHER OR NOT side effect(s) MUST happen! **

    EXAMPLE: The following shows   get_distance_from_START   in action.
    You may also use this example to test this method.
    """
    # ------------------------------------------------------------------
    # DONE: 10.  Follow the same instructions as in DONE 3 above,
    #    but for the  get_distance_from_START  method specified above.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   get_distance_from_START   method')
    print('of the Point class.')
    print('-----------------------------------------------------------')


    p1 = Point(20, 30)
    p1.move_to(111, 222)
    p1.move_by(10, 20)
    p1.move_to(0, 0)
    p1.move_to(21, 31)
    print()
    print('p1 from start to (21, 31), should be about 1.414')
    print('Actually is:', p1.get_distance_from_start())

    p1.move_by(29, 39)
    print()
    print('p1 from start to (50, 70), should be about 50.0')
    print('Actually is:', p1.get_distance_from_start())

    p2 = Point(1, 1)
    print()
    print('p2 from start to (1, 1), should be about 0.0')
    print('Actually is:', p2.get_distance_from_start())

    p2.move_to(11, 1)
    print()
    print('p2 from start to (11, 1), should be about 10.0')
    print('Actually is:', p2.get_distance_from_start())

    p2.move_to(999, 999)
    p2.move_to(1, 1)
    print()
    print('p2 from start to (1, 1), should be about 0.0')
    print('Actually is:', p2.get_distance_from_start())


def run_test_get_distance_traveled():
    """
    Tests the   get_distance_traveled   method of the Point class.

    Here is the specification for the   get_distance_traveled   method:
    What comes in:
       -- self
    What goes out:  Returns the sum of all the distances that
       this Point has "moved" via calls to   move_to  and/or   move_by.
    Side effects:
       ** You figure out WHETHER OR NOT side effect(s) MUST happen! **

    EXAMPLE: The following shows   get_distance_traveled   in action.
    You may also use this example to test this method.
    """
    # ------------------------------------------------------------------
    # DONE: 11.  Follow the same instructions as in DONE 3 above,
    #    but for the  get_distance_traveled  method specified above.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   get_distance_traveled   method')
    print('of the Point class.')
    print('-----------------------------------------------------------')


    p1 = Point(20, 30)
    p1.move_to(21, 30)
    p1.move_to(21, 38)
    print()
    print('Expected p1 has traveled 9.0')
    print('Actual:', p1.get_distance_traveled())

    p1.move_by(1, 1)
    print()
    print('Expected p1 has now traveled about 10.414')
    print('Actual:', p1.get_distance_traveled())

    p2 = Point(0, 0)
    p3 = Point(100, 22)
    p4 = Point(0, 555)
    for k in range(100):
        p2.move_by(0, k + 1)
        p3.move_by(k + 1, 0)
        p4.move_to(k + 1, 555)

    print()
    print('Expected p2 has now traveled', 101 * 50.0)
    print('Actual:', p2.get_distance_traveled())
    print('Expected p3 has now traveled', 101 * 50.0)
    print('Actual:', p3.get_distance_traveled())
    print('Expected p4 has now traveled 100.0')
    print('Actual:', p4.get_distance_traveled())

def run_test_closer_to():
    """
    Tests the   closer_to   method of the Point class.

    Here is the specification for the   closer_to   method:
    What comes in:
       -- self
       -- a Point object p2
       -- a Point object p3
    What goes out:
        Returns whichever of p2 and p3 this Point is closer to.
        (Just to be specific, it should return p2 in the case of a tie.)
    Side effects:
       ** You figure out WHETHER OR NOT side effect(s) MUST happen! **

    EXAMPLE: The following shows   closer_to   in action.
    You may also use this example to test this method.
    """
    # ------------------------------------------------------------------
    # DONE: 12.  Follow the same instructions as in DONE 3 above,
    #    but for the  closer_to  method specified above.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   closer_to   method of the Point class.')
    print('-----------------------------------------------------------')


    p1 = Point(10, 20)
    p2 = Point(15, 20)
    p3 = Point(14, 24)

    print()
    print('Expected:', p2)
    print('Actual:  ', p1.closer_to(p2, p3))
    print('Expected:', p2)
    print('Actual:  ', p1.closer_to(p3, p2))

    print()
    print('Expected:', p1)
    print('Actual:  ', p1.closer_to(p1, p3))
    print('Expected:', p2)
    print('Actual:  ', p2.closer_to(p3, p2))
    print('Expected:', p3)
    print('Actual:  ', p3.closer_to(p3, p3))

    print()
    p4 = p1.clone()
    p5 = p1.clone()
    print('Expected:', p4)
    print('Actual:  ', p1.closer_to(p4, p5))
    print('Expected: True')
    print('Actual:  ', p1.closer_to(p4, p5) is p4)
    print('Expected: False')
    print('Actual:  ', p1.closer_to(p4, p5) is p5)



def run_test_halfway_to():
    """
    Tests the   halfway_to   method of the Point class.

    Here is the specification for the   halfway_to   method:
    What comes in:
       -- self
       -- a Point object p2
    What goes out:
        Returns a new Point that is halfway between this Point and p2.
        That is, the x coordinate of the new Point is the average
        of the x coordinate of this Point and the x coordinate of p2,
        and likewise for the new Point's y coordinate.
    Side effects:
       ** You figure out WHETHER OR NOT side effect(s) MUST happen! **

    EXAMPLE: The following shows   halfway_to   in action.
    You may also use this example to test this method.
    """
    # ------------------------------------------------------------------
    # DONE: 13.  Follow the same instructions as in DONE 3 above,
    #    but for the  halfway_to  method specified above.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   halfway_to   method of the Point class.')
    print('-----------------------------------------------------------')


    p1 = Point(10, 20)
    p2 = Point(30, 100)

    print()
    print('Should be: Point(20.0, 60.0)')
    print('Actual is:', p1.halfway_to(p2))
    print('Should be: Point(20.0, 60.0)')
    print('Actual is:', p2.halfway_to(p1))

    print()
    print('Should be: Point(10.0, 20.0)')
    print('Actual is:', p1.halfway_to(p1))

    p3 = Point(-10, 20)
    p4 = Point(30, -100)

    print()
    print('Should be: Point(10.0, -40.0)')
    print('Actual is:', p3.halfway_to(p4))
    print('Should be: Point(10.0, -40.0)')
    print('Actual is:', p3.halfway_to(p4))

    print()
    print('Should be: Point(-10.0, 20.0)')
    print('Actual is:', p3.halfway_to(p3))



# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
