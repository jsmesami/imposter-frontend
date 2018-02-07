(ns mkp.imposter.views.events
  (:require
    [re-frame.core :refer [reg-event-db trim-v]]))


(reg-event-db
  :views/set-view
  [trim-v]
  (fn [db view]
    (assoc db :view view)))
