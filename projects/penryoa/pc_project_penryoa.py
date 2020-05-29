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
import time
import math
import penryoa_robot_controller as robo
import tkinter
from tkinter import ttk
import penryoa_mqtt_remote_method_calls as com


robot = robo.Snatch3r


"""
                                                    PC remote:
"""
def main():
    client = com.MqttClient()
    client.connect_to_ev3()

    root = tkinter.Tk()
    root.title("+*+*+ Cha Cha Slide +*+*+")

    main_frame = ttk.Frame(root, padding=20, relief='raised')
    main_frame.grid()

    one_hop_button = ttk.Button(main_frame, text="One hop this time.")
    one_hop_button.grid(row=0, column=1)
    one_hop_button['command'] = lambda: one_hop(client)
    root.bind('<Up>', lambda event: one_hop(client))

    slide_left_button = ttk.Button(main_frame, text="Slide to the left!")
    slide_left_button.grid(row=1, column=0)
    slide_left_button['command'] = lambda: slide_left(client)
    root.bind('<Left>', lambda event: slide_left(client))

    cha_cha_smooth_button = ttk.Button(main_frame, text="Cha cha real smooth.")
    cha_cha_smooth_button.grid(row=1, column=1)
    cha_cha_smooth_button['command'] = lambda: cha_cha_smooth(client)

    slide_right_button = ttk.Button(main_frame, text="Slide to the right!")
    slide_right_button.grid(row=1, column=2)
    slide_right_button['command'] = lambda: slide_right(client)
    root.bind('<Right>', lambda event: slide_right(client))

    take_back_button = ttk.Button(main_frame, text="Take it back, now, y'all.")
    take_back_button.grid(row=2, column=1)
    take_back_button['command'] = lambda: take_it_back(client)
    root.bind('<Down>', lambda event: take_it_back(client))

    spacing_one = ttk.Label(main_frame, text = "")
    spacing_one.grid(row = 3, column = 0)

    left_stomp_button = ttk.Button(main_frame, text="Left foot, let's stomp.")
    left_stomp_button.grid(row=4, column=0)
    left_stomp_button['command'] = lambda: left_stomp(client)
    root.bind('<L>', lambda event: left_stomp(client))

    two_hops_button = ttk.Button(main_frame, text="Two hops this time.")
    two_hops_button.grid(row=4, column=1)
    two_hops_button['command'] = lambda: two_hops(client)
    root.bind('<T>', lambda event: two_hops(client))

    right_stomp_button = ttk.Button(main_frame, text="Right foot, let's stomp.")
    right_stomp_button.grid(row=4, column=2)
    right_stomp_button['command'] = lambda: right_stomp(client)
    root.bind('<R>', lambda event: right_stomp(client))

    spacing_two = ttk.Label(main_frame, text = "")
    spacing_two.grid(row = 5, column = 0)

    seconds_label = ttk.Label(main_frame, text="Seconds to freeze:")
    seconds_label.grid(row=6, column=0)
    seconds_entry = ttk.Entry(main_frame, width=8)
    seconds_entry.insert(0, "2")
    seconds_entry.grid(row=6, column=1)
    freeze_clap_button = ttk.Button(main_frame, text="Freeze... "
                                                     ""
                                                     "Everybody clap your hands!")
    freeze_clap_button.grid(row=6, column=2)
    freeze_clap_button['command'] = lambda: freeze_clap(client, seconds_entry)
    root.bind('<space>', lambda event: freeze_clap(client, seconds_entry))

    exit_button = ttk.Button(main_frame, text = "Exit")
    exit_button.grid(row = 9, column = 1)
    exit_button['command'] = lambda: quit(client, True)
    root.bind('<Escape>', lambda event: quit(client, True))

    find_button = ttk.Button(main_frame, text = "Find a friend to dance with!")
    find_button.grid(row = 8, column = 1)
    find_button['command'] = lambda: find_friend(client)


    root.mainloop()

"""
                                    Defining the functions that the buttons call:
"""

def slide_left(client):
    print('Slide to the left')
    client.send_message("slide_to_left")

def left_stomp(client):
    print("Left foot, let's stomp!")
    client.send_message("left_stomp")

def slide_right(client):
    print('Slide to the right')
    client.send_message("slide_to_right")

def right_stomp(client):
    print("Right foot, let's stomp!")
    client.send_message("right_stomp")

def one_hop(client):
    print('One hop this time')
    client.send_message("one_hop")

def two_hops(client):
    print('Two hops')
    client.send_message("two_hops")

def freeze_clap(client, seconds_entry):
    print('FREEZE... Now everybody clap your hands!')
    client.send_message("freeze_clap", [int(seconds_entry.get())])

def cha_cha_smooth(client):
    print("Cha cha real smooth.")
    client.send_message("cha_cha_real_smooth")

def take_it_back(client):
    print("Take it back, now, y'all.")
    client.send_message('take_it_back')

def find_friend(client):
    print("Someone dance with me... ): ")
    client.send_message('seek_beacon')

def quit(client, shutdown_ev3):
    if shutdown_ev3:
        client.send_message("exit")
    client.close()
    exit()





"""
                                                And finally, call main.
"""

main()