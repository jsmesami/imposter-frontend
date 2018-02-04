(ns mkp.imposter.home.views
  (:require
    [mkp.imposter.components.basic :refer [button svg]]
    [mkp.imposter.components.navbar :refer [navbar]]
    [mkp.imposter.home.posters.filter :refer [poster-filter]]
    [mkp.imposter.home.posters.list :refer [poster-list]]))


(defn button-create-poster
  []
  [button "Nový leták"
   :icon-name "plus"
   :modifiers ["primary" "lg"]])


(defn home
  []
  [:div#home
   [navbar
    [button-create-poster]]
   [:div.container
    [poster-filter]
    [poster-list]]])
