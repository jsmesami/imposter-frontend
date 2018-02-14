(ns mkp.imposter.app.views
  (:require
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.alert.views :refer [alerts]]
    [mkp.imposter.components.loader :refer [loader]]))


(defn app
  []
  (let [view @(subscribe [:views/current])
        modal @(subscribe [:modals/current])]
    [:div
     [loader]
     [alerts]
     (when view
       [view])
     (when modal
       [modal])]))
