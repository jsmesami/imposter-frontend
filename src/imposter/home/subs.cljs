(ns imposter.home.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :home/poster-list
  (fn [db _]
    (get-in db [:views :home :poster-list])))


(reg-sub
  :home/poster-filter
  (fn [db _]
    (get-in db [:views :home :poster-filter])))
