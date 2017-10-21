(ns mkp.imposter.home.db
  (:require
    [cemerick.url :refer [url-encode]]))


(def posters-per-page 12)


(def PosterFilterInitial
  {:limit posters-per-page
   :offset 0
   :datetime-from nil
   :datetime-to nil
   :bureau nil
   :template nil
   :fulltext nil})


(def HomeViewInitial
  {:posters {:filter PosterFilterInitial
             :count 0
             :list []}})


(defn filter->query-string
  "Convert a dict into a query string: {:n 1, :s \"awesome stuff\" :x nil} => ?n=1&s=awesome%20stuff"
  [poster-filter]
  (->> poster-filter
       (reduce-kv (fn [result k v]
                    (if (nil? v)
                      result
                      (conj result (str (name k) "=" (url-encode v)))))
                  [])
       (interpose "&")
       (apply str "?")))
