#!/usr/bin/env python3
"""
This demo lets you see how to use an input prompt to test different drive speeds.
"""

import ev3dev.ev3 as ev3
import time


def main():
    print("--------------------------------------------")
    print("  Drive using input")
    print("--------------------------------------------")
    ev3.Sound.speak("Drive using input").wait()

    # Connect two large motors on output ports B and C
    left_motor = ev3.LargeMotor(ev3.OUTPUT_B)
    right_motor = ev3.LargeMotor(ev3.OUTPUT_C)

    # Check that the motors are actually connected
    assert left_motor.connected
    assert right_motor.connected

    for k in range(3):
        sp = int(input(str(k) + ". Enter a speed (0 to 900 dps): "))
        time_s = float(input("Enter a time to drive (seconds): "))
        left_motor.run_forever(speed_sp=sp)
        right_motor.run_forever(speed_sp=sp)
        time.sleep(time_s)
        left_motor.stop()
        right_motor.stop()
    print("Goodbye")
    ev3.Sound.speak("Goodbye").wait()


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
