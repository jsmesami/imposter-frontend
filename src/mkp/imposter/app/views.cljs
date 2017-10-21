(ns mkp.imposter.app.views
  (:require
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.app.db :refer [view-id->view]]
    [mkp.imposter.components.loader :refer [loader]]
    [mkp.imposter.flash.views :refer [flash-messages]]))


(defn current-view
  []
  (view-id->view @(subscribe [:app/current-view])))


(defn app
  []
  [:div
   [loader]
   [flash-messages]
   [current-view]])
