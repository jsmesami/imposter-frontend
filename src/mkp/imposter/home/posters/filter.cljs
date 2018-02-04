(ns mkp.imposter.home.posters.filter
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [mkp.imposter.components.basic :refer [button input]]))


(defn filter-since
  [f enabled?]
  [:div.col-auto
   [:label "Vytvořeno od:"
    [input
     :type "date"
     :classes ["form-control"]
     :enabled? enabled?
     :on-change #(swap! f assoc :since %)]]])


(defn filter-until
  [f enabled?]
  [:div.col-auto
   [:label "Vytvořeno do:"
    [input
     :type "date"
     :classes ["form-control"]
     :enabled? enabled?
     :on-change #(swap! f assoc :until %)]]])


(defn filter-button
  [f enabled?]
  [:div.col-auto
   [:label "\u00A0"
    [button "filtrovat"
     :icon-name "search"
     :classes ["form-control"]
     :busy? enabled?
     :on-click #(dispatch [:home/posters-update-filter @f])]]])


(defn poster-filter
  []
  (let [loading? @(subscribe [:net/loading?])
        f (atom {})]
    [:div.poster-filter.form-row.align-items-center.mb-4
     [filter-since f (not loading?)]
     [filter-until f (not loading?)]
     [filter-button f loading?]]))
