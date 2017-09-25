(ns imposter.views
  (:require
    [re-frame.core :refer [subscribe]]))


(defn layout
  []
  (if @(subscribe [:common/initialized?])
    [:h2 "Imposter"]
    [:h2 "Loading data"]))
