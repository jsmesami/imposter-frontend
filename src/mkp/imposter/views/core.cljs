(ns mkp.imposter.views.core
  (:require
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.views.edit :refer [edit]]
    [mkp.imposter.views.events]
    [mkp.imposter.views.home :refer [home]]
    [mkp.imposter.views.subs]))


(defn view-id->view
  [id]
  (case id
    :home home
    :edit edit
    home))


(defn current-view
  []
  (view-id->view @(subscribe [:views/current-view])))
