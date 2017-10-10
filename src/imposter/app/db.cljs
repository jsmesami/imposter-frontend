(ns imposter.app.db
  (:require
    [imposter.home.views :refer [home]]))


(def initial-db
  {:views {:current :home}})


(defn view-id->view
  [id]
  (case id
    :home home
    home))
