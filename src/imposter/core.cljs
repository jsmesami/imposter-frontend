(ns imposter.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as reframe]
    [imposter.app.core]
    [imposter.flash.core]
    [imposter.generator.core]
    [imposter.net.core]
    [imposter.views :refer [layout]]))


(defn app
  []
  layout)


(defn ^:export main
  []
  (reframe/dispatch-sync [:net/load-api-urls])
  (reagent/render [app]
    (js/document.getElementById "app")))
