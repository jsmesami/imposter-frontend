(ns mkp.imposter.generator.events
  (:require
    [re-frame.core :refer [reg-event-db reg-event-fx trim-v]]
    [mkp.imposter.resources.core :refer [poster-resource]]
    [mkp.imposter.generator.form :refer [data->form-fields]]))


(reg-event-fx
  :generator/prepare
  [trim-v]
  (fn [{:keys [db]} [poster-id]]
    (if poster-id
      {:dispatch [:net/xhr :get (poster-resource db poster-id)
                  :success-fx (fn [_ response]
                                {:dispatch [:generator/edit :update response]})]}
      {:db (assoc db :modal :select-spec)})))


(reg-event-db
  :generator/edit
  [trim-v]
  (fn [db [mode data]]
    (-> db
        (assoc-in [:generator :fields] (data->form-fields data))
        (assoc-in [:generator :mode] mode)
        (assoc :modal nil)
        (assoc :view :edit))))
