(ns mkp.imposter.views.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :views/current-view
  (fn [db _]
    (:view db)))
