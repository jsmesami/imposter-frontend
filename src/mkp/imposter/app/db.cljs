(ns mkp.imposter.app.db
  (:require
    [mkp.imposter.home.db :refer [HomeViewInitial]]
    [mkp.imposter.home.views :refer [home]]
    [mkp.imposter.net.db :refer [NetInitial]]))


(def AppInitial
  {:net NetInitial
   :views {:current :home
           :home HomeViewInitial}})


(defn view-id->view
  [id]
  (case id
    :home home
    home))
