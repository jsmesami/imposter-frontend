(ns mkp.imposter.generator.form)


(defn poster-data->form-fields
  [poster-data]
  (->> (:fields poster-data)
       (merge (get-in poster-data [:spec :fields]))
       (map (fn [[k v]] (assoc v :id k)))
       (sort-by :order)))
