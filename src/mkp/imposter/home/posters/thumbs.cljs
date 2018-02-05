(ns mkp.imposter.home.posters.thumbs
  (:require
    [mkp.imposter.components.basic :refer [icon]]
    [mkp.imposter.utils.bem :refer [bem] :as bem]
    [mkp.imposter.home.db :refer [posters-per-page]]))


(def module-name "poster-thumb")


(defn thumb-download-button
  [poster]
  [:a {:class (bem module-name "button" ["download"])
       :title "stáhnout"
       :href (:print poster)
       :target "_blank"
       :rel "noopener noreferrer"}
      [icon "download"]])


(defn thumb-edit-button
  [poster]
  (when (:editable poster)
    [:a {:class (bem module-name "button" ["edit"])
            :title "editovat"
            :href "#"}
        [icon "edit"]]))


(defn thumb-delete-button
  [poster]
  (when (:editable poster)
    [:a {:class (bem module-name "button" ["delete"])
            :title "smazat"
            :href "#"}
        [icon "trash"]]))


(defn thumb-buttons
  [poster]
  [:div {:class (bem/be module-name "buttons")}
   [thumb-download-button poster]
   [thumb-edit-button poster]
   [thumb-delete-button poster]])


(defn thumb-badges
  [poster]
  [:div {:class (bem/be module-name "badges")}
   [:div {:class (bem module-name "badge" ["bureau"])}
    (get-in poster [:bureau :abbrev])]])


(defn thumb-title
  [poster]
  [:div {:class (bem/be module-name "title")}
   (:title poster)])


(defn thumb-color-code
  [poster]
  [:div
   {:class (bem/be module-name "color")
    :style {:background (get-in poster [:spec :color])}}])


(defn thumb
  [poster]
  [:div.col-6.col-sm-4.col-md-3.mb-4
   [:div.poster-thumb
    [:div {:class (bem/be module-name "thumb")}
     [thumb-buttons poster]
     [thumb-badges poster]
     [:img {:src (:thumb poster)}]]
    [thumb-title poster]
    [thumb-color-code poster]]])


(defn thumb-placeholder
  []
  [:div.col-6.col-sm-4.col-md-3.mb-4
   [:div.poster-placeholder]])


(defn poster-thumbs
  [posters]
  (let [posters-on-page (count (:list posters))]
    [:div.row
     (for [poster (:list posters)]
       ^{:key (:id poster)}
       [thumb poster])
     (for [n (range (- posters-per-page posters-on-page))]
       ^{:key (str "placeholder-" n)}
       [thumb-placeholder])]))
