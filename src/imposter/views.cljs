(ns imposter.views
  (:require
    [re-frame.core :refer [subscribe]]
    [imposter.flash.views :refer [flash-messages-component]]))


(defn layout
  []
  [:div
   [flash-messages-component]
   (if @(subscribe [:app/loading?])
     [:h2 "Imposter"]
     [:h2 "Loading data"])])
