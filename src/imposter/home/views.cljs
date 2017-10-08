(ns imposter.home.views
  (:require
    [imposter.components.navbar :refer [navbar]]))


(defn new-poster-button
  [])


(defn home
  []
  [:div
   [navbar new-poster-button]
   [:h1 "Home"]])
