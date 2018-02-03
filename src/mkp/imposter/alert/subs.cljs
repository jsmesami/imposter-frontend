(ns mkp.imposter.alert.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :alert/messages
  (fn [db _]
    (get-in db [:alert :messages])))
