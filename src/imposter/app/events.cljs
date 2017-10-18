(ns imposter.app.events
  (:require
    [re-frame.core :refer [reg-event-fx]]
    [imposter.app.db :refer [AppInitial]]
    [imposter.settings :refer [api-uri]]))


(reg-event-fx
  :app/initialize
  (fn [_]
    {:db AppInitial
     :dispatch [:net/fetch-resource api-uri [:api]
                :dispatch-after [:home/posters-reload]]}))
