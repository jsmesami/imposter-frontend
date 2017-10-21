(ns mkp.imposter.home.posters.list
  (:require
    [reagent.format :refer [format]]
    [re-frame.core :refer [subscribe dispatch]]
    [mkp.imposter.components.basic :refer [button]]
    [mkp.imposter.home.db :refer [posters-per-page]]
    [mkp.imposter.utils.bem :as bem]))


(defn pagination
  [posters]
  (let [loading? @(subscribe [:net/loading?])
        offset (get-in posters [:filter :offset])
        can-go-bw? (pos? offset)
        can-go-fw? (pos? (- offset (:count posters)))
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
