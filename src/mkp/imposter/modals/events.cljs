(ns mkp.imposter.modals.events
  (:require
    [re-frame.core :refer [reg-event-db trim-v]]))


(reg-event-db
  :modals/clear
  [trim-v]
  (fn [db]
    (assoc db :modal nil)))
