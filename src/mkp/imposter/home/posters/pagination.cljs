(ns mkp.imposter.home.posters.pagination
  (:require
    [re-frame.core :refer [subscribe dispatch]]
    [mkp.imposter.components.basic :refer [button]]
    [mkp.imposter.home.db :refer [posters-per-page]]))


(defn pagination
  [posters]
  (let [loading? @(subscribe [:net/loading?])
        offset (get-in posters [:filter :offset])
        can-go-bw? (pos? offset)
        can-go-fw? (pos? (- (:count posters) offset))
        paginate #(dispatch [:home/posters-update-filter {:offset %}])]
    [:div.poster-pagination.row.mb-4
     [:div.col-12
      [button "novější"
       :on-click #(paginate (- offset posters-per-page))
       :enabled? can-go-bw?
       :busy? loading?]
      [button "starší"
       :on-click #(paginate (+ offset posters-per-page))
       :enabled? can-go-fw?
       :busy? loading?]]]))
