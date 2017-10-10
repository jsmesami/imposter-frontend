(ns imposter.app.effects
  (:require
    [re-frame.core :refer [reg-cofx reg-fx dispatch]]))


(reg-cofx
  :app/get-id
  (fn [coeffects _]
    (assoc coeffects :id (.getTime (js/Date.)))))


(def severity->log-fn
  {:default js/console.log
   :info js/console.info
   :warn js/console.warn
   :error js/console.error})


(reg-fx
  :app/log
  (fn [[message severity]]
    ((severity->log-fn (or severity :default)) message)))


(reg-fx
  :app/dispatch-fetch-resources
  (fn [api-endpoints]
    (let [dispatcher (fn [[res endpoint]] (dispatch [:app/fetch-resource res endpoint]))]
      (doall (map dispatcher api-endpoints)))))
