(ns mkp.imposter.generator.views.buttons
  (:require
    [re-frame.core :refer [dispatch]]
    [mkp.imposter.components.basic :refer [button icon]]
    [mkp.imposter.generator.form :refer [form-filled? form-changed?]]
    [mkp.imposter.utils.url :refer [get-filename]]))


(defn home-button
  [enabled?]
  [button "domů"
   :classes ["mr-3" "mb-3"]
   :icon-name "left"
   :enabled? enabled?
   :on-click #(dispatch [:generator/cancel-edit])])


(defn generate-button
  [enabled? busy?]
  [button "generovat"
   :classes ["mr-3" "mb-3"]
   :icon-name "pdf"
   :enabled? enabled?
   :busy? busy?
   :on-click #(dispatch [:generator/submit])])


(defn preview-button
  [enabled?]
  [button "náhled"
   :classes ["mr-3" "mb-3"]
   :icon-name "media"
   :enabled? enabled?
   :on-click #(dispatch [:generator/preview])])


(defn download-button
  [enabled? link]
  [:a.btn.btn-primary.mr-3.mb-3
   {:class (when-not (and enabled? link) "disabled")
    :href (or link "#")
    :target "_blank"
    :rel "noopener noreferrer"
    :download (when link (get-filename link))}
   "stáhnout" [icon "download"]])


(defn generator-buttons
  [loading? form]
  (let [changed? (form-changed? form)
        filled? (form-filled? form)
        ready? (not (or loading? changed?))]
    [:div.row.justify-content-around
     [:div.col-md-8.mb-3
      [:hr]
      [:p.small "Pole označená" [icon "star"] "jsou povinná."]
      [home-button (not loading?)]
      [generate-button (and changed? filled?) loading?]
      [preview-button ready?]
      [download-button ready? (:print form)]]]))
