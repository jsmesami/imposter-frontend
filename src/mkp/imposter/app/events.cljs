(ns mkp.imposter.app.events
  (:require
    [re-frame.core :refer [reg-event-fx]]
    [mkp.imposter.app.db :refer [AppInitial]]
    [mkp.imposter.settings :refer [api-uri]]))


(reg-event-fx
  :app/initialize
  (fn [_]
    {:db AppInitial
     :dispatch [:net/fetch-resource api-uri [:api]
                :dispatch-after [:home/posters-reload]]}))
