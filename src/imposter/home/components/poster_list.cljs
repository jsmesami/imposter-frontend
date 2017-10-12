(ns imposter.home.components.poster-list
  (:require
    [reagent.format :refer [format]]
    [re-frame.core :refer [subscribe dispatch]]
    [imposter.components.basic :refer [button]]
    [imposter.home.db :refer [posters-per-page]]
    [imposter.utils.bem :as bem]
    [reagent.core :as reagent]))


(defn pagination
  [posters]
  (let [loading? @(subscribe [:net/loading?])
        filter @(subscribe [:home/poster-filter])]
    [:div.poster-pagination
     [button "novější"
      :busy? loading?]
     [button "starší"
      :busy? loading?]]))


(defn poster-thumb
  [spec]
  [:div.poster-thumb
   (:title spec)])


(defn posters-thumbs
  [posters]
  [:div
   (for [spec posters]
     ^{:key (:id spec)}
     [poster-thumb spec])])


(defn posters-stub
  [posters-per-page]
  [:div
   (for [stub (range posters-per-page)]
     ^{:key stub}
     [:div.poster-stub])])


(defn poster-list
  []
  (let [posters @(subscribe [:home/poster-list])
        poster-filter @(subscribe [:home/poster-filter])]
    (if posters
      [:div.poster-list
       [posters-thumbs posters]
       [pagination posters]]
      [:div.poster-list
       [posters-stub (:posters-per-page poster-filter)]])))
