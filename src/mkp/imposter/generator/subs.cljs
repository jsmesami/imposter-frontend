(ns mkp.imposter.generator.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :generator/form
  (fn [db _]
    (get-in db [:generator :form])))
