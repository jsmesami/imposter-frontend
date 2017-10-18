(ns imposter.home.views
  (:require
    [imposter.components.navbar :refer [navbar]]
    [imposter.home.posters.filter :refer [poster-filter]]
    [imposter.home.posters.list :refer [poster-list]]))


(defn home
  []
  [:div
   [navbar]
   [poster-filter]
   [poster-list]])
