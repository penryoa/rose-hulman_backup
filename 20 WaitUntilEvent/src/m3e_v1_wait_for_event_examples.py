"""
This module demonstrates the WAIT-FOR-EVENT pattern using
the WHILE TRUE pattern:

   while True:
       ...
       if <event has occurred>:
           break
       ...

See the module that is the COMPANION to this one for the same examples,
but using the ITCH pattern for WHILE loops.

Authors: David Mutchler, Amanda Stouder, Valerie Galluzzi,
         Chandan Rupakheti, Katie Dion, Claude Anderson, Delvin Defoe,
         Curt Clifton, Matt Boutell, and their colleagues.
"""
# ----------------------------------------------------------------------
# Students: Read and run this program.  There is nothing else
#           for you to do in here.  Just use it as an example.
#
#           Before you leave this example,
#   *** MAKE SURE YOU UNDERSTAND THE   WAIT-FOR-EVENT   PATTERN,
#   *** with its use of   while True:   and   break.
# ----------------------------------------------------------------------

import math
import random
import rosegraphics as rg


def main():
    """ Demonstrates applications of the wait-for-event pattern. """
    demonstrate_wait_for_circle_to_reach_edge()
    demonstrate_wait_for_sentinel()
    demonstrate_wait_for_small_enough_number()


# ----------------------------------------------------------------------
# Demonstrates waiting for a sequence of "growing" circles
# to reach the edge of the window in which they are drawn.
# ----------------------------------------------------------------------
def demonstrate_wait_for_circle_to_reach_edge():
    """
    Demonstrates the   wait_for_event   pattern, where the event
    is that a growing graphical object has grown beyond the window size.

    This particular example draws, moves and grows purple circles
    until a circle extends beyond the border of the window.
    """
    print()
    print('---------------------------------------------------------')
    print('Demonstrating the WAIT FOR EVENT pattern in graphics:')
    print('See the graphics window that pops up.')
    print('---------------------------------------------------------')

    window = rg.RoseWindow(700, 450, 'Animation until a TOO-BIG circle')

    x = 20
    y = 50
    radius = 5
    k = 0
    window.continue_on_mouse_click()

    while True:
        # Construct and draw a purple circle.
        circle = rg.Circle(rg.Point(x, y), radius)
        circle.fill_color = 'purple'
        circle.attach_to(window)

        # If the circle has reached a right/bottom border of the window,
        # break out of the loop
        right_edge = x + radius
        bottom_edge = y + radius

        if right_edge >= window.width or bottom_edge >= window.height:
            break

        # Make the next circle be down, to the right, and bigger.
        k = k + 1
        x = x + 2
        y = y + 1
        radius = radius + (k / 100)

        # Render.  Allow a little time to elapse,
        #          else the animation flashes by.
        window.render(0.01)

    window.close_on_mouse_click()


# ----------------------------------------------------------------------
# Demonstrates waiting for a "sentinel" value to be input.
# ----------------------------------------------------------------------
def demonstrate_wait_for_sentinel():
    """
    Demonstrates the   wait_for_event   pattern, where the event
    is inputting a SENTINEL value to signal the end of user input.

    This particular example inputs positive integers and processes them
    by printing their square roots, and when input is finished,
    printing the sum of those square roots.  User input stops when
    the user inputs the agreed-upon SENTINEL value of -1.
    """
    print()
    print('----------------------------------------------')
    print('Demonstrating the WAIT FOR SENTINEL pattern:')
    print('----------------------------------------------')

    total = 0
    while True:
        number = int(input('Enter a positive integer, or -1 to quit: '))
        if number == -1:
            break
        print('The square root of', number, 'is', math.sqrt(number))
        print()
        total = total + math.sqrt(number)

    print('The total of the square roots is', total)


# ----------------------------------------------------------------------
# Demonstrates waiting for a "small enough" random number
# to be generated.
# ----------------------------------------------------------------------
def demonstrate_wait_for_small_enough_number():
    """
    Demonstrates the   wait_for_event   pattern, by generating
    random numbers between 1 and 50, inclusive,
    and stopping when the following event occurs:
      a number less than or equal to 10 is generated.
    """
    print()
    print('----------------------------------------------------------')
    print('Demonstrating WAIT FOR A SMALL ENOUGH')
    print('  randomly generated number')
    print('----------------------------------------------------------')

    print('I will now genenerate random integers')
    print('between 1 and 50, stopping when a generated')
    print('random integer is less than or equal to 10.')
    print()

    n = wait_for_small_enough_number(10, 50)

    print()
    print(n, 'random integers between 1 and 50 were generated')
    print('before one was less than or equal to 10.')


def wait_for_small_enough_number(small_number, max_number):
    """
    What comes in:  Two non-negative integers.
    What goes out:
      Returns the number of random integers that are generated,
      as described below.
    Side effects:
      -- Repeatedly generates random integers between 1 and max_number,
           inclusive, where max_number is the second given integer.
      -- Stops when the random integer is less than or equal to
           small_number, where small_number is the first given integer.
      -- Prints the random numbers as they are generated.
    """
    count = 0
    while True:
        count = count + 1
        number = random.randrange(1, max_number + 1)
        print(number)
        if number <= small_number:
            break

    return count


# ----------------------------------------------------------------------
# If this module is running at the top level (as opposed to being
# imported by another module), then call the 'main' function.
# ----------------------------------------------------------------------
if __name__ == '__main__':
    main()
