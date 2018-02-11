(ns mkp.imposter.modals.views.spec-modal
  (:require
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.modals.views.modal :refer [generic-modal]]
    [mkp.imposter.utils.bem :as bem]
    [mkp.imposter.utils.events :refer [click-dispatcher]]))


(def module-name "select-spec")


(defn spec-card
  [spec]
  [:div.col-6.col-sm-4.col-md-3.mb-4
   [:a.card {:href "#"
             :on-click (click-dispatcher [:generator/edit :create spec])}
    [:div.card-header
     (:name spec)]
    [:img.card-img-bottom
     {:src (:thumb spec)}]
    [:div.color-stripe
     {:style {:background (:color spec)}}]]])


(defn select-spec
  []
  (let [spec-list @(subscribe [:resources/spec])]
    [generic-modal
     [:div {:class module-name}
       [:div.row.text-center
        [:h2.col-sm-12 "Vyberte šablonu"]]
       [:div.row {:class (bem/be module-name "spec-list")}
        (for [spec spec-list]
          ^{:key (:id spec)}
          [spec-card spec])]]]))
