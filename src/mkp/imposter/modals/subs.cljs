(ns mkp.imposter.modals.subs
  (:require
    [re-frame.core :refer [reg-sub]]
    [mkp.imposter.modals.views :refer [select-spec]]))


(def modal-id->modal
  {:select-spec select-spec})


(reg-sub
  :modals/current-modal
  (fn [db _]
    (-> db :modal modal-id->modal)))
