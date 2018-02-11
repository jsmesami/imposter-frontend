(ns mkp.imposter.generator.views
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [mkp.imposter.components.basic :refer [input icon]]))


(defmulti render-field
          (fn [loading? [id {:keys [type]}]]
            (keyword type)))


(defmethod render-field :text
  [loading? [id {:keys [name text widget char_limit mandatory]}]]
  [:label name (when mandatory [icon "star"])
   [input
    :value text
    :on-change #(dispatch [:generator/update-form-field id :text %])
    :enabled? (not loading?)
    :classes ["form-control"]
    :widget (keyword (or widget :input))
    :attrs {:maxLength char_limit}]])


(defmethod render-field :image
  [loading? [id {:keys [name mandatory]}]]
  [:label name (when mandatory [icon "star"])
   [input
    :enabled? (not loading?)
    :classes ["form-control-file"]
    :type "file"]])


(defn generator
  []
  (let [data @(subscribe [:generator/data])
        loading? @(subscribe [:net/loading?])
        sorted-fields (sort-by #(-> % second :order) (get-in data [:form :fields]))]
    [:div.generator.container
     (for [[id field] sorted-fields]
       ^{:key id}
       [:div.row.justify-content-around
        [:div.form-group.col-md-8
         (render-field loading? [id field])]])]))
