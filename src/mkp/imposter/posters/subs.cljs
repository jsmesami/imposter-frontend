(ns mkp.imposter.posters.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :posters/list
  (fn [db _]
    (:posters db [])))
