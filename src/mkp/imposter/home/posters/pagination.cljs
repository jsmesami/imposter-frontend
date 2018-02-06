(ns mkp.imposter.home.posters.pagination
  (:require
    [re-frame.core :refer [subscribe dispatch]]
    [mkp.imposter.components.basic :refer [button]]
    [mkp.imposter.home.db :refer [posters-per-page]]))


(defn pagination
  [posters]
  (let [loading? @(subscribe [:net/loading?])
        offset (get-in posters [:filter :offset])
        paginate #(dispatch [:home/posters-update-filter {:offset %}])]
    [:div.poster-pagination.row.mb-4
     [:div.col-12
      [button "novější"
       :icon-name "left"
       :on-click #(paginate (- offset posters-per-page))
       :enabled? (and (not loading?) (:prev? posters))
       :busy? loading?]
      [button "starší"
       :icon-name "right"
       :on-click #(paginate (+ offset posters-per-page))
       :enabled? (and (not loading?) (:next? posters))
       :busy? loading?]]]))
