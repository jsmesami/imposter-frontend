(ns mkp.imposter.generator.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :generator/data
  (fn [db _]
    (:generator db)))
