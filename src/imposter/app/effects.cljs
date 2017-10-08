(ns imposter.app.effects
  (:require
    [re-frame.core :refer [reg-cofx reg-fx]]))


(reg-cofx
  :app/get-id
  (fn [coeffects _]
    (assoc coeffects :id (.getTime (js/Date.)))))


(reg-fx
  :app/log
  (fn [message]
    (js/console.log message)))
