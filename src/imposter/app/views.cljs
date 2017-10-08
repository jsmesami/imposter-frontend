(ns imposter.app.views
  (:require
    [re-frame.core :refer [subscribe]]
    [imposter.components.loader :refer [loader]]
    [imposter.flash.views :refer [flash-messages]]))


(defn layout
  []
  [:div
   [loader]
   [flash-messages]
   [:h1 "Imposter"]])
