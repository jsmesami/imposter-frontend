(ns mkp.imposter.generator.form)


(defn data->form-fields
  [data]
  (->> (:fields data)
       (merge (get-in data [:spec :fields]))
       (map (fn [[k v]] (assoc v :id k)))
       (sort-by :order)))
