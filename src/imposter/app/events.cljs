(ns imposter.app.events
  (:require
    [re-frame.core :refer [reg-event-fx trim-v]]))


(reg-event-fx
  :app/initialize
  [trim-v]
  (fn [{:keys [db]} [response]]
    {:db (assoc db :api (js->clj response))}))
