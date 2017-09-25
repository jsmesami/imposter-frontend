(ns imposter.common.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :common/initialized?
  (fn [db _]
    (not (empty? (:data db)))))
