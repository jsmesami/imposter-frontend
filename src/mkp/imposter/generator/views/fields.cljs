(ns mkp.imposter.generator.views.fields
  (:require
    [mkp.imposter.components.basic :refer [icon input select-image]]
    [mkp.imposter.generator.views.components :refer [char-counter error-msg]]
    [mkp.imposter.generator.views.dispatchers :refer [update-text-dispatcher update-image-dispatcher]]
    [mkp.imposter.utils.string :refer [shorten]]))


(defmulti render-field
          (fn [loading? [id {:keys [type]}]]
            (keyword type)))


(defmethod render-field :text
  [loading? [id {:keys [name text error widget char_limit mandatory]}]]
  [:label name (when mandatory [icon "star"])
   [input
    :value text
    :on-change #(update-text-dispatcher id %)
    :enabled? (not loading?)
    :classes ["form-control" (when error "is-invalid")]
    :widget (keyword (or widget :input))
    :attrs {:maxLength char_limit}]
   [char-counter text char_limit]
   [error-msg error]])


(defmethod render-field :image
  [loading? [id {:keys [name filename url data error mandatory]}]]
  [:div.file-selector
   [:label name (when mandatory [icon "star"])
    [:br]
    [:button.btn.btn-sm.btn-outline-primary
     (shorten (or filename "vyberte obrázek (JPEG nebo PNG)") 32)
     [select-image
      :on-change #(update-image-dispatcher id %)
      :enabled? (not loading?)
      :classes ["form-control-file" (when error "is-invalid")]]]]
   [error-msg error]
   (when-let [uri (or data url)]
     [:div.generator__thumb
      [:img.img-thumbnail {:src uri}]])])


(defn form-fields
  [loading? fields]
  [:div
   (for [[id field] (sort-by #(-> % second :order) fields)]
     ^{:key id}
     [:div.row.justify-content-around
      [:div.form-group.col-md-8
       (render-field loading? [id field])]])])
