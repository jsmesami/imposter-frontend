(ns imposter.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as reframe]
    [imposter.common.core]
    [imposter.generator.core]
    [imposter.index.core]
    [imposter.views :refer [layout]]))


(defn app
  []
  layout)


(defn ^:export main
  []
  (reframe/dispatch-sync [:common/load-init-data])
  (reagent/render [app]
    (js/document.getElementById "app")))
