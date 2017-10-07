(ns imposter.flash.views
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [imposter.utils.bem :as bem :refer [bem]]))


(def module-name "flash")


(defn flash-message
  [id severity text]
  [:div {:class (bem module-name "message" [severity])}
   [:div {:class (bem/bm module-name "text")}
    text]
   [:div {:class (bem/bm module-name "dismiss")
          :on-click #(dispatch [:flash/remove-message id])}
    "\u00D7"]])


(defn flash-messages-component
  []
  (when-let [messages @(subscribe [:flash/messages])]
    [:div {:class module-name}
     (for [[id {:keys [severity text]}] (seq messages)]
       ^{:key id}
       [flash-message id severity text])]))
