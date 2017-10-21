(ns app.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :app/current-view
  (fn [db _]
    (get-in db [:views :current])))
