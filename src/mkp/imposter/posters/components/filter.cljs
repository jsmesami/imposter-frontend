(ns mkp.imposter.posters.components.filter
  (:require
    [clojure.string :refer [join]]
    [reagent.core :as reagent]
    [re-frame.core :refer [dispatch subscribe]]
    [mkp.imposter.components.basic :refer [button input select]]
    [mkp.imposter.resources.core :refer [resource->options]]))


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


(defn filter-bureau
  [f* loading?]
  [:div.col-auto
   [:label "Pobočka:"
    [select (resource->options @(subscribe [:resources/bureau]))
     :value (:bureau @f*)
     :classes ["form-control"]
     :enabled? (not loading?)
     :on-change #(swap! f* assoc :bureau %)]]])


(defn filter-spec
  [f* loading?]
  [:div.col-auto
   [:label "Šablona:"
    [select (resource->options @(subscribe [:resources/spec]))
     :value (:spec @f*)
     :classes ["form-control"]
     :enabled? (not loading?)
     :on-change #(swap! f* assoc :spec %)]]])


(defn clear-button
  [f* loading?]
  (when-not (empty? @f*)
    [:div.col-auto
     [:label "\u00A0"
      [button "\u00D7"
       :classes ["form-control"]
       :enabled? (not loading?)
       :on-click #(do (reset! f* {})
                      (dispatch [:posters/reset-filter]))]]]))


(defn filter-button
  [f* loading?]
  [:div.col-auto
   [:label "\u00A0"
    [button "filtrovat"
     :icon-name "search"
     :classes ["form-control"]
     :enabled? (not (or loading? (empty? @f*)))
     :busy? loading?
     :on-click #(dispatch [:posters/update-filter @f*])]]])


(defn poster-filter
  []
  (let [f* (reagent/atom {})]
    (fn []
      (let [loading? @(subscribe [:net/loading?])]
        [:div.poster-filter.form-row.align-items-center.mb-4
          (for [[i fun] (map-indexed vector [filter-since filter-until
                                             filter-bureau filter-spec
                                             filter-button clear-button])]
            ^{:key i}
            [fun f* loading?])]))))
