(ns mkp.imposter.modals.core
  (:require
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.modals.subs]
    [mkp.imposter.modals.views :refer [select-spec]]))


(def modal-id->modal
  {:select-spec select-spec})


(defn current-modal
  []
  (modal-id->modal @(subscribe [:modals/current-modal])))
