"""
The python-ev3dev library tailored for Rose-Hulman.
"""

import ev3dev.ev3 as ev3
import ev3dev.helper


################################################################################
# Classes for making the EV3 Robot move, via motors for wheels, arms, etc.
################################################################################

class StopAction(object):
    COAST = "coast"
    BRAKE = "brake"
    HOLD = "hold"


class LargeMotor(ev3.LargeMotor, ev3dev.helper.MotorMixin):
    def __init__(self, output_plug, speed=100, stop_action=StopAction.BRAKE):
        super().__init__(output_plug)
        self.speed_percent = speed  # -100 to 100: a percent of fastest speed
        self.how_to_stop = stop_action

    def start(self, speed=None):
        if speed is None:
            self.run_forever(sp_speed=self.speed_percent)
        else:
            self.run_forever(sp_speed=speed)

    def stop(self, stop_action=None):
        if stop_action is None:
            super().stop(stop_action=self.how_to_stop)
        else:
            super().stop(stop_action=stop_action)

    def brake(self):
        super().stop(stop_action=StopAction.BRAKE)

    def coast(self):
        super().stop(stop_action=StopAction.COAST)

    def hold(self):
        super().stop(stop_action=StopAction.HOLD)


################################################################################
# Classes for making the EV3 Robot make sounds.
################################################################################

class Sound(object):
    # DAVID_TODO: Implement this.
    pass


class Beep(Sound):
    def __init__(self, blocked=True):
        """
        Store a "beep" Sound with the given arguments.
        """  # DAVID_TODO: Allow arguments per Sound.beep documentation.
        self.blocked = blocked

    def play(self, blocked=None):
        """
        Play the Beep.
        If blocked is True, wait for the Beep to finish before continuing.
          :type blocked: bool
        """
        if blocked is None:
            blocked = self.blocked
        subprocess = ev3.Sound.beep()
        if blocked:
            subprocess.wait()


class Tone(Sound):
    """ A Sound with a frequency and duration. """

    def __init__(self, frequency, duration):
        """
        Store a "tone" Sound with the given frequency (in Hz) and duration
        (in milliseconds).  Use the  play  method to produce the Tone.

        The EV3 speaker can produce frequencies between about 32 and 32,000 Hz.
        Duration can be arbitrarily large.
        Frequencies above 800 or so are rather obnoxious,
        as are durations above 400 or so.

          :type frequency: float
          :type duration:  float
        """
        self.frequency = frequency
        self.duration = duration

    def play(self, blocked=True):
        """
        Play the Beep.
        If blocked is True, wait for the Tone to finish before continuing.
          :type blocked: bool
        """
        subprocess = ev3.Sound.tone(self.frequency, self.duration)
        if blocked:
            subprocess.wait()


class Song(Sound):
    """ A sequence of Tones (with optional pauses between them). """

    def __init__(self, tones):
        """
        Store a sequence of tuples, where each tuple contains:
          - a frequency (in Hz)
          - a duration (in milliseconds)
          - [optionally] a duration (in milliseconds)
              to pause before playing the next item in the sequence

        Use the  play  method to play the Song as a sequence of Tones.

        The EV3 speaker can produce frequencies between about 32 and 32,000 Hz.
        Duration can be arbitrarily large.
        Frequencies above 800 or so are rather obnoxious,
        as are durations above 400 or so.

          :type tones: list[tuple]
        """
        self.tones = tones

    def play(self, blocked=True):
        """
        Play the Song.
        If blocked is True, wait for the Tone to finish before continuing.
          :type blocked: bool
        """
        subprocess = ev3.Sound.tone(self.tones)
        if blocked:
            subprocess.wait()


class Speech(Sound):
    """ Text that can be spoken by using the  speak (aka play)  method. """

    def __init__(self, text):
        """
        Store text to be played by the  speak  method.
          :type text: str
        """
        self.text = text

    def speak(self, *args):
        """ Speak the text.  Synonym for play. """
        self.play(*args)

    def play(self, blocked=True):
        """
        Play (speak) the Text.
        If blocked is True, wait for the Speech to finish before continuing.
          :type blocked: bool
        """
        subprocess = ev3.Sound.speak(self.text)
        if blocked:
            subprocess.wait()


################################################################################
# Other "helper" classes.
################################################################################

class Plug(object):
    """
    A Plug on the EV3 brick:
      either for input (1, 2, 3, 4) or for output (A, B, C, D).
    """
    map_strings_to_plugs = {1: ev3.INPUT_1, 2: ev3.INPUT_2, 3: ev3.INPUT_3,
                            4: ev3.INPUT_4, "1": ev3.INPUT_1, "2": ev3.INPUT_2,
                            "3": ev3.INPUT_3, "4": ev3.INPUT_4,
                            "A": ev3.OUTPUT_A, "B": ev3.OUTPUT_B,
                            "C": ev3.OUTPUT_C, "D": ev3.OUTPUT_D, }

    def __init__(self, plug_name):
        """
        The EV3 name for the given plug expressed as a string:
          "1", "2", "3", "4", "A", "B", "C", or "D",
        or as an integer (for inputs):  1, 2, 3 or 4.
        The string can be upper or lower case (either is fine).

          :type plug_name: str | int
        """
        if type(plug_name) is str:
            plug_name = plug_name.upper()
        try:
            self.plug = Plug.map_strings_to_plugs[plug_name]
        except KeyError:
            plug_names = "'1', '2', '3', '4', 'A', 'B', 'C', or 'D'."
            raise KeyError("The Plug name must be one of: " + plug_names)


# ev3.LargeMotor
#
# ev3.MediumMotor
#
# ev3.Motor
#
# ev3.Sound
#
# Button
#
