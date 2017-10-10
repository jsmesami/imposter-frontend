(ns imposter.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as reframe]
    [imposter.app.core]
    [imposter.app.views :refer [app]]
    [imposter.flash.core]
    [imposter.generator.core]
    [imposter.home.core]))


(defn render!
  []
  (reagent/render
    [app]
    (js/document.getElementById "app")))


(defn ^:export main
  []
  (reframe/dispatch-sync [:app/initialize])
  (render!))


(defn on-js-reload
  []
  (render!))
