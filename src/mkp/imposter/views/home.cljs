(ns mkp.imposter.views.home
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [mkp.imposter.components.basic :refer [button]]
    [mkp.imposter.components.navbar :refer [navbar]]
    [mkp.imposter.posters.views :refer [poster-list]]))


(defn button-create-poster
  []
  (let [loading? @(subscribe [:net/loading?])]
    [button "Nový leták"
     :icon-name "plus"
     :enabled? (not loading?)
     :modifiers ["primary" "lg"]
     :on-click #(dispatch [:posters/create])]))


(defn home
  []
  [:div#home.view
   [navbar
    [button-create-poster]]
   [poster-list]])
