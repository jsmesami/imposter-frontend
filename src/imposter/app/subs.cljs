(ns imposter.app.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :app/loading?
  (fn [db _]
    (empty? (:api db))))


(reg-sub
  :app/view
  (fn [db _]
    (:view db)))
