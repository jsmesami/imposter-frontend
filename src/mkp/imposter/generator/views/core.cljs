(ns mkp.imposter.generator.views.core
  (:require
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.generator.views.fields :refer [form-fields]]))


(defn generator
  []
  (let [data @(subscribe [:generator/data])
        loading? @(subscribe [:net/loading?])
        fields (get-in data [:form :fields])]
    [:div.generator.container
     [form-fields loading? fields]]))
