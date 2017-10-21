(ns home.posters.list
  (:require
    [reagent.format :refer [format]]
    [re-frame.core :refer [subscribe dispatch]]
    [components.basic :refer [button]]
    [home.db :refer [posters-per-page]]
    [utils.bem :as bem]
    [reagent.core :as reagent]))


(defn pagination
  [posters]
  (let [loading? @(subscribe [:net/loading?])
        offset (get-in posters [:filter :offset])
        can-go-bw? (pos? offset)
        can-go-fw? (> (- offset (:count posters)) 0)
        paginate #(dispatch [:home/posters-update-filter {:offset %}])]
    [:div.poster-pagination
     [button "novější"
      :on-click #(paginate (- offset posters-per-page))
      :enabled? can-go-bw?
      :busy? loading?]
     [button "starší"
      :on-click #(paginate (+ offset posters-per-page))
      :enabled? can-go-fw?
      :busy? loading?]]))


(defn poster-thumb
  [spec]
  [:div.poster-thumb
   (:title spec)])


(defn posters-thumbs
  [posters]
  [:div
   (for [spec (:list posters)]
     ^{:key (:id spec)}
     [poster-thumb spec])])


(defn posters-stub
  []
  [:div
   (for [stub (range posters-per-page)]
     ^{:key stub}
     [:div.poster-stub])])


(defn poster-list
  []
  (let [posters @(subscribe [:home/posters])]
    (if (:list posters)
      [:div.poster-list
       [posters-thumbs posters]
       [pagination posters]]
      [:div.poster-list
       [posters-stub]])))
