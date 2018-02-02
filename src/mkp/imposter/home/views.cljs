(ns mkp.imposter.home.views
  (:require
    [mkp.imposter.components.basic :refer [svg]]
    [mkp.imposter.components.navbar :refer [navbar]]
    [mkp.imposter.home.posters.filter :refer [poster-filter]]
    [mkp.imposter.home.posters.list :refer [poster-list]]))


(defn home
  []
  [:div#home
   [navbar]
   [:div.container
    [poster-filter]
    [poster-list]]])
