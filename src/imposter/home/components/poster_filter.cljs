(ns imposter.home.components.poster-filter
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [imposter.components.basic :refer [button]]))


(defn poster-filter
  []
  (let [loading? @(subscribe [:net/loading?])]
    [:div.poster-filter
     [button "filtrovat"
      :busy? loading?
      :on-click #(dispatch [:home/update-filters])]]))
