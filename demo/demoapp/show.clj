(ns demoapp.show)

(tap>
 ^:R [:p "hello, world!"])

; evaled expression inside the hiccup.
(tap> ^:R
      [:p "Multiplication result: " (* 7 7)])

(tap> ^{:render-fn 'demoapp.ui.customer/customer}
   {:first "Walter" :last "Schlemmel"})

; code in highlightjs
; broken.
;(show! ['ui.codemirror/codemirror "(println 5) ; this is code"])

; show pinkie tags!
(tap> ^:reagent ['ui.clock/clock])

; show image (in static resource) 
(tap> ^:R [:img {:src "/r/sun.png"}])

(tap> ['ui.clock/clock])
