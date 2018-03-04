(ns mkp.imposter.posters.events
  (:require
    [reagent.format :refer [format]]
    [re-frame.core :refer [reg-event-fx trim-v]]
    [mkp.imposter.alert.core :refer [status->kind]]
    [mkp.imposter.posters.db :refer [PosterFilterInitial]]
    [mkp.imposter.resources.core :refer [poster-resource]]
    [mkp.imposter.utils.url :refer [m->qs]]))


(reg-event-fx
  :posters/reload
  (fn [{:keys [db]}]
    (let [posters (:posters db)
          uri (str (get-in db [:resources :endpoints :poster]) (m->qs (:filter posters)))]
      {:dispatch [:net/json-xhr :get uri
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


(defn poster-delete-success
  [_]
  {:dispatch-n [[:alert/add-message "Plakát byl úspěšně smazán." :success 8000]
                [:posters/reload]]})


(defn poster-delete-failure
  [_ response]
  (let [failure-kind (-> response :status status->kind)]
    (if (= :server-error failure-kind)
      {:dispatch [:alert/add-message "Spojení se nezdařilo." failure-kind]}
      {:dispatch-n [[:alert/add-message "Plakát se nepodařilo smazat." failure-kind]
                    [:posters/reload]]})))


(reg-event-fx
  :posters/delete
  [trim-v]
  (fn [{:keys [db]} [poster]]
    (when (js/confirm (format "Nevratná operace.\nOpravdu chcete smazat plakát \"%s\"?" (:title poster)))
      {:dispatch [:net/json-xhr :delete (poster-resource db (:id poster))
                  :success-fx poster-delete-success
                  :failure-fx poster-delete-failure]})))


(reg-event-fx
  :posters/preview
  [trim-v]
  (fn [_ [link]]
    {:dispatch [:modals/set :preview-poster {:thumb link}]}))


(reg-event-fx
  :posters/edit
  [trim-v]
  (fn [_ [poster]]
    (let [fx {:dispatch [:generator/prepare poster]}
          poster-age-ms (- (js/Date.) (js/Date. (:modified poster)))
          three-days-ms (* 3 24 60 60 1000)]
      (if (> poster-age-ms three-days-ms)
        (when (js/confirm "Plakát je starší než 3 dny.\nOpravdu ho chcete editovat?")
          fx)
        fx))))


(reg-event-fx
  :posters/create
  [trim-v]
  (fn [_]
    {:dispatch [:generator/prepare]}))
