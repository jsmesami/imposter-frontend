(ns mkp.imposter.generator.views.core
  (:require
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.generator.views.buttons :refer [generator-buttons]]
    [mkp.imposter.generator.views.fields :refer [form-fields]]))


(defn generator
  []
  (let [form @(subscribe [:generator/form])
        loading? @(subscribe [:net/loading?])]
    [:div.generator.container
     [form-fields loading? form]
     [generator-buttons loading? form]]))
