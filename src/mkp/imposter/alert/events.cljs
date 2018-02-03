(ns mkp.imposter.alert.events
  (:require
    [re-frame.core :refer [reg-event-db reg-event-fx trim-v inject-cofx]]))


(reg-event-fx
  :alert/add-message
  [(inject-cofx :app/get-id) trim-v]
  (fn [{:keys [db id]} [severity text]]
    {:db (assoc-in db [:alert :messages id] {:severity severity, :text text})}))


(reg-event-db
  :alert/remove-message
  [trim-v]
  (fn [db [id]]
    (update-in db [:alert :messages] dissoc id)))
