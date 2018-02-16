(ns mkp.imposter.generator.request)


(defmulti prepare-field
  (fn [[_ field]]
    (-> field :type keyword)))


(defmethod prepare-field :text
  [[id field]]
  (when (:changed field)
    [id (select-keys field [:text])]))


(defmethod prepare-field :image
  [[id field]]
  (when (:changed field)
    [id (select-keys field [:data :filename])]))


(defn- prepare-fields
  [form]
  (->> form
       :fields
       seq
       (map prepare-field)
       (remove nil?)
       (into (hash-map))))


(defn form->request
  [form]
  (let [spec-id (:spec form)]
    (-> {:bureau (:bureau form)
         :fields (prepare-fields form)}
        (cond-> spec-id (assoc :spec spec-id)))))
