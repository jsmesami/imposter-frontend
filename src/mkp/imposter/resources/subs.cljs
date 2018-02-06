(ns mkp.imposter.resources.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :resources/bureau
  (fn [db _]
    (get-in db [:resources :bureau])))


(reg-sub
  :resources/spec
  (fn [db _]
    (get-in db [:resources :spec])))
