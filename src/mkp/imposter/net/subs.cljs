(ns mkp.imposter.net.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :net/loading?
  (fn [db _]
    (pos? (get-in db [:net :loading-count]))))
