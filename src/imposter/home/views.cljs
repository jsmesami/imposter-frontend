(ns home.views
  (:require
    [components.navbar :refer [navbar]]
    [home.posters.filter :refer [poster-filter]]
    [home.posters.list :refer [poster-list]]))


(defn home
  []
  [:div
   [navbar]
   [poster-filter]
   [poster-list]])
