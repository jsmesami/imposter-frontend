(ns imposter.flash.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :flash/messages
  (fn [db _]
    (get-in db [:flash :messages])))
