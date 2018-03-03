(ns mkp.imposter.posters.components.thumbs
  (:require
    [mkp.imposter.components.basic :refer [icon]]
    [mkp.imposter.posters.db :refer [posters-per-page]]
    [mkp.imposter.utils.bem :refer [bem] :as bem]
    [mkp.imposter.utils.events :refer [click-dispatcher]]
    [mkp.imposter.utils.url :refer [get-filename]]))


(def module-name "poster-thumb")


(defn thumb-preview-button
  [poster]
  [:a {:class (bem module-name "button" ["preview"])
       :title "náhled"
       :href "#"
       :on-click (click-dispatcher [:posters/preview (:thumb poster)])}
      [icon "media"]])


(defn thumb-download-button
  [poster]
  [:a {:class (bem module-name "button" ["download"])
       :title "stáhnout"
       :href (:print poster)
       :target "_blank"
       :rel "noopener noreferrer"
       :download (-> poster :print get-filename)}
      [icon "download"]])


(defn thumb-edit-button
  [poster]
  [:a {:class (bem module-name "button" ["edit"])
       :title "editovat"
       :href "#"
       :on-click (click-dispatcher [:posters/edit (:id poster)])}
      [icon "edit"]])


(defn thumb-delete-button
  [poster]
  [:a {:class (bem module-name "button" ["delete"])
       :title "smazat"
       :href "#"
       :on-click (click-dispatcher [:posters/delete (:id poster)])}
      [icon "trash"]])


(defn thumb-buttons
  [poster]
  [:div {:class (bem/be module-name "buttons")}
   (for [[i fun] (map-indexed vector [thumb-preview-button thumb-download-button
                                      thumb-edit-button thumb-delete-button])]
     ^{:key i}
     [fun poster])])


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
  [:div.color-stripe
   {:style {:background (get-in poster [:spec :color])}}])


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
