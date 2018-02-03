(ns mkp.imposter.app.views
  (:require
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.alert.views :refer [alerts]]
    [mkp.imposter.app.db :refer [view-id->view]]
    [mkp.imposter.components.loader :refer [loader]]))


(defn current-view
  []
  (view-id->view @(subscribe [:app/current-view])))


(defn app
  []
  [:div
   [loader]
   [alerts]
   [current-view]])
