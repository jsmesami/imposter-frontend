(ns mkp.imposter.app.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :app/view
  (fn [db _]
    (:view db)))
