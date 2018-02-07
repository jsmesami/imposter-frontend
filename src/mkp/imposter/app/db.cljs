(ns mkp.imposter.app.db
  (:require
    [mkp.imposter.posters.db :refer [PosterListInitial]]
    [mkp.imposter.net.db :refer [NetInitial]]
    [mkp.imposter.views.home :refer [home]]
    [mkp.imposter.views.edit :refer [edit]]))


(def AppInitial
  {:net  NetInitial
   :posters PosterListInitial
   :view :home})


(defn view-id->view
  [id]
  (case id
    :home home
    :edit edit
    home))
