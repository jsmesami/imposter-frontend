(ns mkp.imposter.generator.views.buttons
  (:require
    [re-frame.core :refer [dispatch]]
    [mkp.imposter.components.basic :refer [button icon]]))


(defn generator-buttons
  [loading? form]
  [:div.row.justify-content-around
   [:div.col-md-8.mb-5
    [:hr]
    [:p.small "Pole označená" [icon "star"] "jsou povinná."]
    [button "domů"
     :classes ["mr-3"]
     :icon-name "left"
     :enabled? (not loading?)
     :on-click #(dispatch [:generator/cancel-edit])]
    [button "generovat"
     :classes ["mr-3"]
     :icon-name "pdf"
     :enabled? (not loading?)
     :busy? loading?
     :on-click #(dispatch [:generator/submit])]
    [button "náhled"
     :classes ["mr-3"]
     :icon-name "media"
     :enabled? (not loading?)
     :on-click #(dispatch [:generator/preview])]
    [button "stáhnout"
     :classes []
     :icon-name "download"
     :enabled? (not loading?)]]])
