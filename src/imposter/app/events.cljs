(ns imposter.app.events
  (:require
    [ajax.core :as ajax]
    [day8.re-frame.http-fx]
    [reagent.format :refer [format]]
    [re-frame.core :refer [reg-event-db reg-event-fx trim-v]]
    [imposter.app.settings :refer [api-uri default-request-timeout]]))


(reg-event-fx
  :app/initialize
  (fn [_]
    {:db {:view :home}
     :dispatch [:app/fetch-api-urls]}))


(reg-event-fx
  :app/fetch-api-urls
  (fn [_]
    {:http-xhrio {:method          :get
                  :uri             api-uri
                  :timeout         default-request-timeout
                  :format          (ajax/json-request-format)
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:app/populate-db]
                  :on-failure      [:app/bad-response "Spojení se nepodařilo."]}}))


(reg-event-fx
  :app/bad-response
  [trim-v]
  (fn [_ [message response]]
    {:app/log [(str "Request error: " (:debug-message response)) :error]
     :dispatch [:flash/add-message :error message]}))


(reg-event-fx
  :app/populate-db
  [trim-v]
  (fn [{:keys [db]} [response]]
    (let [api-endpoints (js->clj response)]
      {:db (assoc db :api api-endpoints)
       :app/dispatch-fetch-resources (seq api-endpoints)})))


(reg-event-fx
  :app/fetch-resource
  [trim-v]
  (fn [_ [res endpoint]]
    {:http-xhrio {:method          :get
                  :uri             endpoint
                  :timeout         default-request-timeout
                  :format          (ajax/json-request-format)
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:app/save-resource res]
                  :on-failure      [:app/bad-response (format "Nepodařilo se nahrát zdroj: %s." (name res))]}}))


(reg-event-db
  :app/save-resource
  [trim-v]
  (fn [db [res data]]
    (assoc-in db [:resources res] (js->clj data))))
