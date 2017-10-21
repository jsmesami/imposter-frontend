(ns mkp.imposter.app.db
  (:require
    [mkp.imposter.home.db :refer [HomeViewInitial]]
    [mkp.imposter.home.views :refer [home]]))


(def AppInitial
  {:views {:current :home
           :home HomeViewInitial}})


(defn view-id->view
  [id]
  (case id
    :home home
    home))
