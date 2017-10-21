(ns app.events
  (:require
    [re-frame.core :refer [reg-event-fx]]
    [app.db :refer [AppInitial]]
    [settings :refer [api-uri]]))


(reg-event-fx
  :app/initialize
  (fn [_]
    {:db AppInitial
     :dispatch [:net/fetch-resource api-uri [:api]
                :dispatch-after [:home/posters-reload]]}))
