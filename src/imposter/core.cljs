(ns imposter.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as reframe]
    [imposter.app.core]
    [imposter.flash.core]
    [imposter.generator.core]
    [imposter.net.core]
    [imposter.app.views :refer [layout]]))


(defn app
  []
  layout)


(defn render-app
  []
  (reagent/render
    [app]
    (js/document.getElementById "app")))


(defn ^:export main
  []
  (reframe/dispatch-sync [:net/load-api-urls])
  (render-app))


(defn on-js-reload
  []
  (render-app))
