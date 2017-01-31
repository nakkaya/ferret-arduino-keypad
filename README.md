ferret-arduino-keypad
===============

Wrapper for the Arduino keypad library.

#### Dependencies

 - Keypad Library for Arduino - `http://playground.arduino.cc/Code/Keypad`

Available via Arduino library manager. 

#### Usage

    (require '[ferret-arduino-keypad.core :as keypad])

    (def input (keypad/device
                [[\1 \2 \3 \A]  ;; Keypad Layout
                 [\4 \5 \6 \B]
                 [\7 \8 \9 \C]
                 [\# \0 \* \D]]
                [22 23 24 25]   ;; Row Pins
                [26 27 28 29])) ;; Col Pins


    (forever
      (println (keypad/wait-key input)))