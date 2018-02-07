(ns mkp.imposter.alert.events
  (:require
    [re-frame.core :refer [dispatch reg-event-db reg-event-fx trim-v inject-cofx]]))


(reg-event-fx
  :alert/add-message
  [(inject-cofx :app/get-id) trim-v]
  (fn [{:keys [db id]} [severity text timeout]]
    (when timeout
      (js/setTimeout #(dispatch [:alert/remove-message id]) timeout))
    {:db (assoc-in db [:alert :messages id] {:severity severity, :text text})}))


(reg-event-db
  :alert/remove-message
  [trim-v]
  (fn [db [id]]
    (update-in db [:alert :messages] dissoc id)))
