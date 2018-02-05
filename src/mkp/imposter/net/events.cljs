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
  (fn [{:keys [db]} [uri save-path & {:keys [error-msg transform dispatch-after]
                                      :or   {error-msg "Spojení se nezdařilo."
                                             transform (fn [src] src)}}]]
    {:db (update-in db [:net :loading-count] inc)
     :http-xhrio {:method          :get
                  :uri             uri
                  :timeout         default-request-timeout
                  :format          (ajax/json-request-format)
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:net/success save-path transform dispatch-after]
                  :on-failure      [:net/failure error-msg]}}))


(reg-event-fx
  :net/success
  [trim-v]
  (fn [{:keys [db]} [save-path transform dispatch-after response]]
    {:dispatch-n [dispatch-after]
     :db (-> db
             (update-in [:net :loading-count] #(if (pos? %) (dec %) 0))
             (assoc-in save-path (-> response
                                     js->clj
                                     transform)))}))


(reg-event-fx
  :net/failure
  [trim-v]
  (fn [{:keys [db]} [message response]]
    {:db (update-in db [:net :loading-count] #(if (pos? %) (dec %) 0))
     :app/log [(str "Request error: " (:debug-message response)) :error]
     :dispatch [:alert/add-message :error message]}))
