(ns app.db
  (:require
    [home.db :refer [HomeViewInitial]]
    [home.views :refer [home]]))


(def AppInitial
  {:views {:current :home
           :home HomeViewInitial}})


(defn view-id->view
  [id]
  (case id
    :home home
    home))
