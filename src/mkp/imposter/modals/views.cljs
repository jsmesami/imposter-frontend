(ns mkp.imposter.modals.views
  (:require
    [mkp.imposter.components.backdrop :refer [backdrop]]))


(defn modal
  []
  [backdrop
   [:div.modal]])


(defn select-spec
  []
  [modal])
