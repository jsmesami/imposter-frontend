(ns mkp.imposter.alert.views
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [mkp.imposter.utils.bem :as bem :refer [bem]]))


(def module-name "alert")


(defn- message
  [id severity text]
  [:div {:class (bem module-name "message" [severity])}
   [:div {:class (bem/be module-name "text")}
    text]
   [:div {:class (bem/be module-name "dismiss")
          :on-click #(dispatch [:alert/remove-message id])}
    "\u00D7"]])


(defn alert-messages
  []
  (when-let [messages @(subscribe [:alert/messages])]
    [:div {:class module-name}
     (for [[id {:keys [severity text]}] (seq messages)]
       ^{:key id}
       [message id severity text])]))
