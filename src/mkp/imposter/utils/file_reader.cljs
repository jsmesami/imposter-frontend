(ns mkp.imposter.utils.file-reader)


(defn file->base64
  [file callback]
  (let [reader (js/FileReader.)]
    (set! (.-onload reader) #(callback (-> % .-target .-result)))
    (.readAsDataURL reader file)))
