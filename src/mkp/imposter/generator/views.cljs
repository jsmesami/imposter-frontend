(ns mkp.imposter.generator.views
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [mkp.imposter.components.basic :refer [icon input select-image]]
    [mkp.imposter.utils.file-reader :refer [file->base64]]
    [mkp.imposter.utils.string :refer [shorten]]))


(defn char-counter
  [text char_limit]
  (when char_limit
    [:small.form-text.text-muted
     "Vyplněno "
     [:strong (count text)]
     " znaků z "
     [:strong char_limit]
     " dostupných."]))


(defmulti render-field
          (fn [loading? [id {:keys [type]}]]
            (keyword type)))


(defmethod render-field :text
  [loading? [id {:keys [name text widget char_limit mandatory]}]]
  (let [dispatcher (fn [value]
                     (dispatch [:generator/update-form-field id :text value]))]
    [:label name (when mandatory [icon "star"])
     [input
      :value text
      :on-change dispatcher
      :enabled? (not loading?)
      :classes ["form-control"]
      :widget (keyword (or widget :input))
      :attrs {:maxLength char_limit}]
     [char-counter text char_limit]]))


(defmethod render-field :image
  [loading? [id {:keys [name filename url data mandatory]}]]
  (let [save-data (fn [data]
                    (dispatch [:generator/update-form-field id :data data]))
        save-fname (fn [fname]
                       (dispatch [:generator/update-form-field id :filename fname]))
        dispatcher (fn [file]
                     (do (file->base64 file save-data)
                         (save-fname (.-name file))))]
    [:div.file-selector
     [:label name (when mandatory [icon "star"])
      [:br]
      [:button.btn.btn-outline-primary
       (shorten (or filename "vyberte obrázek (JPEG nebo PNG)") 32)
       [select-image
        :on-change dispatcher
        :enabled? (not loading?)
        :classes ["form-control-file"]]]]
     (when-let [uri (or data url)]
       [:div.generator__thumb
        [:img.img-thumbnail {:src uri}]])]))


(defn editor-fields
  [loading? fields]
  [:div
   (for [[id field] (sort-by #(-> % second :order) fields)]
     ^{:key id}
     [:div.row.justify-content-around
      [:div.form-group.col-md-8
       (render-field loading? [id field])]])])


(defn generator
  []
  (let [data @(subscribe [:generator/data])
        loading? @(subscribe [:net/loading?])
        fields (get-in data [:form :fields])]
    [:div.generator.container
     [editor-fields loading? fields]]))
