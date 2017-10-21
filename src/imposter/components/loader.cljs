(ns components.loader
  (:require
    [re-frame.core :refer [subscribe]]
    [utils.bem :as bem]))


(defn loader
  []
  (let [loading? @(subscribe [:net/loading?])]
    [:div {:class (bem/bm "loader" [(when loading? "loading")])}]))
