(ns mkp.imposter.modals.views.spec-modal
  (:require
    [reagent.core :as reagent]
    [re-frame.core :refer [subscribe]]
    [mkp.imposter.modals.views.modal :refer [generic-modal]]
    [mkp.imposter.components.basic :refer [icon]]
    [mkp.imposter.utils.bem :as bem]
    [mkp.imposter.utils.events :refer [click-dispatcher]]))


(def module-name "select-spec")


(defn spec-item
  [spec]
  [:a.card {:href "#"
            :on-click (click-dispatcher [:generator/create spec])}
   [:div.card-header
    (:name spec)]
   [:img.card-img-bottom
    {:src (:thumb spec)}]
   [:div.color-stripe
    {:style {:background (:color spec)}}]])


(defn pagination-buttons
  [cursor first? last?]
  [:div
   [:button.button.button--left
    {:on-click #(when-not first? (swap! cursor dec))
     :disabled (when first? "disabled")}
    [icon "left"]]
   [:button.button.button--right
    {:on-click #(when-not last? (swap! cursor inc))
     :disabled (when last? "disabled")}
    [icon "right"]]])


(defn spec-pagination
  []
  (let [spec-list @(subscribe [:resources/spec])
        last-index (dec (count spec-list))
        cursor (reagent/atom 0)]
    (fn []
      [:div.col-sm-12 {:class (bem/be module-name "content")}
       [:div {:class (bem/be module-name "card")}
        [:strong {:class (bem/be module-name "counter")}
         (str (inc @cursor) "/" (inc last-index))]
        [pagination-buttons cursor (= @cursor 0) (= @cursor last-index)]
        [spec-item (nth spec-list @cursor)]]])))


(defn select-spec
  []
  [generic-modal
   [:div {:class module-name}
    [:div.row.text-center
     [:h2.col-sm-12 "Vyberte šablonu"]
     [spec-pagination]]]])
