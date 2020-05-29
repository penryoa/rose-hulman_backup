import ev3dev.ev3 as ev3
import time

import robot_controller as robo
import tkinter
from tkinter import ttk
import mqtt_remote_method_calls as com

def main():
    buttons()





def buttons():
    mqtt_client = com.MqttClient()
    mqtt_client.connect_to_ev3()

    root = tkinter.Tk()
    root.title("Let's Get Some Aliens")

    main_frame = ttk.Frame(root, padding=110, relief='raised')
    main_frame.grid()

    find_them_button = ttk.Button(main_frame, text="Find Them")
    find_them_button.grid(row=1, column=1)
    find_them_button['command'] = lambda: find_them(mqtt_client)
    root.bind('<f>', lambda event: find_them(mqtt_client))

    grab_them_button = ttk.Button(main_frame, text="Grab Them")
    grab_them_button.grid(row=2, column=1)
    grab_them_button['command'] = lambda: grab_them(mqtt_client, right_speed_entry)
    root.bind('<g>', lambda event: grab_them(mqtt_client, right_speed_entry))

    place_them_button = ttk.Button(main_frame, text="Put Them Down")
    place_them_button.grid(row=3, column=1)
    place_them_button['command'] = lambda: place_them(mqtt_client, right_speed_entry)
    root.bind('<p>', lambda event: place_them(mqtt_client, right_speed_entry))

    root.mainloop()

def find_them(mqtt_client):
    print("Finding Them")
    mqtt_client.send_message("seek_beacon")

def 










#
# #this is for the shutdown of the theme song
# class DataContainer(object):
#     """ Helper class that might be useful to communicate between different callbacks."""
#
#     def __init__(self):
#         self.running = True
# dc = DataContainer()
# btn = ev3.Button()
#
# # def play_sound():
# #     while dc.running:
# #         ev3.Sound.play("/home/robot/csse120/assets/sounds/xfilestheme.wav")
# #         btn.process()  # This command is VERY important when using button callbacks!
# #         time.sleep(0.01)  # A short delay is important to allow other things to happen.
# #         btn.on_backspace = lambda state:handle_shutdown(state,dc)
# #         print("Goodbye!")
# #         ev3.Sound.speak("Goodbye").wait()
#
# def handle_shutdown(button_state, dc):
#     if button_state:
#         print('back')
#         dc.running = False


main()