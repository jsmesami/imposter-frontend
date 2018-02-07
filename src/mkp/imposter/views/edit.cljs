(ns mkp.imposter.views.edit
  (:require
    [mkp.imposter.generator.views :refer [generator]]
    [mkp.imposter.components.navbar :refer [navbar]]))


(defn edit
  []
  [:div#edit
   [navbar
    [:h1 "Generátor"]]
   [generator]])
