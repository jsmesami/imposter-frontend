(ns mkp.imposter.flash.events
  (:require
    [re-frame.core :refer [reg-event-db reg-event-fx trim-v inject-cofx]]))


(reg-event-fx
  :flash/add-message
  [(inject-cofx :app/get-id) trim-v]
  (fn [{:keys [db id]} [severity text]]
    {:db (assoc-in db [:flash :messages id] {:severity severity, :text text})}))


(reg-event-db
  :flash/remove-message
  [trim-v]
  (fn [db [id]]
    (update-in db [:flash :messages] dissoc id)))
