(ns mkp.imposter.home.events
  (:require
    [re-frame.core :refer [reg-event-db reg-event-fx trim-v]]
    [mkp.imposter.home.db :refer [posters-per-page]]
    [mkp.imposter.utils.url :refer [m->qs]]))


(reg-event-fx
  :home/posters-reload
  (fn [{:keys [db]}]
    (let [posters-path [:views :home :posters]
          posters (get-in db posters-path)
          uri (str (get-in db [:api :posters]) (m->qs (:filter posters)))]
      {:dispatch [:net/fetch-resource uri posters-path
                  :error-msg "Nepodařilo se nahrát plakáty."
                  :transform (fn [response] (-> posters
                                                (assoc :count (:count response))
                                                (assoc :list (:results response))))]})))


(reg-event-fx
  :home/posters-update-filter
  [trim-v]
  (fn [{:keys [db]} [f]]
    {:db (update-in db [:views :home :posters :filter] #(merge % f))
     :dispatch [:home/posters-reload]}))
