(ns mkp.imposter.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as reframe]
    [mkp.imposter.app.core]
    [mkp.imposter.app.views :refer [app]]
    [mkp.imposter.alert.core]
    [mkp.imposter.generator.core]
    [mkp.imposter.home.core]
    [mkp.imposter.net.core]
    [mkp.imposter.resources.core]))


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
