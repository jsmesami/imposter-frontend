(ns imposter.common.events
  (:require [ajax.core :as ajax]
            [day8.re-frame.http-fx]
            [re-frame.core :refer [reg-event-fx trim-v]]))


(reg-event-fx
  :common/load-init-data
  (fn [{:keys [db]} _]
    {:http-xhrio {:method          :get
                  :uri             "https://api.github.com/orgs/day8"
                  :timeout         8000
                  :format          (ajax/json-request-format)
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:common/initialize]
                  :on-failure      [:common/bad-response]}}))


(reg-event-fx
  :common/initialize
  [trim-v]
  (fn [{:keys [db]} [response]]
    {:db (assoc db :data (js->clj response))}))


(reg-event-fx
  :common/bad-response
  [trim-v]
  (fn [{:keys [db]} [response]]
    {}))
