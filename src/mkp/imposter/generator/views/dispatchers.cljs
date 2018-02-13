(ns mkp.imposter.generator.views.dispatchers
  (:require
    [re-frame.core :refer [dispatch]]
    [mkp.imposter.utils.file-reader :refer [file->base64]]))


(defn- update-field-dispatcher
  [field-id key value]
  (dispatch [:generator/update-form-field field-id key value]))


(defn- validate-image-data
  [base64]
  base64)


(defn- save-image-data
  [field-id base64]
  (try
    (update-field-dispatcher field-id :data (validate-image-data base64))
    (catch js/Error e
      (update-field-dispatcher field-id :error e))))


(defn update-image-dispatcher
  [field-id file]
  (do (file->base64 file (partial save-image-data field-id))
      (update-field-dispatcher field-id :filename (.-name file))))


(defn update-text-dispatcher
  [field-id text]
  (update-field-dispatcher field-id :text text))
