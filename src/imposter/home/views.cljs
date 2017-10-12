(ns imposter.home.views
  (:require
    [imposter.components.navbar :refer [navbar]]
    [imposter.home.components.poster-filter :refer [poster-filter]]
    [imposter.home.components.poster-list :refer [poster-list]]))


(defn home
  []
  [:div
   [navbar]
   [poster-filter]
   [poster-list]
   [:h1 "Home"]])
