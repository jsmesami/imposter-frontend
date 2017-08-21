(ns imposter.core
  (:require
    [reagent.core :as reagent :refer [atom]]))


(defonce db (atom {:name "Imposter"}))


(defn app []
  [:div
   [:h1 (:name @db)]])


(reagent/render-component
  [app]
  (js/document.getElementById "app"))
