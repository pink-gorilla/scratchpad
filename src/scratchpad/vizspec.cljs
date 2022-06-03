(ns scratchpad.vizspec
  (:require
   [viz :refer [show]]
   [goldly.sci :refer [resolve-symbol-sci]]))

(defn safe-resolve [s]
  (try
    (resolve-symbol-sci s)
    (catch :default _e
      (.log js/console "renderer not found: " s)
      nil)))

(defn render-vizspec [h]
  ;(println "rendering vizspec: " h)
  ;(println "first item in vec:" (first h) "type: " (type (first h)))
  ;(println "render fn:" (get-symbol-value (first h)))
  ;(println "now showing..")
  (let [h-fn (show safe-resolve h)]
    ;(println "rendered spec: " (pr-str h-fn))
    h-fn))