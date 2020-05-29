#!/usr/bin/env python3
"""
This module is a sample program that you can transfer from your computer to
the EV3 computer, then run on the EV3.

All members of your team will be using the same robot so when this file is
copied from your computer to the robot it will override the prior student's
code.


  Demo program that shows some of the driving API for ev3.LargeMotor objects.
    
Authors: Dave Fisher and PUT_YOUR_NAME_HERE.
"""  # TODO: 1. PUT YOUR NAME IN THE ABOVE LINE.

# ------------------------------------------------------------------
# TODO: 2
#   Follow along with the lecture to run this program in 2 ways:
#    - Using SSH from your computer
#    - Marking the file as an executable and running it via Brickman
# ------------------------------------------------------------------

import ev3dev.ev3 as ev3
import time


def main():
    """ Creates the LargeMotor objects and passes them to other functions. """
    print("--------------------------------------------")
    print("  Drive demo")
    print("--------------------------------------------")
    ev3.Sound.speak("Drive demo").wait()

    # ------------------------------------------------------------------
    # TODO: 3
    #   Read the code and see if you can start learning the API by example
    #       ** Ask a TA or your professor for help as needed. **
    # ------------------------------------------------------------------

    # Connect two large motors on output ports B and C
    left_motor = ev3.LargeMotor(ev3.OUTPUT_B)
    right_motor = ev3.LargeMotor(ev3.OUTPUT_C)

    # Check that the motors are actually connected
    assert left_motor.connected
    assert right_motor.connected

    # Displays a list of commands for a motor
    print(left_motor.commands)

    # Drive in an L shape
    drive_straight(left_motor, right_motor, 4)
    turn_90(left_motor, right_motor)
    drive_straight(left_motor, right_motor, 2)

    shutdown(left_motor, right_motor)  # Say Goodbye


def drive_straight(left_motor, right_motor, time_s):
    """Shows an example of using run_forever at a given speed."""
    print("Driving straight...")
    left_motor.run_forever(speed_sp=400)
    right_motor.run_forever(speed_sp=400)
    time.sleep(time_s)
    left_motor.stop(stop_action="brake")
    right_motor.stop(stop_action="brake")
    # There are other types of drive commands too, we are just learning one approach now.


def turn_90(left_motor, right_motor):
    """Shows an example of using run_to_rel_pos at a given speed."""
    print("Turning...")
    motor_turns_deg = 486  # May require some tuning depending on your surface!
    left_motor.run_to_rel_pos(position_sp=motor_turns_deg, speed_sp=400)
    right_motor.run_to_rel_pos(position_sp=-motor_turns_deg, speed_sp=400)
    # Note, that there is no time.sleep needed when using the commands above, but we must add a
    # wait_while command so that the execution of code doesn't get ahead of the robot's physical movements.
    left_motor.wait_while("running")  # Wait for the turn to finish
    right_motor.wait_while("running")  # (optional) Make sure both motors are done (they finish at the same time)
    ev3.Sound.beep().wait()  # Fun little beep


def shutdown(left_motor, right_motor):
    """Close the program"""
    left_motor.stop(stop_action="coast")
    right_motor.stop(stop_action="coast")
    print("Goodbye")
    ev3.Sound.speak("Goodbye").wait()


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
