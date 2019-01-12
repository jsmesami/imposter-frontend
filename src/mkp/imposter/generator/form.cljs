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
   :print_pdf (:print_pdf poster)
   :bureau (or (get-in poster [:bureau :id]) (:bureau poster))
   :spec (or (get-in poster [:spec :id]) (:spec poster))
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
       (every? field-filled?)))


(defn form-changed?
  [form]
  (->> form
       :fields
       vals
       (some :changed)))
