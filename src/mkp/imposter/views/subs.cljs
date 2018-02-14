(ns mkp.imposter.views.subs
  (:require
    [re-frame.core :refer [reg-sub]]
    [mkp.imposter.views.home :refer [home]]
    [mkp.imposter.views.edit :refer [edit]]))


(def view-id->view
  {:home home
   :edit edit})


(reg-sub
  :views/current
  (fn [db _]
    (-> db :view view-id->view)))
