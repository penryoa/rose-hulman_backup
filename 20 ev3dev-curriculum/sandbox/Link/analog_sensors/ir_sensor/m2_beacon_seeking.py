#!/usr/bin/env python3
"""
The goal of this module is to drive towards the beacon (the IR remote in beacon mode) and stop your robot right in front
of the beacon.  To put the IR Remote into beacon mode, press the button at the top of the remote and make sure the green
LED is on. Use channel 1 for this module.

Your program will call a function called seek_beacon that will run until the distance to the beacon is 0.  Once that
function gets the robot to that location it will stop the robot and return.  Within the main function the user will be
prompted if they want to find the beacon again (presumably you move it first) or quit.


Authors: David Fisher and Link.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.
import traceback

import ev3dev.ev3 as ev3
import time
import math

import robot_controller as robo


def main():
    print("--------------------------------------------")
    print(" Beacon seeking")
    print("--------------------------------------------")
    ev3.Sound.speak("Beacon seeking")

    robot = robo.Snatch3r()
    try:
        while True:
            result = seek_beacon(robot)


            # DONE: 5. Save the result of the seek_beacon function (a bool), then use that value to only say "Found the
            # beacon" if the return value is True.  (i.e. don't say "Found the beacon" if the attempts was cancelled.)
            if result is True:
                ev3.Sound.speak("Found the beacon")

            command = input("Hit enter to seek the beacon again or enter q to quit: ")
            if command == "q":
                break
    except:
        traceback.print_exc()
        ev3.Sound.speak("Error")

    print("Goodbye!")
    ev3.Sound.speak("Goodbye").wait()


def seek_beacon(robot):
    """
    Uses the IR Sensor in BeaconSeeker mode to find the beacon.  If the beacon is found this return True.
    If the beacon is not found and the attempt is cancelled by hitting the touch sensor, return False.

    Type hints:
      :type robot: robo.Snatch3r
      :rtype: bool
    """

    # DONE: 2. Create a BeaconSeeker object on channel 1.
    beacon_seeker = ev3.BeaconSeeker(channel=1)
    forward_speed = 300
    turn_speed = 100
    left_motor = ev3.LargeMotor(ev3.OUTPUT_B)
    right_motor = ev3.LargeMotor(ev3.OUTPUT_C)

    while not robot.touch_sensor.is_pressed:
        # The touch sensor can be used to abort the attempt (sometimes handy during testing)

        # DONE: 3. Use the beacon_seeker object to get the current heading and distance.
        current_heading = beacon_seeker.heading  # use the beacon_seeker heading
        current_distance = beacon_seeker.distance # use the beacon_seeker distance
        if current_distance == -128:
            # If the IR Remote is not found just sit idle for this program until it is moved.
            print("IR Remote not found. Distance is -128")
            robot.stop()
        else:
            # DONE: 4. Implement the following strategy to find the beacon.
            # If the absolute value of the current_heading is less than 2, you are on the right heading.
            #     If the current_distance is 0 return from this function, you have found the beacon!  return True
            #     If the current_distance is greater than 0 drive straight forward (forward_speed, forward_speed)
            # If the absolute value of the current_heading is NOT less than 2 but IS less than 10, you need to spin
            #     If the current_heading is less than 0 turn left (-turn_speed, turn_speed)
            #     If the current_heading is greater than 0 turn right  (turn_speed, -turn_speed)
            # If the absolute value of current_heading is greater than 10, then stop and print Heading too far off
            #
            # Using that plan you should find the beacon if the beacon is in range.  If the beacon is not in range your
            # robot should just sit still until the beacon is placed into view.  It is recommended that you always print
            # something each pass through the loop to help you debug what is going on.  Examples:
            #    print("On the right heading. Distance: ", current_distance)
            #    print("Adjusting heading: ", current_heading)
            #    print("Heading is too far off to fix: ", current_heading)

            # Here is some code to help get you started
            if math.fabs(current_heading) < 2:
                # Close enough of a heading to move forward
                print("On the right heading. Distance: ", current_distance)
                # You add more!
                if current_distance == 0:
                    left_motor.run_forever(speed_sp = forward_speed)
                    right_motor.run_forever(speed_sp = forward_speed)
                    time.sleep(0.5)
                    left_motor.stop()
                    right_motor.stop()
                    return True
                if math.fabs(current_distance) > 0:
                    left_motor.run_forever(speed_sp = forward_speed)
                    right_motor.run_forever(speed_sp = forward_speed)

            if math.fabs(current_heading) >= 2:
                if math.fabs(current_heading) < 10:
                    if current_heading < 0:
                        left_motor.run_forever(speed_sp=-turn_speed)
                        right_motor.run_forever(speed_sp=turn_speed)
                    if current_heading > 0:
                        left_motor.run_forever(speed_sp=turn_speed)
                        right_motor.run_forever(speed_sp=-turn_speed)
                print("Adjusting heading: ", current_heading)

            if math.fabs(current_heading) >= 10:
                left_motor.stop()
                right_motor.stop()
                print("Heading too far off to fix: ", current_heading)



        time.sleep(0.2)

    # The touch_sensor was pressed to abort the attempt if this code runs.
    print("Abandon ship!")
    robot.stop()
    return False

    # TODO: 6. Demo your program by putting the beacon within a few feet of the robot, within 30 degrees of straight in
    # front.  The robot should drive to and stop at the beacon.  After a successful run move the beacon then do it again
    # for the demo.  During testing if your robot fails to find the beacon remember that you can press the touch sensor
    # to abandon ship on the attempt. ;) You must demo 2 successful finds to check off but you can have as many attempts
    # as you need.
    #
    # Observations you should make, using the BeaconSeeker mode is pretty easy to code, but there sure is a lot that can
    # go wrong in the real world using it. :)


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
