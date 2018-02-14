(ns mkp.imposter.generator.views.buttons
  (:require
    [re-frame.core :refer [dispatch]]
    [mkp.imposter.components.basic :refer [button icon]]))



(defn generator-buttons
  [loading? fields]
  [:div.row.justify-content-around
   [:div.col-md-8.mb-5
    [:hr]
    [:p.small "Pole označená" [icon "star"] "jsou povinná."]
    [button "zpět"
     :classes ["mr-3"]
     :icon-name "left"
     :enabled? (not loading?)]
    [button "generovat"
     :classes ["mr-3"]
     :icon-name "pdf"
     :enabled? (not loading?)]
    [button "náhled"
     :classes ["mr-3"]
     :icon-name "media"
     :enabled? (not loading?)]
    [button "stáhnout"
     :classes []
     :icon-name "download"
     :enabled? (not loading?)]]])
