(ns mkp.imposter.generator.views.fields
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [mkp.imposter.components.basic :refer [icon input select select-image]]
    [mkp.imposter.generator.form :refer [field-filled?]]
    [mkp.imposter.generator.views.components :refer [help-text char-counter error-msg]]
    [mkp.imposter.generator.views.dispatchers :refer [on-change-text-dispatcher on-change-image-dispatcher]]
    [mkp.imposter.resources.core :refer [resource->options]]
    [mkp.imposter.utils.string :refer [shorten]]))


(defmulti render-field
          (fn [_ [_ field]]
            (-> field :type keyword)))


(defmethod render-field :text
  [loading? [id field]]
  (let [{:keys [name text help_text error widget char_limit mandatory hidden]} field]
    [:label
     name
     (when mandatory (if (field-filled? field) [icon "check"] [icon "star"]))
     [help-text help_text]
     [input
      :value text
      :on-change #(on-change-text-dispatcher id %)
      :enabled? (not loading?)
      :classes ["form-control" (when error "is-invalid")]
      :widget (keyword (or widget :input))
      :attrs {:id id
              :maxLength char_limit
              :readOnly hidden}]
     [char-counter text char_limit]
     [error-msg error]]))


(defmethod render-field :image
  [loading? [id field]]
  (let [{:keys [name filename url data help_text error mandatory]} field]
    [:div.file-selector
     [:label
      name
      (when mandatory (if (field-filled? field) [icon "check"] [icon "star"]))
      [:br]
      [help-text help_text]
      [:label.btn.btn-outline-primary.btn-sm
       {:for id}
       (shorten (or filename "vyberte obrázek (JPEG nebo PNG)") 32)]
      [select-image
       :on-change #(on-change-image-dispatcher id %)
       :enabled? (not loading?)
       :classes ["form-control-file" (when error "is-invalid")]
       :attrs {:id id
               :accept "image/png, image/jpeg"}]]
     [error-msg error]
     (when-let [uri (or data url)]
       [:div.generator__thumb
        [:img.img-thumbnail {:src uri}]])]))


(defn bureau-special-field
  "Special field (not present in form fields). Adds bureau ID to the form
  and also populates read-only but mandatory 'bureau address' form field."
  [loading? form]
  (let [bureau-resource @(subscribe [:resources/bureau])
        full-address #(str "Pobočka " (:name %) ", " (:address %))
        bureau-id->address (->> bureau-resource
                                (map #(vector (:id %) (full-address %)))
                                (into (hash-map)))]
    [:div.row.justify-content-around
     [:div.form-group.col-md-8
      [:label
       "Pobočka" (if (pos? (:bureau form)) [icon "check"] [icon "star"])
       [select (resource->options bureau-resource)
        :value (:bureau form)
        :classes ["form-control"]
        :enabled? (not loading?)
        :on-change #(let [bureau-id (int %)]
                      (dispatch [:generator/reset-form (assoc form :bureau bureau-id)])
                      (on-change-text-dispatcher :bureau_address (bureau-id->address bureau-id)))]]]]))


(defn form-fields
  [loading? form]
  [:div
   [bureau-special-field loading? form]
   (for [[id field] (sort-by #(-> % second :order) (:fields form))]
     ^{:key id}
     [:div.row.justify-content-around
      [:div.form-group.col-md-8
       (render-field loading? [id field])]])])
