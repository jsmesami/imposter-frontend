(ns mkp.imposter.home.events
  (:require
    [re-frame.core :refer [reg-event-db reg-event-fx trim-v]]
    [mkp.imposter.home.db :refer [filter->query-string posters-per-page]]))


(reg-event-fx
  :home/posters-reload
  (fn [{:keys [db]}]
    (let [posters-path [:views :home :posters]
          posters (get-in db posters-path)
          uri (str (get-in db [:api :posters]) (filter->query-string (:filter posters)))]
      {:dispatch [:net/fetch-resource uri posters-path
                  :error-msg "Nepodařilo se nahrát plakáty."
                  :translate (fn [response] (-> posters
                                                (assoc :count (:count response))
                                                (assoc :list (:results response))))]})))


(reg-event-fx
  :home/posters-update-filter
  [trim-v]
  (fn [{:keys [db]} [f]]
    {:db (update-in db [:views :home :posters :filter] #(merge % f))
     :dispatch [:home/posters-reload]}))
