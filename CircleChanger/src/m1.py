"""
A problem in which to practice:
  -- IMPLEMENTING a CLASS
  -- using SEQUENCES

Authors: Valerie Galluzzi, David Mutchler, Dave Fisher, Amanda Stouder,
         their colleagues and Olivia Penry.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

import rosegraphics as rg
import random
import sys
import time
import m1t_test_CircleChanger as m1_tests

########################################################################
# IMPORTANT:
#   Your instructor will help you get started on this exercise.
########################################################################


def main():
    """
    Calls the   TEST   functions in this module, but ONLY if
    the method to be tested has at least a partial implementation.
    That is, a  TEST   function will not be called
    until you begin work on the code that it is testing.
    """
    if m1_tests.is_implemented('__init__', 20):
        run_test_init()
    if m1_tests.is_implemented('get_distance_from'):
        run_test_get_distance_from()
    if m1_tests.is_implemented('swell_or_shrink_once'):
        run_test_swell_or_shrink_once()
    if m1_tests.is_implemented('swell_or_shrink_repeatedly', 4):
        run_test_swell_or_shrink_repeatedly()
    if m1_tests.is_implemented('swallow'):
        run_test_swallow()
    if m1_tests.is_implemented('change_color'):
        run_test_change_color()
    if m1_tests.is_implemented('change_to_original_color'):
        run_test_change_to_original_color()
    if m1_tests.is_implemented('change_to_next_color_in_tuple'):
        run_test_change_to_next_color_in_tuple()


########################################################################
# The   CircleChanger   class (and its methods) begins here.
########################################################################
class CircleChanger(object):
    """
    A CircleChanger has an rg.Circle and a list of colors.
    Methods can draw the circle and change its characteristics,
     """

    def __init__(self, x, y, radius, fill_color, colors):
        """
        What comes in:
          -- self
          -- Three integers: x, y and radius
          -- A string (or other value) that represents a color
               in RoseGraphics
          -- A non-empty tuple of strings that represent colors
               in RoseGraphics, e.g.
                 ('blue', 'gray', 'light green', 'yellow', 'black')
        What goes out: Nothing (i.e., None).
        Side effects:
          -- Constructs an rg.Circle whose:
               -- center is an rg.Point at the given x and y
               -- radius is the given radius
               -- fill_color is the given fill color
          -- Stores that rg.Circle in the instance variable:
                   circle
          -- Stores the given tuple of colors in the instance variable:
                   colors
          -- [Eventually] Sets additional instance variables
                          as needed for other methods.
        Example: See   run_test_init   below for an example.
        Type hints:
            :type x: int
            :type y: int
            :type radius: int
            :type fill_color: str
            :type colors: sequence of str
        """
        self.animation_factor = 1  # Smaller => faster animations
        self.seconds_to_sleep = 0.5  # Default for each call to draw
        # --------------------------------------------------------------
        # Change the above "animation_factor" if the animations
        # go too fast or too slow for your tastes.  Setting it to N
        # makes the animations go N times SLOWER.
        # --------------------------------------------------------------

        ################################################################
        # DONE: 2.
        #   First, READ the doc-string (specification) above.
        #   Second, READ the   run_test_init   function (below).
        #   Third, implement and test this method.
        #
        # Each TEST function gives an EXAMPLE that helps you understand
        # the SPECIFICATION of the method.  That is why you read the
        # TEST function before implementing the method that it tests.
        ################################################################

        self.colors = colors
        self.circle = rg.Circle(rg.Point(x,y), radius)
        self.circle.fill_color = fill_color
        self.fill = fill_color
        self.count = -1



    def __repr__(self):
        """
        What comes in:
          -- self
        What comes out:
          Returns a string that represents this CircleChanger.
          The string has the form of the following example:
            ANIMATED_CIRCLE:
              Circle: center=(10, 23), radius=50, fill_color=None,
                      outline_color=black, outline_thickness=1.
              colors: ('blue', 'gray', 'light green', 'black')
        Side effects: None.
        """
        # --------------------------------------------------------------
        # We have already implemented this  __repr__  function for you.
        # Do NOT modify it.
        # --------------------------------------------------------------

        # Break the string for the circle into two lines:
        circle_string = repr(self.circle)
        circle_string = circle_string.replace(' outline_color',
                                              ('\n' +
                                               '          ' +
                                               'outline_color'))

        # Use lower-case C for 'circle' to match instance-variable name:
        circle_string = circle_string.replace('Circle', 'circle')

        s = 'CircleChanger:\n'
        s = s + '  ' + circle_string + '\n'
        s = s + '  ' + 'colors: ' + repr(self.colors)
        return s

    def draw(self, message=None):
        # ###### DO NOT MODIFY THIS METHOD #######
        """
        What comes in:
          -- self
          -- an optional message
        What comes out:  Nothing (i.e., None)

        Side effects:
          This method draws and renders this CircleChanger
          on an rg.RoseWindow.

          Then, if   message   is:
            None (the default):
                This method pauses for the default number of seconds
                (multiplied by self.animation_factor).
            Any number:
                This method pauses for the given number of seconds
                (multiplied by self.animation_factor).
                There is no pause if the number is 0.
            Anything else:
                This method prints the message on the rg.RoseWindow
                and waits for the user to click the mouse.
        """
        # --------------------------------------------------------------
        # We have already implemented this  draw  function for you.
        # Do NOT modify it.
        #
        # You can do this entire exercise knowing only that   draw
        #    "Draws this CircleChanger on a window,
        #     pausing briefly after doing so."
        #
        # per the doc_string (specification) above.
        # You do NOT need to know HOW   draw   does its work.
        #
        # But feel free to ask your instructor about it if you
        # are curious as to how   draw   works.
        # --------------------------------------------------------------

        # ###### DO NOT MODIFY THIS METHOD #######
        m1_tests.draw(self, message)

    def get_distance_from(self, point):
        """
        What comes in:
          -- self
          -- An rg.Point
        What goes out:  Returns the distance between the center of
           this CircleChanger's circle and the given rg.Point
        Side effects: None.

        Example: See   run_test_get_distance_from   below for examples.

        Type hints:
            :type point: rg.Point
        """
        ################################################################
        # DONE: 3.
        #   First, READ the doc-string (specification) above.
        #   Second, READ the   run_test_get_distance_from   function
        #   (below).  Third, implement and test this method.
        #
        # *** YOU   ** MUST **   USE the relevant method
        #   of the    rg.Point     class to compute this distance.
        #   NO CREDIT if you use the distance formula here.
        ################################################################

        cir_center = self.circle.center
        dis = point.get_distance_from(cir_center)
        return dis





    def swell_or_shrink_once(self, amount_to_swell_or_shrink):
        """
        What comes in:
          -- self
          -- An integer that indicates how much this CircleChanger's
               circle is to swell or shrink by, that is, how much
               this CircleChanger's circle's radius is to change by.
        What goes out: Nothing (i.e., None).

        Side effects:
        The following happens IN THE ORDER LISTED.
          1. The radius of this CircleChanger's circle changes
               by adding the given  amount_to_swell_or_shrink  to it.

               So the circle:
                 -- swells if   amount_to_swell_or_shrink   is positive
                 -- shrinks if  amount_to_swell_or_shrink   is negative.

               Exception:  If the change would make the radius
                 LESS THAN 1, the radius should be set to 1.

          2. The outline_thickness of this CircleChanger's circle
               is set to a random number chosen from [3, 15]
               (that is, between 3 and 15, inclusive).

          3. The fill color of this CircleChanger's circle
               changes to a color chosen at random from this
               CircleChanger's list of colors.

        Example: See   run_test_swell_or_shrink_once   below for examples.

        Type hints:
            :type amount_to_swell_or_shrink: int
        """
        ################################################################
        # DONE: 4.
        #   First, READ the doc-string (specification) above.
        #   Second, READ the   run_test_swell_or_shrink_once   function
        #   (below).  Third, implement and test this method.
        ################################################################
        #
        # IMPORTANT  ** HINT ** Regarding randomness:
        #
        #   The following statement chooses a random number between
        #   3 and 15, inclusive:
        #       r = random.randrange(3, 16)
        #   Note the 16 --  randrange   is like   range   in that
        #   it does NOT include the ending number.
        #   Use the above to help set the outline_thickness.
        #
        #   To get a random COLOR, you need to get a random INDEX
        #   into the   self.colors   tuple.  So something like:
        #       r_index = random.randrange(0, ??)
        #
        #   where you figure out what ?? must be, and then
        #
        #       r_color = self.colors[r_index]
        #
        #   will give a randomly chosen color from self.colors.
        #
        #   Simply   ** ASK FOR HELP **
        #            if this does not make sense to you.
        ################################################################

        self.circle.radius = self.circle.radius + amount_to_swell_or_shrink
        if self.circle.radius < 1:
            self.circle.radius = 1
        self.circle.outline_thickness = random.randrange(3,16)
        r_index = random.randrange(0,len(self.colors))
        self.circle.fill_color = self.colors[r_index]







    def swell_or_shrink_repeatedly(self,
                                   amount_to_swell_or_shrink,
                                   times_to_swell_or_shrink):
        """
        What comes in:
          -- self
          -- Two integers (see   Side effects  below for what they do)
        What goes out: Nothing (i.e., None).
        Side effects:
          Does the following 4 steps repeatedly:
            1. Calls its
                 swell_or_shrink_once
               method with the given amount_to_swell_or_shrink.
            2. Draws this CircleChanger
                 (by using its  draw   method with no arguments).
            3. Calls its
                 swell_or_shrink_once
               method with an argument that will UNDO the amount
               of swell/shrink it just did.
            4. Draws this CircleChanger
                 (by using its  draw   method with no arguments).

          The argument  times_to_swell_or_shrink  says how many times
          to do the above 4 steps.

        Example:
          Suppose that the radius of this CircleChanger's circle
          is currently 50.  Suppose further that:
            -- amount_to_swell_or_shrink is 10
            -- times_to_swell_or_shrink  is 3.
          Then this method would:
            -- Increase the circle's radius to 60.
                 Draw the CircleChanger
                 (by using its   draw   method with no arguments).
            -- Decrease the circle's radius back to 50.
                 Draw the CircleChanger again.
            -- Increase the circle's radius to 60.
                 Draw the CircleChanger again.
            -- Decrease the circle's radius back to 50.
                 Draw the CircleChanger again.
            -- Increase the circle's radius to 60.
                 Draw the CircleChanger again.
            -- Decrease the circle's radius back to 50.
                 Draw the CircleChanger again.
          So, 3 times it did an increase by 10 followed by a decrease
          by 10.  Since the  draw  method pauses briefly each time
          it is called, this creates an animation.

        Type hints:
            :type amount_to_swell_or_shrink: int
            :type times_to_swell_or_shrink:  int
        """
        ################################################################
        # DONE: 5.
        #   First, READ the doc-string (specification) above.
        #   Second, READ the  run_test_swell_or_shrink_repeatedly  function
        #   (below).  Third, implement and test this method.
        ################################################################

        for k in range(2*times_to_swell_or_shrink):
            if k%2 == 0:
                self.swell_or_shrink_once(amount_to_swell_or_shrink)
                self.draw()
            else:
                self.swell_or_shrink_once(-1*amount_to_swell_or_shrink)
                self.draw()



    def swallow(self, other_circle_changer):
        """
        What comes in:
          -- self
          -- Another CircleChanger
        What goes out:
          -- Returns a new CircleChanger:
             -- whose circle is a new rg.Circle that:
                -- is centered at the point that is HALFWAY between
                     the center of this CircleChanger's circle and
                     the center of the other CircleChanger's circle.
                -- has radius that is HALF of the DISTANCE from:
                     -- the center of this CircleChanger's circle to
                     -- the center of the other CircleChanger's circle.
                -- has 'red' as its fill color
             -- whose tuple of colors a new tuple
                  that is this CircleChanger's tuple of colors
                  plus (that is, concatenated with)
                  the other CircleChanger's tuple of colors.
        Side effects: None.
        Example: See   run_test_swallow   below for examples.
        Type hints:
            :type other_circle_changer: CircleChanger
            :rtype CircleChanger
        """
        ################################################################
        # DONE: 6.
        #   First, READ the doc-string (specification) above.
        #   Second, READ the   run_test_swallow   function (below).
        #   Third, implement and test this method.
        #
        # *** YOU   ** MUST **   USE the relevant method(s)
        #   of the rg.Point class AND this class to compute
        #   the center and radius of the new CircleChanger.
        #   NO CREDIT if you use the distance formula here.
        ################################################################

        p1 = other_circle_changer.circle.center
        p2 = self.circle.center
        line = rg.Line(p1, p2)
        center = line.get_midpoint()
        radius = (1/2)*(other_circle_changer.get_distance_from(self.circle.center))
        c1 = CircleChanger(center.x, center.y, radius,'red', self.colors + other_circle_changer.colors)
        return c1


    def change_color(self, index_of_color):
        """
        What comes in:
          -- self
          -- An nonnegative integer that is less than the length
               of this CircleChanger's tuple of colors.
        What goes out: Nothing (i.e., None).
        Side effects:
          -- The fill_color of this CircleChanger's circle becomes
               the color in this CircleChanger's tuple of colors
               whose index is the given index_of_color.
        Example:
          If this CircleChanger's tuple of colors is:
             ('blue', 'gray', 'green', 'black')
          and if index_of_color is 2,
          then the fill_color of this CircleChanger becomes 'green'.
        Type hints:
            :type index_of_color: int
        """
        ################################################################
        # DONE: 7.
        #   First, READ the doc-string (specification) above.
        #   Second, READ the   run_test_change_color   function (below).
        #   Third, implement and test this method.
        ################################################################


        self.circle.fill_color = self.colors[index_of_color]





    def change_to_original_color(self):
        """
        What comes in:
          -- self
        What goes out: Nothing (i.e., None).
        Side effects:
          -- The fill_color of this CircleChanger's circle becomes
               the same color that it was when this CircleChanger
               was constructed.
        """
        ################################################################
        # DONE: 8.
        #   First, READ the doc-string (specification) above.
        #   Second, READ the   run_test_change_to_original_color   function
        #   (below).  Third, implement and test this method.
        ################################################################

        self.circle.fill_color = self.fill



    def change_to_next_color_in_tuple(self):
        """
        What comes in:
          -- self
        What goes out: Nothing (i.e., None).
        Side effects:
          -- The first time that this method is called, this
               CircleChanger's circle's fill color changes to the first
               (beginning) color in this CircleChanger's tuple of colors.
          -- Each time thereafter that this method is called, this
               CircleChanger's circle's fill color changes to the NEXT
               color in this CircleChanger's tuple of colors.
          -- It "wraps" when it reaches the end of the list.
        Example:
          If this CircleChanger's tuple of colors is:
             ('blue', 'gray', 'red')
          then:
            --- The first time this method is called,
                  it sets this CircleChanger's circle's fill color
                  to 'blue'.
            --- The second time this method is called,
                  it sets this CircleChanger's circle's fill color
                  to 'gray'.
            --- The third time ... to 'red'.
            --- The fourth time ... to 'blue'.
            --- The fifth time ... to 'gray'.
            --- The sixth time ... to 'red'.
            --- The seventh time ... to 'blue'.
            --- and so forth.
        Note: Other methods that affect this CircleChanger's circle's
        fill color have no effect on or interaction with this method.
        """
        ################################################################
        # DONE: 9.
        #   First, READ the doc-string (specification) above.
        #   Second, READ the   run_test_change_to_next_color_in_tuple
        #   function (below).  Third, implement and test this method.
        ################################################################

        self.count = self.count + 1
        self.circle.fill_color = self.colors[self.count%len(self.colors)]



########################################################################
# The TEST functions for the  CircleChanger  class begin here.
########################################################################

def run_test_init():
    """ Tests the   __init__   method of the CircleChanger class. """
    m1_tests.run_test_init()  # This runs OUR tests.

    # This is a VISUAL test.
    m1_tests.start_drawing('Testing: the   __init__   method')

    # Construct two CircleChanger objects:
    circle_changer1 = CircleChanger(100, 150, 100, 'blue', ('red', 'blue', 'green'))
    circle_changer2 = CircleChanger(300, 50, 30, 'yellow', ('green', 'gold'))

    # Print and draw them:
    print('After construction:')
    print(circle_changer1, '\n', circle_changer2, '\n', sep='')
    circle_changer1.draw(0.5)
    circle_changer2.draw("""
    A BLUE circle at (100, 150) with radius 100 and thickness 1,
    and a YELLOW circle at (250, 50) with radius 30 and thickness 1.""")

    # Change some of their characteristics, then print and redraw them:
    circle_changer1.circle.fill_color = circle_changer1.colors[2]
    circle_changer2.circle.outline_thickness = 10
    print('After changing characteristics:')
    print(circle_changer1, '\n', circle_changer2, sep='')
    circle_changer1.draw()
    circle_changer2.draw("""
    Now the leftmost (formerly BLUE) circle is GREEN
    and the YELLOW circle has a thicker outline.""")


def run_test_get_distance_from():
    m1_tests.run_test_get_distance_from()  # This runs OUR tests

    print()
    print('The following are tests from within  m1  itself.')

    circle_changer1 = CircleChanger(100, 50, 30, 'blue',
                                    ('red', 'blue', 'green'))
    circle_changer2 = CircleChanger(160, 50, 10, 'red',
                                    ('red',))
    circle_changer3 = CircleChanger(163, 46, 40, 'blue',
                                    ('red', 'green'))

    print()
    print('Expected:', 60.0)
    print('  Actual:',
          circle_changer1.
          get_distance_from(circle_changer2.circle.center))

    print()
    print('Expected:', 5.0)
    print('  Actual:',
          circle_changer2.
          get_distance_from(circle_changer3.circle.center))

    print()
    print('Expected: about', 63.126856)
    print('  Actual:      ',
          circle_changer1.
          get_distance_from(circle_changer3.circle.center))


def run_test_swell_or_shrink_once():
    """ Tests the   swell_or_shrink_once   method. """
    m1_tests.run_test_swell_or_shrink_once()  # This runs OUR tests
    random.seed(42)  # Lets us determine the results of the randomness

    # This is a VISUAL test.
    # Construct 3 CircleChanger objects, printing and drawing them.
    m1_tests.start_drawing('Testing: the  swell_or_shrink_once   method')
    print('After construction:')
    circle_changer1 = CircleChanger(200, 150, 30, 'blue',
                                    ('blue', 'yellow', 'green',
                                        'aquamarine', 'brown'))
    print(circle_changer1)
    circle_changer1.draw()

    circle_changer2 = CircleChanger(400, 100, 50, 'red', ('green',))
    print(circle_changer2)
    circle_changer2.draw()

    circle_changer3 = CircleChanger(300, 200, 10, 'green',
                                    ('yellow', 'blue'))
    print(circle_changer3)
    circle_changer3.draw("""
    A BLUE circle at (200, 150) with radius 30 and thickness 1,
    and a RED circle at (400, 100) with radius 50 and thickness 1,
    and a GREEN circle at (300, 200) with radius 10 and thickness 1.""")

    # For each of the three CircleChanger objects,
    #   apply the   swell_or_shrink_once  method, then redraw/reprint.
    print('\nAfter the first set of    swell_or_shrink_once   calls:')
    circle_changer1.swell_or_shrink_once(100)
    print(circle_changer1)
    circle_changer1.draw()

    circle_changer2.swell_or_shrink_once(-30)
    print(circle_changer2)
    circle_changer1.draw()

    circle_changer3.swell_or_shrink_once(40)
    print(circle_changer3)
    circle_changer3.draw("""
    After the first swell_or_shrink, now:
    Left circle is bigger (radius 130), still BLUE but thickness 13,
    Right circle is smaller (radius 20), GREEN with thickness 3,
    Middle circle is bigger (radius 50), YELLOW with thickness 6.""")

#     # Apply the   swell_or_shrink_once  method to each a second time:
#     circle_changer1.swell_or_shrink_once(-80)
#     circle_changer2.swell_or_shrink_once(30)
#     circle_changer3.swell_or_shrink_once(50)
#
#     print('After the second swell_or_shrink_once:')
#     print(circle_changer1, '\n',
#           circle_changer2, '\n',
#           circle_changer3, '\n', sep='')
#     m1_tests.start_drawing('After the second swell_or_shrink_once')
#     circle_changer1.draw()
#     circle_changer2.draw()
#     circle_changer3.draw("""
#     After the second swell_or_shrink:
#     The leftmost circle swelled to radius 130 and thickness 4,
#     and a RED circle at (400, 100) with radius 50 and thickness 1,
#     and a GREEN circle at (300, 200) with radius 10 and thickness 1.""")
#
#     circle_changer1.swell_or_shrink_once(-50)
#     circle_changer2.swell_or_shrink_once(100)
#     circle_changer3.swell_or_shrink_once(70)
#     print(circle_changer1)
#     circle_changer1.draw('Now GREEN, radius 80, thickness 10')


def run_test_swell_or_shrink_repeatedly():
    """ Tests the   swell_or_shrink_repeatedly   method. """
    m1_tests.run_test_swell_or_shrink_repeatedly()  # This runs OUR tests
    random.seed(999)  # Lets us determine the results of the randomness

    # This is a VISUAL test.
    # Construct 1 CircleChanger object, printing and drawing it.
    title = 'Testing: the  swell_or_shrink_repeatedly   method'
    m1_tests.start_drawing(title)

    print('After construction:')
    circle_changer1 = CircleChanger(200, 150, 30, 'blue',
                                    ('blue', 'yellow', 'green',
                                        'aquamarine', 'brown'))
    print(circle_changer1)
    circle_changer1.draw("""
    A BLUE circle at (200, 150) with radius 30 and thickness 1.""")

    # Apply the   swell_or_shrink_repeatedly   method.
    print('\nAfter the first   swell_or_shrink_repeatedly   call:')
    circle_changer1.animation_factor = 0.25  # faster animation

    circle_changer1.swell_or_shrink_repeatedly(50, 10)

    print(circle_changer1)
    circle_changer1.draw("""
    The circle should have swelled/shrunk by 50 10 times,
    changing color and thickness each time.
    It should end with the radius that it began (30),
    GREEN with thickness 10.""")


def run_test_swallow():
    """ Tests the   swallow   method. """
    m1_tests.run_test_swallow()  # This runs OUR tests.

    # This is a VISUAL test.
    # Construct 2 CircleChanger objects, printing and drawing them.
    title = 'Testing: the  swallow   method'
    m1_tests.start_drawing(title)

    print('After construction:')
    circle_changer1 = CircleChanger(200, 150, 50, 'blue',
                                    ('blue', 'yellow', 'green'))
    circle_changer1.draw()
    print(circle_changer1)
    circle_changer2 = CircleChanger(450, 180, 30, 'green',
                                    ('yellow', 'magenta'))
    print(circle_changer2)
    circle_changer2.draw("""
    A BLUE circle at (200, 150) with radius 50,
    and a GREEN circle at (450, 180) with radius 30.""")

    # Apply the   swallow   method.  Then print/draw the resulting
    # circle, drawing the original circles on top of it.
    print('\nAfter the first   swallow   call:')

    circle_changer3 = circle_changer1.swallow(circle_changer2)
    print(circle_changer3)
    circle_changer3.draw("""
    The RED circle should be centered at (325, 165) with radius about 126.
    It should cover approximately HALF of the BLUE and GREEN circles.""")

    circle_changer4 = CircleChanger(200, 150, 50, 'blue',
                                    ('blue', 'yellow', 'green'))
    circle_changer4.draw()
    circle_changer5 = CircleChanger(450, 180, 30, 'green',
                                    ('yellow', 'magenta'))
    circle_changer5.draw("""
    Here are the BLUE and GREEN circles again, on TOP of the RED circle.
    The RED should appear to be underneath approximately HALF
    of each of the BLUE and GREEN circles.""")

    # Test that the swallowing (red) CircleChanger
    # has a   colors   attribute that is the CONCATENATION
    # of the  colors   attributes of the swallowed CircleChangers.
    if circle_changer3.colors != (circle_changer4.colors +
                                  circle_changer5.colors):
        message = """The   colors   instance variable
    of the swallowing CircleChanger (the one RETURNED by
    the   swallow   method) is wrong.
    It should be the CONCATENATION of the   colors   instance
    variables of the two SWALLOWED Animated Circle objects.
    For example, if   circle_changer1.colors   is:
       (blue', 'yellow', 'green')
    and if   circle_changer2.colors   is:
       ('yellow', 'magenta')
    then   circle_changer1.swallow(circle_changer2)   should be:
       ('blue', 'yellow', 'green', 'yellow', 'magenta')
    and    circle_changer2.swallow(circle_changer1)   should be:
       ('yellow', 'magenta', 'blue', 'yellow', 'green')
    """
        time.sleep(0.5)
        print(message, file=sys.stderr)
        print('The   colors   instance  of   circle_changer1   is:',
              '\n   ', circle_changer1.colors, file=sys.stderr)
        print('The   colors   instance  of   circle_changer2   is:',
              '\n   ', circle_changer2.colors, file=sys.stderr)
        print('The   colors   instance  of the swallowing',
              file=sys.stderr)
        print('CircleChanger (circle_changer3)   is:',
              '\n   ', circle_changer3.colors, file=sys.stderr)
        print('but should be:', file=sys.stderr)
        print("  ('blue', 'yellow', 'green', 'yellow', 'magenta')",
              file=sys.stderr)
        time.sleep(1)


def run_test_change_color():
    """ Tests the   change_color   method. """
    m1_tests.run_test_change_color()  # This runs OUR tests.
    random.seed(77)  # Lets us determine the results of the randomness

    # This is a VISUAL test.
    # Construct 2 CircleChanger objects, printing and drawing them.
    title = 'Testing: the  change_color   method'
    m1_tests.start_drawing(title)

    print('After construction:')
    circle_changer1 = CircleChanger(100, 100, 70, 'black',
                                    ('blue', 'yellow'))
    circle_changer1.draw()
    print(circle_changer1)
    circle_changer2 = CircleChanger(350, 130, 50, 'purple',
                                    ('yellow', 'magenta', 'blue',
                                        'green', 'yellow', 'aquamarine'))
    print(circle_changer2)
    circle_changer2.draw("""
    A BLACK circle at (100, 100) with radius 70,
    and a PURPLE circle at (350, 130) with radius 50.""")

    # Apply the   change_color   method.  Then print/draw the results.
    print('\nAfter the first set of   change_color   calls:')

    circle_changer1.change_color(1)
    print(circle_changer1)
    circle_changer1.draw()

    circle_changer2.change_color(5)
    print(circle_changer2)
    circle_changer2.draw("""
    Same circles, but now YELLOW and AQUAMARINE.
    The next test will cycle the LEFT circle through:
      blue and yellow (repeating as needed)
    and the RIGHT circle through:
      yellow, magenta, blue, green, yellow, and aquamarine.""")

    # Another test:
    for k in range(6):
        circle_changer1.change_color(k % 2)
        circle_changer1.draw()

        circle_changer2.change_color(k)
        circle_changer2.draw()

    circle_changer1.draw("""
    Should have finished with YELLOW and AQUAMARINE.""")

    # This tests   change_color   and   swell_and_shrink_once
    # repeatedly.
    for k in range(20, 0, -1):
        circle_changer1.change_color(k % 2)
        circle_changer1.draw(0.05)
        circle_changer1.swell_or_shrink_once((-40 // k) * (k ** -1))
        circle_changer1.draw(0.05)

        circle_changer2.change_color(k % 6)
        circle_changer2.draw(0.05)
        circle_changer2.swell_or_shrink_once(50 // k)
        circle_changer1.draw(0.05)

    circle_changer1.draw("""
    Should have ended with two YELLOW circles:
    a TINY one on the LEFT and a HUGE one on the RIGHT.""")


def run_test_change_to_original_color():
    """ Tests the   change_to_original_color   method. """
    m1_tests.run_test_change_to_original_color()  # This runs OUR tests.
    random.seed(123)  # Lets us determine the results of the randomness

    # This is a VISUAL test.
    # Construct 2 CircleChanger objects, printing and drawing them.
    title = 'Testing: the  change_to_original_color   method'
    m1_tests.start_drawing(title)

    print('After construction:')
    circle_changer1 = CircleChanger(100, 100, 100, 'black',
                                    ('blue', 'green'))
    circle_changer1.draw()
    print(circle_changer1)
    circle_changer2 = CircleChanger(280, 100, 100, 'purple',
                                    ('yellow', 'magenta', 'blue',
                                        'green', 'yellow', 'aquamarine'))
    print(circle_changer2)
    circle_changer2.draw("""
    A BLACK circle at (100, 100) with radius 100,
    and a PURPLE circle at (280, 100) with radius 100.
    You will next see a bunch of colors,
    ending with BLACK and PURPLE again.""")

    # Flash through many color changes.  Then apply the
    #   change_to_original_color   method and print/draw the results.
    print('\nAfter the first set of  change_to_original_color  calls:')
    for k in range(30):
        circle_changer1.change_color(k % 2)
        circle_changer1.draw(0.05)

        circle_changer2.change_color(k % 6)
        circle_changer2.draw(0.05)

    circle_changer1.change_to_original_color()
    print(circle_changer1)
    circle_changer1.draw()

    circle_changer2.change_to_original_color()
    print(circle_changer2)
    circle_changer2.draw("""
    Should end as it started: BLACK and PURPLE.""")


def run_test_change_to_next_color_in_tuple():
    """ Tests the   change_to_next_color_in_tuple   method. """
#     m1_tests.change_to_next_color()  # This runs OUR tests.

    # This is a VISUAL test.
    # Construct 2 CircleChanger objects, printing and drawing them.
    title = 'Testing: the  change_to_next_color   method'
    m1_tests.start_drawing(title)

    print('After construction:')
    circle_changer1 = CircleChanger(100, 100, 100, 'black',
                                    ('blue', 'green', 'red'))
    circle_changer1.draw()
    print(circle_changer1)
    circle_changer2 = CircleChanger(280, 100, 40, 'purple',
                                    ('yellow', 'magenta', 'blue',
                                        'green', 'yellow', 'aquamarine'))
    print(circle_changer2)
    circle_changer2.draw("""
    A BLACK circle at (100, 100) with radius 100,
    and a PURPLE circle at (280, 100) with radius 40.
    You will next see a bunch of colors,
    cycling through BLUE, GREEN, RED (for the left, larger circle)
    and YELLOW, MAGENTA, BLUE, GREEN, YELLOW (again) and AQUAMARINE
    (for the right, smaller circle).""")

    # Cycle through the CircleChanger's tuples of colors:
    print('\nAfter the first set of  change_to_next_color  calls:')
    for _ in range(16):
        circle_changer1.change_to_next_color_in_tuple()
        circle_changer1.draw(0.25)

        circle_changer2.change_to_next_color_in_tuple()
        circle_changer2.draw(0.25)

    circle_changer2.draw("""
    Should end with circles: BLUE and GREEN.""")


# ----------------------------------------------------------------------
# If this module is running at the top level (as opposed to being
# imported by another module), then call the 'main' function.
# ----------------------------------------------------------------------
if __name__ == '__main__':
    main()
