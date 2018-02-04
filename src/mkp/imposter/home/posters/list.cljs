(ns mkp.imposter.home.posters.list
  (:require
    [reagent.format :refer [format]]
    [re-frame.core :refer [subscribe dispatch]]
    [mkp.imposter.components.basic :refer [button icon]]
    [mkp.imposter.home.db :refer [posters-per-page]]
    [mkp.imposter.utils.bem :refer [bem] :as bem]))


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


(defn thumb-download-button
  [poster]
  [:a {:class (bem "poster-thumb" "button" ["download"])
       :title "stáhnout"
       :href (:print poster)
       :target "_blank"
       :rel "noopener noreferrer"}
      [icon "download"]])


(defn thumb-edit-button
  [poster]
  (when (:editable poster)
    [:a {:class (bem "poster-thumb" "button" ["edit"])
            :title "editovat"
            :href "#"}
        [icon "edit"]]))


(defn thumb-badges
  [poster]
  [:div {:class (bem/be "poster-thumb" "badges")}
   [:div {:class (bem "poster-thumb" "badge" ["bureau"])}
    (get-in poster [:bureau :abbrev])]])


(defn thumb
  [poster]
  [:div.col-6.col-sm-4.col-md-3.mb-4
   [:div.poster-thumb
    [:div {:class (bem/be "poster-thumb" "thumb")}
     [thumb-download-button poster]
     [thumb-edit-button poster]
     [thumb-badges poster]
     [:img {:src (:thumb poster)}]]
    [:div {:class (bem/be "poster-thumb" "title")}
     (:title poster)]
    [:div
     {:class (bem/be "poster-thumb" "color")
      :style {:background (get-in poster [:spec :color])}}]]])


(defn poster-thumbs
  [posters]
  [:div.row
   (for [poster (:list posters [])]
     ^{:key (:id poster)}
     [thumb poster])])


(defn poster-list
  []
  (let [posters @(subscribe [:home/posters])]
    (if (seq (:list posters))
      [:div.poster-list
       [poster-thumbs posters]
       [pagination posters]])))
