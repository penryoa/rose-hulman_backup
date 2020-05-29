#!/usr/bin/env python3
"""
The goal of this module to combine using the Pixy with the drive motors.  You will track a color using the Pixy and turn
the robot so that it is always facing the color signature.  You will need to teach Pixy a color before starting to
implement the code, then make the robot always face the color as you move it around.  The robot will only spin and never
move forwards or backwards.

Authors: David Fisher and Link.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

import ev3dev.ev3 as ev3
import time

import robot_controller as robop


def main():
    print("--------------------------------------------")
    print(" Color tracking")
    print("--------------------------------------------")
    ev3.Sound.speak("Color tracking").wait()
    print("Press the touch sensor to exit this program.")

    # This code assumes you have setup the pixy object on the Snatch3r class.
    # Add the pixy property to that class if you have not done so already.
    robot = robo.Snatch3r()
    robot.pixy.mode = "SIG1"
    turn_speed = 100
    left_motor = ev3.LargeMotor(ev3.OUTPUT_B)
    right_motor = ev3.LargeMotor(ev3.OUTPUT_C)

    while not robot.touch_sensor.is_pressed:

        # DONE: 2. Read the Pixy values for x and y
        # Print the values for x and y
        pixy_x = robot.pixy.value(1)
        pixy_y = robot.pixy.value(2)
        width = robot.pixy.value(3)
        height = robot.pixy.value(4)
        print("(X,Y) = ({},{}) Width = {} Height = {}".format(pixy_x, pixy_y, width, height))

        # DONE: 3. Use the x value to turn the robot
        #   If the Pixy x value is less than 150 turn left (-turn_speed, turn_speed)
        #   If the Pixy x value is greater than 170 turn right (turn_speed, -turn_speed)
        #   If the Pixy x value is between 150 and 170 stop the robot
        # Continuously track the color until the touch sensor is pressed to end the program.

        if pixy_x < 150:
            left_motor.run_forever(speed_sp = -turn_speed)
            right_motor.run_forever(speed_sp = turn_speed)
        if pixy_x > 170:
            left_motor.run_forever(speed_sp = turn_speed)
            right_motor.run_forever(speed_sp = -turn_speed)
        if pixy_x > 150:
            if pixy_x < 170:
                left_motor.stop()
                right_motor.stop()

        time.sleep(0.25)

    print("Goodbye!")
    ev3.Sound.speak("Goodbye").wait()

# TODO: 4. Call over a TA or instructor to sign your team's checkoff sheet.
#
# Observations you should make, the Pixy cam could potentially be used for a lot of cool project ideas, but if you
# decide to use the Pixy you should be aware of what it does well and what it doesn't do well.


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
