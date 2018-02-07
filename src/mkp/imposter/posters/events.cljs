(ns mkp.imposter.posters.events
  (:require
    [re-frame.core :refer [reg-event-db reg-event-fx trim-v]]
    [mkp.imposter.posters.db :refer [posters-per-page PosterFilterInitial]]
    [mkp.imposter.utils.url :refer [m->qs]]))


(reg-event-fx
  :posters/reload
  (fn [{:keys [db]}]
    (let [posters (:posters db)
          uri (str (get-in db [:resources :endpoints :poster]) (m->qs (:filter posters)))]
      {:dispatch [:net/fetch-resource uri [:posters]
                  :error-msg "Nepodařilo se nahrát plakáty."
                  :transform (fn [response] (-> posters
                                                (assoc :count (:count response))
                                                (assoc :next? (string? (:next response)))
                                                (assoc :prev? (string? (:previous response)))
                                                (assoc :list (:results response))))]})))


(reg-event-fx
  :posters/update-filter
  [trim-v]
  (fn [{:keys [db]} [f]]
    {:db (update-in db [:posters :filter] #(merge % f))
     :dispatch [:posters/reload]}))


(reg-event-fx
  :posters/reset-filter
  [trim-v]
  (fn [{:keys [db]}]
    {:db (assoc-in db [:posters :filter] PosterFilterInitial)
     :dispatch [:posters/reload]}))
