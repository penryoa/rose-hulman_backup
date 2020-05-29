"""
  Library of EV3 robot functions that are useful in many different applications. For example things
  like arm_up, arm_down, driving around, or doing things with the Pixy camera.

  Add commands as needed to support the features you'd like to implement.  For organizational
  purposes try to only write methods into this library that are NOT specific to one tasks, but
  rather methods that would be useful regardless of the activity.  For example, don't make
  a connection to the remote control that sends the arm up if the ir remote control up button
  is pressed.  That's a specific input --> output task.  Maybe some other task would want to use
  the IR remote up button for something different.  Instead just make a method called arm_up that
  could be called.  That way it's a generic action that could be used in any task.
"""

import ev3dev.ev3 as ev3
import math
import time
MAX_SPEED = 900


class Snatch3r(object):
    """Commands for the Snatch3r robot that might be useful in many different programs."""
    def __init__(self):
        self.left_motor = ev3.LargeMotor(ev3.OUTPUT_B)
        self.right_motor = ev3.LargeMotor(ev3.OUTPUT_C)
        self.arm_motor = ev3.MediumMotor(ev3.OUTPUT_A)
        self.touch_sensor = ev3.TouchSensor()
        self.color_sensor = ev3.ColorSensor()
        self.ir_sensor = ev3.InfraredSensor()
        self.beacon_seeker = ev3.BeaconSeeker(channel=1)
        self.pixy = ev3.Sensor(driver_name="pixy-lego")


        assert self.left_motor.connected
        assert self.right_motor.connected
        assert self.arm_motor.connected
        assert self.touch_sensor.connected
        assert self.color_sensor
        assert self.ir_sensor
        assert self.beacon_seeker
        assert self.pixy

    def drive_inches(self, inches_target, speed_deg_per_second):
        speed = speed_deg_per_second
        if speed == 0:
            return
        dist_in = inches_target
        if dist_in == 0:
            return
        degrees_per_inch = 90
        motor_turns_needed_in_degrees = dist_in * degrees_per_inch

        self.left_motor.run_to_rel_pos(position_sp=motor_turns_needed_in_degrees, speed_sp=speed,
                                  stop_action=ev3.Motor.STOP_ACTION_BRAKE)
        self.right_motor.run_to_rel_pos(position_sp=motor_turns_needed_in_degrees, speed_sp=speed,
                                   stop_action=ev3.Motor.STOP_ACTION_BRAKE)

        self.left_motor.wait_while(ev3.Motor.STATE_RUNNING)
        self.right_motor.wait_while(ev3.Motor.STATE_RUNNING)

        ev3.Sound.beep().wait()

    def turn_degrees(self, degrees_to_turn, turn_speed_sp):
        if degrees_to_turn == 0:
            return
        if turn_speed_sp == 0:
            return

        self.left_motor.run_to_rel_pos(position_sp = 5 * degrees_to_turn, speed_sp = turn_speed_sp, stop_action = ev3.Motor.STOP_ACTION_BRAKE)
        self.right_motor.run_to_rel_pos(position_sp = -5 * degrees_to_turn, speed_sp = -turn_speed_sp, stop_action = ev3.Motor.STOP_ACTION_BRAKE)
        self.left_motor.wait_while(ev3.Motor.STATE_RUNNING)
        self.right_motor.wait_while(ev3.Motor.STATE_RUNNING)


        ev3.Sound.beep().wait()


    def arm_calibration(self):
        self.arm_motor.run_forever(speed_sp=MAX_SPEED)
        while True:
            if self.touch_sensor.is_pressed:
                break
            time.sleep(0.01)
        self.arm_motor.stop(stop_action=ev3.Motor.STOP_ACTION_BRAKE)
        ev3.Sound.beep()
        arm_revolutions_for_full_range = 14.2 * 360
        self.arm_motor.run_to_rel_pos(position_sp=-arm_revolutions_for_full_range)
        self.arm_motor.wait_while(ev3.Motor.STATE_RUNNING)
        ev3.Sound.beep()
        self.arm_motor.position = 0  # Calibrate the down position as 0 (this line is correct as is).


    def arm_up(self):
        self.arm_motor.run_forever(speed_sp=MAX_SPEED)
        while True:
            if self.touch_sensor.is_pressed:
                break
            time.sleep(0.01)
        self.arm_motor.stop(stop_action=ev3.Motor.STOP_ACTION_BRAKE)
        ev3.Sound.beep()


    def arm_down(self):
        arm_revolutions_for_full_range = 14.2 * 360
        self.arm_motor.run_to_rel_pos(position_sp=-arm_revolutions_for_full_range)
        self.arm_motor.wait_while(ev3.Motor.STATE_RUNNING)
        ev3.Sound.beep()
        self.arm_motor.position = 0


    def shutdown(self):
        self.running = False
        while True:
            if ev3.Button.backspace:
                print('Goodbye.')
                break
        ev3.Leds.set_color(ev3.Leds.LEFT,ev3.Leds.GREEN)
        ev3.Leds.set_color(ev3.Leds.RIGHT, ev3.Leds.GREEN)
        ev3.Sound.speak("Goodbye").wait()

    def loop_forever(self):
        self.running = True
        while self.running:
            time.sleep(0.1)  # Do nothing (except receive MQTT messages) until an MQTT message calls shutdown.

    def stop(self):
        self.left_motor.stop(stop_action = ev3.Motor.STOP_ACTION_BRAKE)
        self.right_motor.stop(stop_action = ev3.Motor.STOP_ACTION_BRAKE)

    def go_forward(self, left_speed, right_speed):
        self.left_motor.run_forever(speed_sp = left_speed)
        self.right_motor.run_forever(speed_sp = right_speed)

    def go_backward(self, left_speed, right_speed):
        self.left_motor.run_forever(speed_sp=-left_speed)
        self.right_motor.run_forever(speed_sp=-right_speed)

    def turn_left(self, right_speed):
        self.right_motor.run_forever(speed_sp=right_speed)

    def turn_right(self, left_speed):
        self.left_motor.run_forever(speed_sp=left_speed)

    def seek_beacon(self):
        forward_speed = 300
        turn_speed = 100

        while not self.touch_sensor.is_pressed:
            current_heading = self.beacon_seeker.heading  # use the beacon_seeker heading
            current_distance = self.beacon_seeker.distance  # use the beacon_seeker distance
            if current_distance == -128:
                # If the IR Remote is not found just sit idle for this program until it is moved.
                print("IR Remote not found. Distance is -128")
                self.left_motor.stop()
                self.right_motor.stop()
            else:
                # Here is some code to help get you started
                if math.fabs(current_heading) < 2:
                    # Close enough of a heading to move forward
                    # print("On the right heading. Distance: ", current_distance)
                    # You add more!
                    if current_distance == 0:
                        self.left_motor.run_forever(speed_sp=forward_speed)
                        self.right_motor.run_forever(speed_sp=forward_speed)
                        time.sleep(0.75)
                        self.left_motor.stop()
                        self.right_motor.stop()
                        ev3.Sound.speak("It takes two to tango")
                        return True
                    if math.fabs(current_distance) > 0:
                        self.left_motor.run_forever(speed_sp=forward_speed)
                        self.right_motor.run_forever(speed_sp=forward_speed)

                if math.fabs(current_heading) >= 2:
                    if math.fabs(current_heading) < 10:
                        if current_heading < 0:
                            self.left_motor.run_forever(speed_sp=-turn_speed)
                            self.right_motor.run_forever(speed_sp=turn_speed)
                        if current_heading > 0:
                            self.left_motor.run_forever(speed_sp=turn_speed)
                            self.right_motor.run_forever(speed_sp=-turn_speed)
                    # print("Adjusting heading: ", current_heading)

                if math.fabs(current_heading) >= 10:
                    self.left_motor.stop()
                    self.right_motor.stop()
                    # print("Heading too far off to fix: ", current_heading)

            time.sleep(0.2)


        # The touch_sensor was pressed to abort the attempt if this code runs.
        print("I'm already a great dancer by myself.")
        self.left_motor.stop()
        self.right_motor.stop()
        return False





# OLIVIA'S PROJECT / ADDED FUNCTIONS:

    def slide_to_left(self):
        self.turn_degrees(-90,500)
        self.drive_inches(8, 500)


    def left_stomp(self):
        self.turn_degrees(-90,500)
        self.drive_inches(4, 500)


    def slide_to_right(self):
        self.turn_degrees(90,500)
        self.drive_inches(8, 500)


    def right_stomp(self):
        self.turn_degrees(90,500)
        self.drive_inches(4, 500)

    def one_hop(self):
        self.drive_inches(5, 500)


    def two_hops(self):
        self.drive_inches(5, 500)
        self.drive_inches(5, 500)


    def take_it_back(self):
        self.left_motor.run_forever(speed_sp= - 500)
        self.right_motor.run_forever(speed_sp= - 500)
        time.sleep(1)
        self.left_motor.stop()
        self.right_motor.stop()


    def cha_cha_real_smooth(self):
        ev3.Leds.set_color(ev3.Leds.LEFT, ev3.Leds.AMBER)
        ev3.Leds.set_color(ev3.Leds.RIGHT, ev3.Leds.AMBER)
        self.turn_degrees(360, 500)
        ev3.Leds.set_color(ev3.Leds.LEFT, ev3.Leds.GREEN)
        ev3.Leds.set_color(ev3.Leds.RIGHT, ev3.Leds.GREEN)


    def freeze_clap(self, seconds_to_freeze):
        ev3.Leds.set_color(ev3.Leds.LEFT, ev3.Leds.RED)
        ev3.Leds.set_color(ev3.Leds.RIGHT, ev3.Leds.RED)
        ev3.Sound.speak('Freeze')
        if seconds_to_freeze > 0:
            if seconds_to_freeze > 8:
                time.sleep(8)
            if seconds_to_freeze < 8:
                time.sleep(seconds_to_freeze)
        else:
            time.sleep(1)

        ev3.Leds.set_color(ev3.Leds.LEFT, ev3.Leds.GREEN)
        ev3.Leds.set_color(ev3.Leds.RIGHT, ev3.Leds.GREEN)
        ev3.Sound.speak('Everybody clap your hands')
        self.arm_calibration()

    def find_friend(self):
        self.seek_beacon()


    def exit(self):
        self.running = False
        ev3.Sound.speak('Goodbye, human.')



