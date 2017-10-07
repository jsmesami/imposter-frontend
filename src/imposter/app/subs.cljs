(ns imposter.app.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :app/loading?
  (fn [db _]
    (not (empty? (:api db)))))
