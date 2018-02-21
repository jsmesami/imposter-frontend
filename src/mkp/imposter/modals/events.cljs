(ns mkp.imposter.modals.events
  (:require
    [re-frame.core :refer [reg-event-db trim-v]]))


(reg-event-db
  :modals/clear
  [trim-v]
  (fn [db]
    (assoc db :modal nil)))


(reg-event-db
  :modals/set
  [trim-v]
  (fn [db [id data]]
    (-> db (assoc-in [:modal :id] id)
           (assoc-in  [:modal :data] data))))
