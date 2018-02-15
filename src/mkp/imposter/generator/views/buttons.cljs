(ns mkp.imposter.generator.views.buttons
  (:require
    [re-frame.core :refer [dispatch]]
    [mkp.imposter.components.basic :refer [button icon]]
    [mkp.imposter.utils.url :refer [get-filename]]))


(defn home-button
  [loading? form]
  [button "domů"
   :classes ["mr-3" "mb-3"]
   :icon-name "left"
   :enabled? (not loading?)
   :on-click #(dispatch [:generator/cancel-edit])])


(defn generate-button
  [loading? form]
  [button "generovat"
   :classes ["mr-3" "mb-3"]
   :icon-name "pdf"
   :enabled? (not loading?)
   :busy? loading?
   :on-click #(dispatch [:generator/submit])])


(defn preview-button
  [loading? form]
  [button "náhled"
   :classes ["mr-3" "mb-3"]
   :icon-name "media"
   :enabled? (not loading?)
   :on-click #(dispatch [:generator/preview])])


(defn download-button
  [loading? form]
  [:a.btn.btn-primary.mr-3.mb-3
   {:class (when loading? "disabled")
    :href (:print form)
    :target "_blank"
    :rel "noopener noreferrer"
    :download (-> form :print get-filename)}
   "stáhnout" [icon "download"]])


(defn generator-buttons
  [loading? form]
  [:div.row.justify-content-around
   [:div.col-md-8.mb-3
    [:hr]
    [:p.small "Pole označená" [icon "star"] "jsou povinná."]
    [home-button loading? form]
    [generate-button loading? form]
    [preview-button loading? form]
    [download-button loading? form]]])
