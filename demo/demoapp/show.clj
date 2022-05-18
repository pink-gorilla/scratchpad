(ns demoapp.show
  (:require
    [scratchpad.core :refer [show! clear!]]))


(show! [:p "hello, demo!"])


; evaled expression inside the hiccup.
(show! [:p "Multiplication result: " (* 7 7)])

; code in highlightjs
(show! ['user/codemirror "(println 5) ; this is code"])

; show pinkie tags!
(show! ['user/clock])

; show image (in static resource) 
(show! [:img {:src "/r/sun.png"}])