(ns mkp.imposter.app.db
  (:require
    [mkp.imposter.posters.db :refer [PosterListInitial]]
    [mkp.imposter.net.db :refer [NetInitial]]))


(def AppInitial
  {:net  NetInitial
   :posters PosterListInitial
   :view :home})
