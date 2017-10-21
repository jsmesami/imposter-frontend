(ns net.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :net/loading?
  (fn [db _]
    (get-in db [:net :loading?])))
