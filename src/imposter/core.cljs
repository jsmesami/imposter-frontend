(ns core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as reframe]
    [app.core]
    [app.views :refer [app]]
    [flash.core]
    [generator.core]
    [home.core]
    [net.core]))


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
