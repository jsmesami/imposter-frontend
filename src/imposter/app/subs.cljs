(ns imposter.app.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :app/loading?
  (fn [db _]
    (:loading? db)))


(reg-sub
  :app/view
  (fn [db _]
    (get-in db [:views :current])))


(reg-sub
  :app/resources
  (fn [db _]
    (:resources db)))
