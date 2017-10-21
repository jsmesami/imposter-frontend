(ns home.posters.filter
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [components.basic :refer [button]]))


(defn poster-filter
  []
  (let [loading? @(subscribe [:net/loading?])
        f (atom {})]
    [:div.poster-filter
     [button "filtrovat"
      :busy? loading?
      :on-click #(dispatch [:home/posters-update-filter @f])]]))
