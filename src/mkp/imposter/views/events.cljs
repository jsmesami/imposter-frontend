(ns mkp.imposter.views.events
  (:require
    [re-frame.core :refer [reg-event-db trim-v]]))


(reg-event-db
  :views/set
  [trim-v]
  (fn [db [id]]
    (assoc db :view id)))
