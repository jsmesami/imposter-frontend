(ns mkp.imposter.posters.events
  (:require
    [re-frame.core :refer [reg-event-fx trim-v]]
    [mkp.imposter.posters.db :refer [PosterFilterInitial]]
    [mkp.imposter.resources.core :refer [poster-resource]]
    [mkp.imposter.utils.url :refer [m->qs]]))


(reg-event-fx
  :posters/reload
  (fn [{:keys [db]}]
    (let [posters (:posters db)
          uri (str (get-in db [:resources :endpoints :poster]) (m->qs (:filter posters)))]
      {:dispatch [:net/xhr :get uri
                  :success-fx (fn [db response]
                                {:db (-> db
                                         (assoc-in [:posters :count] (:count response))
                                         (assoc-in [:posters :next?] (string? (:next response)))
                                         (assoc-in [:posters :prev?] (string? (:previous response)))
                                         (assoc-in [:posters :list] (:results response)))})]})))

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


(reg-event-fx
  :posters/delete
  [trim-v]
  (fn [{:keys [db]} [poster-id]]
    {:dispatch [:net/xhr :delete (poster-resource db poster-id)
                :success-fx #(hash-map :dispatch-n [[:alert/add-message :success "Plakát byl úspěšně smazán" 5000]
                                                    [:posters/reload]])]}))


(reg-event-fx
  :posters/edit
  [trim-v]
  (fn [_ [poster-id]]
    {:dispatch [:generator/prepare poster-id]}))


(reg-event-fx
  :posters/create
  [trim-v]
  (fn [_]
    {:dispatch [:generator/prepare]}))
