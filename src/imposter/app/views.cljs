(ns imposter.app.views
  (:require
    [re-frame.core :refer [subscribe]]
    [imposter.app.db :refer [view-id->view]]
    [imposter.components.loader :refer [loader]]
    [imposter.flash.views :refer [flash-messages]]))


(defn view
  []
  (view-id->view @(subscribe [:app/view])))


(defn layout
  []
  [:div
   [loader]
   [flash-messages]
   [view]])
