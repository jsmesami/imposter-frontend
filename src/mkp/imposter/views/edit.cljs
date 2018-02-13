(ns mkp.imposter.views.edit
  (:require
    [mkp.imposter.generator.views.core :refer [generator]]
    [mkp.imposter.components.navbar :refer [navbar]]))


(defn edit
  []
  [:div#edit.view
   [navbar
    [:h1 "Generátor"]]
   [generator]])
