(ns mkp.imposter.generator.form
  (:require
    [mkp.imposter.utils.string :refer [filled?]]))


(defn spec->form
  [spec]
  {:spec (:id spec)
   :fields (:fields spec)})


(defn poster->form
  [poster]
  {:poster (:id poster)
   :thumb (:thumb poster)
   :print (:print poster)
   :bureau (get-in poster [:bureau :id])
   :spec (get-in poster [:spec :id])
   :fields (merge (get-in poster [:spec :fields])
                  (:fields poster))})


(defmulti field-filled?
          (fn [{:keys [type]}]
            (keyword type)))


(defmethod field-filled? :text
  [{:keys [text]}]
  (filled? text))


(defmethod field-filled? :image
  [{:keys [data filename url id]}]
  (or (and (filled? data) (filled? filename))
      (and (filled? url) (number? id))))


(defn form-filled?
  [form]
  (->> form
       :fields
       vals
       (filter :mandatory)
       (map field-filled?)
       (every? true?)))


(defn form-changed?
  [form]
  (->> form
       :fields
       vals
       (some :changed)))
