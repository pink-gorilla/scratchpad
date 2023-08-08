(ns scratchpad.page.scratchpad
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   [goldly.page :as page]
   ;[reval.goldly.viz.show :as viz]
   [scratchpad.page.show :as viz]))

(def empty-data
  {:render-fn 'reval.goldly.viz.render-fn/hiccup
   :data [:div ;.bg-blue-500.h-24.pt-3
          [:blockquote.text-xl.italic.ml-10.text-red-600 "Goldly Scratchpad"]
          [:h1.text-xl "scratchpad is empty!"]]})

(def demo-data
  {:render-fn 'reval.goldly.viz.render-fn/hiccup
   :data [:p.bg-red-300 "demo123"]})

(defonce state
  (r/atom empty-data))

(defn show-new-data [{:keys [render-fn data]}]
  (println "showing data: render-fn: " render-fn " data: " data)
  (reset! state {:render-fn render-fn :data data}))

(defn clear-scratchpad [& _args]
  (show-new-data empty-data))

; eval

(defonce scratchpad-show
  (r/atom false))

(defn scratchpad []
  [:div.w-full.h-full.m-0.p-5  ;[:div.bg-green-300.w-screen.h-screen.overflow-scroll
   ; header
   [:div.pt-5
    [:span.text-xl.text-blue-500.text-bold.mr-4 "scratchpad"]
    [:button.bg-gray-400.m-1 {:on-click clear-scratchpad} "clear"]
    [:button.bg-gray-400.m-1 {:on-click #(show-new-data demo-data)} "demo"]]
   ; rendered
   [:p.text-xl.text-blue-500.mt-3.mb-3 "output"]
   [:div#scratchpadtest]
   [:div.w-full
    [viz/show-data (:render-fn @state)
     (:data @state)]]
   ; separator
   [:hr.mt-5]
   ; source
   [:p.text-xl.text-blue-500.mt-3.mb-3 "render-fn: " (str (:render-fn @state))]
   [:div.bg-gray-300.overflow-scroll.w-full (pr-str (:data @state))]])

(page/add scratchpad :scratchpad)

(defn process-scratchpad-op [{:keys [op payload] :as _msg}]
  (case op
    :clear (clear-scratchpad)
    :show  (show-new-data payload)
    (println "unknown scratchpad op:" (str op))))

(rf/reg-event-fx
 :scratchpad/msg
 (fn [{:keys [_db]} [_ msg]]
   ;(println "scratchpad msg received: " msg)
   (process-scratchpad-op msg)
   nil))

(rf/reg-event-fx
 :scratchpad/get
 (fn [{:keys [_db]} [_ _msg]]
   (rf/dispatch [:goldly/send :scratchpad/state @state])
   nil))
