(ns scratchpad.handler
  (:require
   [taoensso.timbre :refer [trace debug info error]]
   [clojure.core.async :refer [go <! <!!]]
   [clojure.edn]
   [ring.util.response :as res]
   [ring.util.request :refer  [body-string]]
   [modular.webserver.middleware.api :refer [wrap-api-handler]]
   [scratchpad.core :as scratchpad]))

(defn scratchpad-get-handler
  [req]
  (debug "scratchpad-get-handler: " req)
  (scratchpad/get!)
  (<!! (go  (let [s (<! scratchpad/chan-scratchpad-get)]
              (res/response s))))
   ;(res/bad-request {:error 123})
  )

(defn scratchpad-set-handler
  [req]
  (debug "scratchpad-api-handler: " req)
  (let [s (body-string req)
        hiccup (clojure.edn/read-string s)]
    (info "string:" s "hiccup: " hiccup)
    (scratchpad/show! hiccup)
    (res/response {:message "src sent to scratchpad."})
    #_(res/bad-request save-result)))

(def wrapped-scratchpad-get-handler (wrap-api-handler scratchpad-get-handler))
(def wrapped-scratchpad-set-handler (wrap-api-handler scratchpad-set-handler))


