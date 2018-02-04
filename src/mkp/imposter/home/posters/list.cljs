(ns mkp.imposter.home.posters.list
  (:require
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.home.posters.pagination :refer [pagination]]
    [mkp.imposter.home.posters.thumbs :refer [poster-thumbs]]))


(defn poster-list
  []
  (let [posters @(subscribe [:home/posters])]
    [:div.poster-list
     [poster-thumbs posters]
     [pagination posters]]))
