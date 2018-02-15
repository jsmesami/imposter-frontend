(ns mkp.imposter.modals.subs
  (:require
    [re-frame.core :refer [reg-sub]]
    [mkp.imposter.modals.views.spec-modal :refer [select-spec]]
    [mkp.imposter.modals.views.preview-modal :refer [preview-poster]]))


(def modal-id->modal
  {:select-spec select-spec
   :preview-poster preview-poster})


(reg-sub
  :modals/current
  (fn [db _]
    (-> db :modal modal-id->modal)))
