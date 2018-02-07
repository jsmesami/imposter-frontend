(ns mkp.imposter.posters.views
  (:require
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.posters.components.filter :refer [poster-filter]]
    [mkp.imposter.posters.components.pagination :refer [pagination]]
    [mkp.imposter.posters.components.thumbs :refer [poster-thumbs]]))


(defn poster-list
  []
  (let [posters @(subscribe [:posters/list])]
    [:div.container
     [poster-filter]
     [:div.poster-list
      [poster-thumbs posters]
      [pagination posters]]]))
