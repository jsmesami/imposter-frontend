(ns imposter.app.effects
  (:require
    [re-frame.core :refer [reg-cofx]]))


(reg-cofx
  :app/get-id
  (fn [coeffects _]
    (assoc coeffects :id (.getTime (js/Date.)))))
