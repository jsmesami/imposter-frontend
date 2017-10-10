(ns imposter.home.components.poster-list
  (:require
    [imposter.utils.bem :as bem]))


(defn poster-thumb
  [spec]
  [:div {:class "poster-thumb"}
   (:title spec)])


(defn poster-list
  [posters]
  (let [items-per-page 12]
    (when posters
      [:div {:class "poster-list"}
       (for [spec (take items-per-page posters)]
         ^{:key (:id spec)}
         [poster-thumb spec])])))
