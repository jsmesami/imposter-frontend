(ns imposter.app.db
  (:require
    [imposter.home.db :refer [HomeViewInitial]]
    [imposter.home.views :refer [home]]))


(def AppInitial
  {:views {:current :home
           :home HomeViewInitial}})


(defn view-id->view
  [id]
  (case id
    :home home
    home))
