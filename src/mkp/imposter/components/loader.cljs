(ns mkp.imposter.components.loader
  (:require
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.utils.bem :as bem]))


(defn loader
  []
  (let [loading? @(subscribe [:net/loading?])]
    [:div {:class (bem/bm "loader" [(when loading? "loading")])}]))
