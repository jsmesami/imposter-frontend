(ns mkp.imposter.home.posters.filter
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [mkp.imposter.components.basic :refer [button]]))


(defn poster-filter
  []
  (let [loading? @(subscribe [:net/loading?])
        f (atom {})]
    [:div.poster-filter.row.mb-4
     [:div.col-12
      [button "filtrovat"
       :busy? loading?
       :on-click #(dispatch [:home/posters-update-filter @f])]]]))
