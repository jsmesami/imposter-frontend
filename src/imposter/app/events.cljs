(ns imposter.app.events
  (:require
    [re-frame.core :refer [reg-event-fx trim-v]]))


(reg-event-fx
  :app/initialize
  [trim-v]
  (fn [{:keys [db]} [api-endpoints]]
    {:db (-> db (assoc :api (js->clj api-endpoints))
                (assoc :view :home))}))
