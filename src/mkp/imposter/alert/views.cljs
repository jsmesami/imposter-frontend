(ns mkp.imposter.alert.views
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [mkp.imposter.alert.core :refer [kind->severity]]
    [mkp.imposter.utils.bem :as bem :refer [bem]]
    [mkp.imposter.utils.events :refer [reload!]]))


(def module-name "alert")


(defn reload-link
  []
  [:span " "
   [:a.alert-link
    {:href "#"
     :on-click reload!}
    "Znovu načíst"]])


(defn- alert
  [id text kind severity]
  [:div {:class (bem/bm module-name [severity])}
   text
   (when (= kind :server-error)
     [reload-link])
   [:button.close
    {:on-click #(dispatch [:alert/remove-message id])}
    "\u00D7"]])


(defn alerts
  []
  (when-let [messages @(subscribe [:alert/messages])]
    [:div.alerts
     (for [[id {:keys [text kind]}] (seq messages)]
       ^{:key id}
       [alert id text kind (kind->severity kind)])]))
