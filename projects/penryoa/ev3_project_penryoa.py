"""

 ---------  ----      ----  ----     ----  ----      ---
 ---------  ----      ----   ----   ----   ----     -----
 ---   ---  ----      ----    ---- ----    ----    --   --
 ---   ---  ----      ----     -------     ----   ---------
 ---------  --------  ----      -----      ----  -----------
 ---------  --------  ----       ---       ---- ---       ---

GOAL:
The goal of this project is to get the robot to the Cha Cha Slide! Should be fun.
(It'd be helpful to be familiar with the song.)
- There is a PC remote that has buttons saying "Slide to the left!", "Slide to the right!", etc.
    set so that when those buttons are pressed, the robot will go the indicated direction or do
    the indicated action.
- When the button "Cha Cha real smooth" is pressed, the robot will do a 360 with amber LEDs.
- User can input a number of seconds to freeze in the "Clap your hands!" command.
    If the user inputs anything over eight seconds, it will be accounted as eight. The robot has got to clap
    its hands, you know? And if it should freeze, then clap its hands, "freezing" for over eight seconds is too long.

"""

import ev3dev.ev3 as ev3
import penryoa_robot_controller as robo
import penryoa_mqtt_remote_method_calls as com

"""
                                        EV3 CONTROL!!
"""


print("--------------"
      "Let's Cha Cha!"
      "--------------")
ev3.Sound.speak("Time to cha cha")




def main():
    robot = robo.Snatch3r()
    client = com.MqttClient(robot)
    client.connect_to_pc()
    robot.loop_forever()

main()