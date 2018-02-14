(ns mkp.imposter.net.events
  (:require
    [ajax.core :as ajax]
    [day8.re-frame.http-fx]
    [re-frame.core :refer [reg-event-fx trim-v]]
    [mkp.imposter.net.interceptors :refer [inc-loading-count dec-loading-count]]
    [mkp.imposter.settings :refer [default-request-timeout]]))


(defn default-success-fx
  [db _]
  {:db db})


(defn default-failure-fx
  [db response]
  {:db db
   :app/log [(str "Request error: " (:debug-message response)) :error]
   :dispatch [:alert/add-message :error "Spojení se nezdařilo."]})


(reg-event-fx
  :net/json-xhr
  [trim-v inc-loading-count]
  (fn [_ [method uri & {:keys [data success-fx failure-fx timeout]
                        :or {data {}
                             success-fx default-success-fx
                             failure-fx default-failure-fx
                             timeout default-request-timeout}}]]
    {:http-xhrio {:method          method
                  :uri             uri
                  :timeout         timeout
                  :params          data
                  :format          (ajax/json-request-format)
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:net/success success-fx]
                  :on-failure      [:net/failure failure-fx]}}))


(reg-event-fx
  :net/success
  [trim-v dec-loading-count]
  (fn [{:keys [db]} [success-fx response]]
    (success-fx db (js->clj response))))


(reg-event-fx
  :net/failure
  [trim-v dec-loading-count]
  (fn [{:keys [db]} [failure-fx response]]
    (failure-fx db (js->clj response))))
