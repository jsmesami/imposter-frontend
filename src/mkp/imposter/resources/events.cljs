(ns mkp.imposter.resources.events
  (:require
    [re-frame.core :refer [reg-event-fx trim-v]]
    [mkp.imposter.settings :refer [api-uri]]))


(reg-event-fx
  :resources/fetch-data
  [trim-v]
  (fn [_]
    {:dispatch [:net/json-xhr :get api-uri
                :success-fx (fn [db response]
                              {:db (assoc-in db [:resources :endpoints] response)
                               :dispatch-n [[:resources/fetch-bureau-data]
                                            [:resources/fetch-spec-data]
                                            [:posters/reload]]})]}))


(reg-event-fx
  :resources/fetch-bureau-data
  [trim-v]
  (fn [{:keys [db]}]
    {:dispatch [:net/json-xhr :get (get-in db [:resources :endpoints :bureau])
                :success-fx (fn [db response]
                              {:db (assoc-in db [:resources :bureau] response)})]}))


(reg-event-fx
  :resources/fetch-spec-data
  [trim-v]
  (fn [{:keys [db]}]
    {:dispatch [:net/json-xhr :get (get-in db [:resources :endpoints :spec])
                :success-fx (fn [db response]
                              {:db (assoc-in db [:resources :spec] response)})]}))
