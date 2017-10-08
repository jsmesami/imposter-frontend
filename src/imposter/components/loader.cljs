(ns imposter.components.loader
  (:require
    [re-frame.core :refer [subscribe]]
    [imposter.utils.bem :as bem]))


(def module-name "loader")


(defn loader
  []
  (let [loading? @(subscribe [:app/loading?])]
    [:div {:class (bem/bm module-name (when loading? "loading"))}]))
