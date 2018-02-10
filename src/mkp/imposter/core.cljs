(ns mkp.imposter.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as reframe]
    [mkp.imposter.alert.core]
    [mkp.imposter.app.core]
    [mkp.imposter.app.views :refer [app]]
    [mkp.imposter.generator.core]
    [mkp.imposter.modals.core]
    [mkp.imposter.net.core]
    [mkp.imposter.posters.core]
    [mkp.imposter.resources.core]
    [mkp.imposter.views.core]))


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
