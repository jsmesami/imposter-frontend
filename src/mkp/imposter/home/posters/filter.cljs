(ns mkp.imposter.home.posters.filter
  (:require
    [clojure.string :refer [join]]
    [reagent.core :as reagent]
    [re-frame.core :refer [dispatch subscribe]]
    [mkp.imposter.components.basic :refer [button input]]))


(defn filter-since
  [f* loading?]
  [:div.col-auto
   [:label "Vytvořeno od:"
    [input
     :type "date"
     :value (:since @f*)
     :classes ["form-control"]
     :enabled? (not loading?)
     :on-change #(swap! f* assoc :since %)]]])


(defn filter-until
  [f* loading?]
  [:div.col-auto
   [:label "Vytvořeno do:"
    [input
     :type "date"
     :value (:until @f*)
     :classes ["form-control"]
     :enabled? (not loading?)
     :on-change #(swap! f* assoc :until %)]]])


(defn clear-button
  [f* loading?]
  (when-not (empty? @f*)
    [:div.col-auto
     [:label "\u00A0"
      [button "\u00D7"
       :classes ["form-control"]
       :enabled? (not loading?)
       :on-click #(do (reset! f* {})
                      (dispatch [:home/posters-reset-filter]))]]]))


(defn filter-button
  [f* loading?]
  [:div.col-auto
   [:label "\u00A0"
    [button "filtrovat"
     :icon-name "search"
     :classes ["form-control"]
     :enabled? (not (or loading? (empty? @f*)))
     :busy? loading?
     :on-click #(dispatch [:home/posters-update-filter @f*])]]])


(defn poster-filter
  []
  (let [loading? @(subscribe [:net/loading?])
        f (reagent/atom {})]
    (fn []
      [:div.poster-filter.form-row.align-items-center.mb-4
       [filter-since f loading?]
       [filter-until f loading?]
       [filter-button f loading?]
       [clear-button f loading?]])))
