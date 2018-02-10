(ns mkp.imposter.generator.events
  (:require
    [re-frame.core :refer [reg-event-fx trim-v]]
    [mkp.imposter.resources.core :refer [poster-resource]]
    [mkp.imposter.generator.form :refer [poster-data->form-fields]]))


(reg-event-fx
  :generator/prepare
  [trim-v]
  (fn [{:keys [db]} [poster-id]]
    (if poster-id
      {:dispatch [:net/xhr :get (poster-resource db poster-id)
                  :success-fx (fn [db response]
                                {:db (assoc-in db [:generator :fields] (poster-data->form-fields response))})]}
      {:db (assoc db :modal :select-spec)})))
