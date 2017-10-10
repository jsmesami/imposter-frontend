(ns imposter.home.views
  (:require
    [re-frame.core :refer [subscribe]]
    [imposter.components.navbar :refer [navbar]]
    [imposter.home.components.poster-list :refer [poster-list]]))


(defn create-poster-button
  [])


(defn home
  []
  (let [resources @(subscribe [:app/resources])]
    [:div
     [navbar create-poster-button]
     [poster-list (:posters resources)]
     [:h1 "Home"]]))
