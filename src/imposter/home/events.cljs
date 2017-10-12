(ns imposter.home.events
  (:require
    [re-frame.core :refer [reg-event-fx trim-v]]
    [imposter.home.db :refer [filter->query-string]]))


(reg-event-fx
  :home/reload-posters
  [trim-v]
  (fn [{:keys [db]}]
    (let [api (:api db)
          poster-filter (get-in db [:views :home :poster-filter])]
      (let [uri (str (:posters api) (filter->query-string poster-filter))]
        {:dispatch [:net/fetch-resource uri [:views :home :poster-list]
                    :error-msg "Nepodařilo se nahrát plakáty."]}))))
