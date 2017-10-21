(ns app.views
  (:require
    [re-frame.core :refer [subscribe]]
    [app.db :refer [view-id->view]]
    [components.loader :refer [loader]]
    [flash.views :refer [flash-messages]]))


(defn current-view
  []
  (view-id->view @(subscribe [:app/current-view])))


(defn app
  []
  [:div
   [loader]
   [flash-messages]
   [current-view]])
