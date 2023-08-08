(ns demoapp.ui.customer)

(defn customer [{:keys [first last]}]
  [:div.bg-green-100.m-5
   [:p.text-bold.text-red-500 first]
   [:p.text-bold.text-blue-500 last]])
