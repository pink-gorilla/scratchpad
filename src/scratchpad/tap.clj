(ns scratchpad.tap
  (:require
   [taoensso.timbre :refer [trace debug info warn error]]
   [modular.config :as config :refer [get-in-config]]
   [reval.viz.data :refer [value->data]]
   [scratchpad.core :as scratchpad]))

(defn log [x]
  (spit "event.log" (str x \newline) :append true))

(defn to-scratchpad [x]
  (debug "sending to scratchpad..")
  (let [viz (value->data x)]
    (scratchpad.core/show! viz)))

;; add log function to tap
; (add-tap log)

(defn init! []
  (let [scratchpad (get-in-config [:scratchpad])
        tap  (:tap scratchpad)]

    (if tap
      (do (warn "Adding Tap Listener that will send data to scratchpad ...")
          (add-tap to-scratchpad))
      (warn "Scratchpad tap is disabled. To enable {:scratchpad :tap} true"))))

(init!)

(comment

  (+ 5 5)
  (nb-collections)

  (-> 3
      value->data
      keys)

;  
  )
