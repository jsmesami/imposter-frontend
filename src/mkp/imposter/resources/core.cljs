(ns mkp.imposter.resources.core
  (:require
    [mkp.imposter.resources.events]
    [mkp.imposter.resources.subs]
    [mkp.imposter.utils.string :refer [locale-compare]]))


(defn poster-resource
  "Assemble poster resource from poster ID"
  [db poster-id]
  (str (get-in db [:resources :endpoints :poster]) poster-id "/"))


(defn resource->options
  "Convert list of resource items into select input options"
  [resource]
  (->> resource
       (sort-by :name locale-compare)
       (map #(vector (:name %) (:id %)))
       (cons ["-" ""])))
