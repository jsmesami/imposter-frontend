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
  [enabled? busy? form]
  [button "generovat"
   :classes ["mr-3" "mb-3"]
   :icon-name "pdf"
   :enabled? enabled?
   :busy? busy?
   :on-click #(dispatch [:generator/submit form])])


(defn preview-button
  [enabled? {link :thumb}]
  [button "náhled"
   :classes ["mr-3" "mb-3"]
   :icon-name "media"
   :enabled? (and enabled? link)
   :on-click #(dispatch [:generator/preview link])])


(defn download-button-pdf
  [enabled? {url :print_pdf}]
  [:a.btn.btn-primary.mr-3.mb-3
   {:class (when-not (and enabled? url) "disabled")
    :href (or url "#")
    :target "_blank"
    :rel "noopener noreferrer"
    :download (when url (get-filename url))}
   "Stáhnout PDF" [icon "download-pdf"]])


(defn download-button-jpg
  [enabled? {url :print_jpg}]
  [:a.btn.btn-primary.mr-3.mb-3
   {:class (when-not (and enabled? url) "disabled")
    :href (or url "#")
    :target "_blank"
    :rel "noopener noreferrer"
    :download (when url (get-filename url))}
   "Stáhnout JPG" [icon "download-jpg"]])


(defn help-messages
  [filled? changed?]
  [:p.small
   (if-not filled?
     [:span "Pole označená" [icon "star"] "jsou povinná."]
     (if-not changed?
       [:span "Pro nové generování letáku je potřeba změnit některou položku."]
       [:span "Pro náhled nebo stažení stiskněte \"generovat\"."]))])


(defn generator-buttons
  [loading? form]
  (let [changed? (form-changed? form)
        filled? (form-filled? form)
        ready? (not (or loading? changed?))]
    [:div.row.justify-content-around
     [:div.col-md-8.mb-3
      [:hr]
      [help-messages filled? changed?]
      [home-button (not loading?)]
      [generate-button (and changed? filled?) loading? form]
      [preview-button ready? form]]
     [:div.col-md-8.mb-3
      [download-button-pdf ready? form]
      [download-button-jpg ready? form]]]))
