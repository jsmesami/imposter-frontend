(ns mkp.imposter.net.events
  (:require
    [ajax.core :as ajax]
    [day8.re-frame.http-fx]
    [reagent.format :refer [format]]
    [re-frame.core :refer [reg-event-db reg-event-fx trim-v]]
    [mkp.imposter.settings :refer [default-request-timeout]]))


(reg-event-fx
  :net/fetch-resource
  [trim-v]
  (fn [{:keys [db]} [uri save-path & {:keys [error-msg translate dispatch-after]
                                      :or   {error-msg "Spojení se nezdařilo."
                                             translate (fn [src] src)}}]]
    {:db (assoc-in db [:net :loading] true)
     :http-xhrio {:method          :get
                  :uri             uri
                  :timeout         default-request-timeout
                  :format          (ajax/json-request-format)
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:net/success save-path translate dispatch-after]
                  :on-failure      [:net/failure error-msg]}}))


(reg-event-fx
  :net/success
  [trim-v]
  (fn [{:keys [db]} [save-path translate dispatch-after response]]
    {:dispatch-n [dispatch-after]
     :db (-> db
             (assoc-in [:net :loading] false)
             (assoc-in save-path (-> response
                                     js->clj
                                     translate)))}))


(reg-event-fx
  :net/failure
  [trim-v]
  (fn [{:keys [db]} [message response]]
    {:db (assoc-in db [:net :loading] false)
     :app/log [(str "Request error: " (:debug-message response)) :error]
     :dispatch [:flash/add-message :error message]}))
