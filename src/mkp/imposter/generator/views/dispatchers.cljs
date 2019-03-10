(ns mkp.imposter.generator.views.dispatchers
  (:require
    [re-frame.core :refer [dispatch]]
    [mkp.imposter.settings :refer [max-image-length]]
    [mkp.imposter.utils.file-reader :refer [file->base64]]
    [mkp.imposter.utils.string :refer [prepos]]))


(defn- update-field-dispatcher
  [field-id kvs]
  (doseq [[key value] kvs]
    (dispatch [:generator/update-form-field field-id key value])))


(defn- resize-image
  [img width height]
  (let [canvas (doto (js/document.createElement "canvas")
                 (-> .-width (set! width))
                 (-> .-height (set! height)))
        context (.getContext canvas "2d")]
    (.drawImage context img 0 0 width height)
    (-> context .-canvas (.toDataURL "image/jpeg" 0.65))))


(defn constrain-image-size
  [img]
  (let [width (.-width img)
        height (.-height img)]
    (if (> (max width height) max-image-length)
      (let [ratio (min (/ max-image-length height) (/ max-image-length width))]
        (resize-image img (* width ratio) (* height ratio)))
      (.-src img))))


(defn- store-image-data
  [field-id filename base64-data]
  (update-field-dispatcher field-id
    {:data base64-data
     :filename filename
     :error nil
     :changed true}))


(defn- prepare-image
  [field-id filename base64-data]
  (doto (js/Image.)
    (-> .-src (set! base64-data))
    (-> .-onload (set! #(->> (.-target %)
                             constrain-image-size
                             (store-image-data field-id filename))))))


(defn on-change-image-dispatcher
  [field-id img-file]
  (file->base64 img-file (partial prepare-image field-id (.-name img-file))))


(defn on-change-text-dispatcher
  [field-id text]
  (update-field-dispatcher field-id {:text (prepos text)
                                     :changed true}))
