(ns mkp.imposter.generator.form)


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
