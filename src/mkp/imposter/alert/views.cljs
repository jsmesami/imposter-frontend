(ns mkp.imposter.alert.views
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [mkp.imposter.utils.bem :as bem :refer [bem]]))


(def module-name "alert")


(defn- alert
  [id severity text]
  [:div {:class (bem/bm module-name [severity])}
   text
   [:button.close
    {:on-click #(dispatch [:alert/remove-message id])}
    "\u00D7"]])


(defn alerts
  []
  (when-let [messages @(subscribe [:alert/messages])]
    [:div.alerts
     (for [[id {:keys [severity text]}] (seq messages)]
       ^{:key id}
       [alert id severity text])]))
