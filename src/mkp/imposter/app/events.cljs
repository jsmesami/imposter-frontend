(ns mkp.imposter.app.events
  (:require
    [re-frame.core :refer [reg-event-fx]]
    [mkp.imposter.app.db :refer [AppInitial]]))


(reg-event-fx
  :app/initialize
  (fn [_]
    {:db       AppInitial
     :dispatch [:resources/fetch-data]}))
