(ns mkp.imposter.resources.events
  (:require
    [re-frame.core :refer [reg-event-fx trim-v]]
    [mkp.imposter.settings :refer [api-uri]]))


(reg-event-fx
  :resources/fetch-resources
  [trim-v]
  (fn [_]
    {:dispatch [:net/fetch-resource api-uri [:resources :endpoints]
                :dispatch-after [[:resources/fetch-bureau-resource]
                                 [:resources/fetch-spec-resource]
                                 [:home/posters-reload]]]}))


(reg-event-fx
  :resources/fetch-bureau-resource
  [trim-v]
  (fn [{:keys [db]}]
    {:dispatch [:net/fetch-resource (get-in db [:resources :endpoints :bureau]) [:resources :bureau]
                :error-msg "Nepodařilo se nahrát pobočky."]}))


(reg-event-fx
  :resources/fetch-spec-resource
  [trim-v]
  (fn [{:keys [db]}]
    {:dispatch [:net/fetch-resource (get-in db [:resources :endpoints :spec]) [:resources :spec]
                :error-msg "Nepodařilo se nahrát šablony."]}))
