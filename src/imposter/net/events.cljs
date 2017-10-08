(ns imposter.net.events
  (:require
    [ajax.core :as ajax]
    [day8.re-frame.http-fx]
    [re-frame.core :refer [reg-event-db reg-event-fx trim-v]]))


(reg-event-fx
  :net/load-api-urls
  (fn [{:keys [db]} _]
    {:http-xhrio {:method          :get
                  :uri             "/api/v1/"
                  :timeout         8000
                  :format          (ajax/json-request-format)
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:app/initialize]
                  :on-failure      [:net/bad-response]}}))


(reg-event-fx
  :net/bad-response
  [trim-v]
  (fn [_ [response]]
    {:app/log (str "Error: " (:debug-message response))
     :dispatch [:flash/add-message [:error "Spojení se nezdařilo."]]}))
