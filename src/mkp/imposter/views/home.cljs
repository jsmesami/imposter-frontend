(ns mkp.imposter.views.home
  (:require
    [mkp.imposter.components.basic :refer [button]]
    [mkp.imposter.components.navbar :refer [navbar]]
    [mkp.imposter.posters.views :refer [poster-list]]))


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
   [poster-list]])
