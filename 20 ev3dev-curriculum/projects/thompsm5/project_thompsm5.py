import ev3dev.ev3 as ev3
import math
import time
import json
import collections
import paho.mqtt.client as mqtt
import mqtt_remote_method_calls as com
import tkinter
from tkinter import ttk
import PIL
from PIL import ImageTk, Image

def main():
    mqtt_client = com.MqttClient()
    mqtt_client.connect_to_ev3()
    welcome_screen(mqtt_client)

def welcome_screen(mqtt_client):
    root = tkinter.Tk()
    root.title = "Indiana Jone's Adventure"

    frame1 = ttk.Frame(root, padding=20, relief='raised')
    frame1.grid()

    label = ttk.Label(frame1, justify=tkinter.LEFT, text="Welcome to Indy's Adventure")
    label.grid(columnspan=2)

    path = "C:/Users/thompsm5/Pictures/CSSE/062612-indiana-jones.jpg"
    indy = ImageTk.PhotoImage(Image.open(path))
    label1 = ttk.Label(frame1, image=indy)
    label1.grid(row=1, column=0)

    label2 = ttk.Label(frame1, justify=tkinter.LEFT, text="What is your name?")
    label2.grid(columnspan=2)

    name_entry = ttk.Entry(frame1, width=50)
    name_entry.grid(row=3, column=0)

    submit = ttk.Button(frame1, text = "Submit")
    submit.grid(row=3, column=1)
    submit['command'] = lambda: start_adventure(name_entry, root, mqtt_client)
    root.bind('<Return>', lambda event: start_adventure(name_entry, root, mqtt_client))

    root.mainloop()

def start_adventure(name_entry, root1, mqtt_client):
    name = name_entry.get()
    root1.destroy()
    root2 = tkinter.Tk()

    frame = ttk.Frame(root2, padding=20, relief='raised')
    frame.grid()

    path = "C:/Users/thompsm5/Pictures/CSSE/indiana-jones-header.jpg"
    indy = ImageTk.PhotoImage(Image.open(path))
    label = ttk.Label(frame, justify=tkinter.LEFT, text="Great, " + str(name) + ", let's begin your adventure.")
    label.grid(row=0, column=0)
    label1 = ttk.Label(frame, image = indy)
    label1.grid(row=1,column=0)

    label2 = ttk.Label(frame, justify=tkinter.LEFT, text="There is tale of an ancient treasure in a hidden mystical cave.\nIndiana Jones has enlisted you to help him find the artifact.\nYou must go through three daunting tasks to find it.\nPass and you will be rewarded with treasures beyond your wildest dreams.\nFail, and you might be run over by a loose boulder.\nOr worse, attacked by snakes.\n\nAre you ready?")
    label2.grid(row=3, column=0)

    ready = ttk.Button(frame, text = "I'm Ready!")
    ready.grid(row=4, column=1)
    ready['command'] = lambda: [puzzle_1(root2, mqtt_client)]
    root2.bind('<Return>', lambda event: puzzle_1(root2, mqtt_client))

    root2.mainloop()

def puzzle_1(r, mqtt_client):
    mqtt_client.send_message("speak")
    r.destroy()

    root = tkinter.Tk()

    frame = ttk.Frame(root, padding=20, relief='raised')
    frame.grid()


    solved = ttk.Button(frame, text='Solved')
    solved.grid()
    solved['command']=lambda : snakes(root, mqtt_client)

    root.mainloop()

def snakes(r, mqtt_client):
    r.destroy()
    mqtt_client.send_message("snakes")
    root = tkinter.Tk()

    frame = ttk.Frame(root, padding =20)
    frame.grid()

    left_speed_label = ttk.Label(frame, text="Left Speed")
    left_speed_label.grid(row=0, column=0)
    left_speed_entry = ttk.Entry(frame, width=8)
    left_speed_entry.insert(0, "600")
    left_speed_entry.grid(row=1, column=0)

    right_speed_label = ttk.Label(frame, text="Right Speed")
    right_speed_label.grid(row=0, column=2)
    right_speed_entry = ttk.Entry(frame, width=8, justify=tkinter.RIGHT)
    right_speed_entry.insert(0, "600")
    right_speed_entry.grid(row=1, column=2)

    forward_button = ttk.Button(frame, text="Go Forward")
    forward_button.grid(row=2, column=1)
    forward_button['command'] = lambda: mqtt_client.send_message("go_forward", [int(left_speed_entry.get()), int(right_speed_entry.get())])
    root.bind('<Up>', lambda event: mqtt_client.send_message("go_forward", [int(left_speed_entry.get()), int(right_speed_entry.get())]))

    left_button = ttk.Button(frame, text="Turn Left")
    left_button.grid(row=3, column=0)
    left_button['command'] = lambda: mqtt_client.send_message("turn_left", [int(right_speed_entry.get())])
    root.bind('<Left>', lambda event: mqtt_client.send_message("turn_left", [int(right_speed_entry.get())]))

    stop_button = ttk.Button(frame, text="Stop")
    stop_button.grid(row=3, column=1)
    stop_button['command'] = lambda: mqtt_client.send_message("stop")
    root.bind('<space>', lambda event: mqtt_client.send_message("stop"))

    right_button = ttk.Button(frame, text="Right")
    right_button.grid(row=3, column=2)
    right_button['command'] = lambda: mqtt_client.send_message("turn_right", [int(left_speed_entry.get())])
    root.bind('<Right>', lambda event: mqtt_client.send_message("turn_right", [int(left_speed_entry.get())]))

    back_button = ttk.Button(frame, text="Back")
    back_button.grid(row=4, column=1)
    back_button['command'] = lambda: mqtt_client.send_message("go_backward", [int(left_speed_entry.get()), int(right_speed_entry.get())])
    root.bind('<Down>', lambda event: mqtt_client.send_message("go_backward", [int(left_speed_entry.get()), int(right_speed_entry.get())]))




main()