(ns flash.views
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [utils.bem :as bem :refer [bem]]))


(def module-name "flash")


(defn- message
  [id severity text]
  [:div {:class (bem module-name "message" [severity])}
   [:div {:class (bem/bm module-name "text")}
    text]
   [:div {:class (bem/bm module-name "dismiss")
          :on-click #(dispatch [:flash/remove-message id])}
    "\u00D7"]])


(defn flash-messages
  []
  (when-let [messages @(subscribe [:flash/messages])]
    [:div {:class module-name}
     (for [[id {:keys [severity text]}] (seq messages)]
       ^{:key id}
       [message id severity text])]))
