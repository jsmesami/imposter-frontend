(ns mkp.imposter.generator.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :generator/fields
  (fn [db _]
    (get-in db [:generator :fields])))
