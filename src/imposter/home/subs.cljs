(ns home.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :home/posters
  (fn [db _]
    (get-in db [:views :home :posters])))
