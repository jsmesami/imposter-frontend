(ns mkp.imposter.app.views
  (:require
    [mkp.imposter.alert.views :refer [alerts]]
    [mkp.imposter.components.loader :refer [loader]]
    [mkp.imposter.modals.core :refer [current-modal]]
    [mkp.imposter.views.core :refer [current-view]]))


(defn app
  []
  [:div
   [loader]
   [alerts]
   [current-view]
   [current-modal]])
