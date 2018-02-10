(ns mkp.imposter.modals.views.modal
  (:require
    [goog.events :refer [listen unlisten]]
    [goog.events.EventType :refer [KEYDOWN]]
    [reagent.core :as reagent]
    [mkp.imposter.components.basic :refer [icon]]
    [mkp.imposter.components.backdrop :refer [backdrop]]
    [mkp.imposter.utils.bem :as bem]
    [mkp.imposter.utils.events :refer [click-dispatcher esc?]]))


(def module-name "modal")


(def dismiss! (click-dispatcher [:modals/clear]))


(defn dismiss-button
  []
  [:a {:class (bem/be module-name "dismiss-button")
       :href "#"
       :title "zrušit"
       :on-click dismiss!}
   [icon "cross"]])


(defn modal
  [content & {:keys [dismissable?]
              :or {dismissable? true}}]
  (let [handle-esc (fn [e] (if (esc? e) (dismiss! e) true))]
    (reagent/create-class
      {:display-name
       "modal"

       :component-will-mount
       (fn [_]
         (when dismissable?
           (listen js/window KEYDOWN handle-esc)))

       :component-will-unmount
       (fn [_]
         (when dismissable?
           (unlisten js/window KEYDOWN handle-esc)))

       :reagent-render
       (fn [& {:keys [dismissable?]
               :or {dismissable? true}}]
         [backdrop
          [:div {:class module-name}
           [:div.container
            content]]
          (when dismissable?
            [dismiss-button])])})))
