(ns mkp.imposter.generator.events
  (:require
    [re-frame.core :refer [reg-event-db reg-event-fx trim-v]]
    [mkp.imposter.resources.core :refer [poster-resource]]
    [mkp.imposter.generator.form :refer [poster->form spec->form]]))


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
  (fn [db [action data]]
    (let [data->form (get {:update poster->form, :create spec->form} action)]
      (-> db
          (assoc-in [:generator :form] (data->form data))
          (assoc-in [:generator :action] action)
          (assoc :modal nil)
          (assoc :view :edit)))))


(reg-event-db
  :generator/update-form-field
  [trim-v]
  (fn [db [field-id key value]]
    (assoc-in db [:generator :form :fields field-id key] value)))
