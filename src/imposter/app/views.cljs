(ns imposter.app.views
  (:require
    [re-frame.core :refer [subscribe]]
    [imposter.app.db :refer [view-id->view]]
    [imposter.components.loader :refer [loader]]
    [imposter.flash.views :refer [flash-messages]]))


(defn current-view
  []
  (view-id->view @(subscribe [:app/current-view])))


(defn app
  []
  [:div
   [loader]
   [flash-messages]
   [current-view]])
