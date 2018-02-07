(ns mkp.imposter.app.views
  (:require
    [mkp.imposter.alert.views :refer [alerts]]
    [mkp.imposter.components.loader :refer [loader]]
    [mkp.imposter.views.routing :refer [current-view]]))


(defn app
  []
  [:div
   [loader]
   [alerts]
   [current-view]])
