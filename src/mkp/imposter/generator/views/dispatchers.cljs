(ns mkp.imposter.generator.views.dispatchers
  (:require
    [re-frame.core :refer [dispatch]]
    [mkp.imposter.utils.file-reader :refer [file->base64]]))


(defn update-field-dispatcher
  [field-id kvs]
  (doseq [[key value] kvs]
    (dispatch [:generator/update-form-field field-id key value])))


(defn- validate-image-data
  [base64]
  (when-not (re-matches #"^data:image/(jpe?g|png);.*" (subs base64 0 16))
    (throw (js/Error. "Obrázek musí být JPEG nebo PNG.")))
  (when (> (.-length base64) (* 3 1024 1024))
    (throw (js/Error. "Obrázek je moc velký. Maximum je 3MB.")))
  base64)


(defn- save-image-data
  [field-id filename base64]
  (try
    (update-field-dispatcher field-id {:data (validate-image-data base64)
                                       :filename filename
                                       :error nil})
    (catch js/Error e
      (update-field-dispatcher field-id {:error (.-message e)}))))


(defn update-image-dispatcher
  [field-id file]
  (file->base64 file (partial save-image-data field-id (.-name file))))


(defn update-text-dispatcher
  [field-id text]
  (update-field-dispatcher field-id {:text text}))
