(ns mkp.imposter.resources.core
  (:require
    [mkp.imposter.resources.events]
    [mkp.imposter.resources.subs]))


(defn poster-resource
  [db poster-id]
  (str (get-in db [:resources :endpoints :poster]) poster-id "/"))
