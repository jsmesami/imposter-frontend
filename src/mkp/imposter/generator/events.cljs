(ns mkp.imposter.generator.events
  (:require
    [re-frame.core :refer [reg-event-db reg-event-fx trim-v]]
    [mkp.imposter.resources.core :refer [poster-resource]]
    [mkp.imposter.generator.form :refer [poster->form spec->form]]
    [mkp.imposter.generator.request :refer [form->request]]))


(defn edit-poster-fx
  [_ poster]
  {:dispatch [:generator/edit poster]})


(reg-event-fx
  :generator/prepare
  [trim-v]
  (fn [{:keys [db]} [poster]]
    (if poster
      {:dispatch [:net/json-xhr :get (poster-resource db (:id poster))
                  :success-fx edit-poster-fx]}
      {:dispatch [:modals/set :select-spec]})))


(reg-event-fx
  :generator/create
  [trim-v]
  (fn [_ [spec]]
    {:dispatch-n [[:generator/reset-form (spec->form spec)]
                  [:modals/clear]
                  [:views/set :edit]]}))


(reg-event-fx
  :generator/edit
  [trim-v]
  (fn [_ [poster]]
    {:dispatch-n [[:generator/reset-form (poster->form poster)]
                  [:views/set :edit]]}))


(reg-event-fx
  :generator/cancel-edit
  [trim-v]
  (fn [_]
    {:dispatch-n [[:views/set :home]
                  [:posters/reload]]}))


(reg-event-db
  :generator/reset-form
  [trim-v]
  (fn [db [data]]
    (assoc-in db [:generator :form] data)))


(reg-event-db
  :generator/update-form-field
  [trim-v]
  (fn [db [field-id key value]]
    (assoc-in db [:generator :form :fields field-id key] value)))


(reg-event-fx
  :generator/preview
  [trim-v]
  (fn [_ [link]]
    {:dispatch [:modals/set :preview-poster {:thumb link}]}))


(defn submit-poster-fx
  [_ poster]
  {:dispatch-n [[:alert/add-message "Plakát byl uložen." :success 8000]
                [:generator/edit poster]]})


(reg-event-fx
  :generator/submit
  [trim-v]
  (fn [{:keys [db]} [form]]
    (if-let [poster-id (:poster form)]
      {:dispatch [:net/json-xhr :patch (poster-resource db poster-id)
                  :data (form->request form)
                  :success-fx submit-poster-fx]}
      {:dispatch [:net/json-xhr :post (get-in db [:resources :endpoints :poster])
                  :data (form->request form)
                  :success-fx submit-poster-fx]})))
