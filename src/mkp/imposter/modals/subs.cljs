(ns mkp.imposter.modals.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :modals/current-modal
  (fn [db _]
    (:modal db)))
