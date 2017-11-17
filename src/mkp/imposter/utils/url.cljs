(ns mkp.imposter.utils.url
  (:require
    [cemerick.url :refer [url-encode]]))


(defn m->qs
  "Convert a map into a query string: {:n 1, :s \"awesome stuff\" :x nil} => ?n=1&s=awesome%20stuff"
  [poster-filter]
  (->> poster-filter
       (reduce-kv (fn [result k v]
                    (if (nil? v)
                      result
                      (conj result (str (name k) "=" (url-encode v)))))
                  [])
       (interpose "&")
       (apply str "?")))
