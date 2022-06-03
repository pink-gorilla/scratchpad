(ns demoapp.show
  (:require
   [scratchpad.core :refer [show! clear!]]))

(show! [:p "hello, demo!"])

; evaled expression inside the hiccup.
(show! [:p "Multiplication result: " (* 7 7)])

(show! ['user/customer {:first "Walter" :last "Schlemmel"}])

; code in highlightjs
; broken.
;(show! ['ui.codemirror/codemirror "(println 5) ; this is code"])

; show pinkie tags!
(show! ['ui.clock/clock])

; show image (in static resource) 
(show! [:img {:src "/r/sun.png"}])