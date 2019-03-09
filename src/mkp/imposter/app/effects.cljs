(ns mkp.imposter.app.effects
  (:require
    [re-frame.core :refer [reg-cofx reg-fx dispatch]]
    [mkp.imposter.alert.views :refer [reload-link]]))


(reg-cofx
  :app/get-id
  (fn [coeffects _]
    (assoc coeffects :id (.getTime (js/Date.)))))


(def severity->log-fn
  {:default js/console.log
   :info    js/console.info
   :warn    js/console.warn
   :error   js/console.error})


(reg-fx
  :app/log
  (fn [[message severity]]
    (severity->log-fn (or severity :default)) message))


(defn update-app
  []
  (when (= (-> js/window
               .-applicationCache
               .-status)
           (-> js/window
               .-applicationCache
               .-UPDATEREADY))
    (dispatch [:alert/add-message "Je k dispozici nová verze aplikace."
               :info
               :children [reload-link "Načíst"]])))


(reg-fx
  :app/check-update
  (fn []
    (.addEventListener js/window "load" #(-> js/window
                                             .-applicationCache
                                             (.addEventListener "updateready" update-app)))))
