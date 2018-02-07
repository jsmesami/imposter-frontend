(ns mkp.imposter.views.routing
  (:require
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.views.home :refer [home]]
    [mkp.imposter.views.edit :refer [edit]]))


(defn view-id->view
  [id]
  (case id
    :home home
    :edit edit
    home))


(defn current-view
  []
  (view-id->view @(subscribe [:views/current-view])))
