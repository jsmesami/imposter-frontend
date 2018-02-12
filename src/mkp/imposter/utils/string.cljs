(ns mkp.imposter.utils.string)


(defn shorten
  [s n]
  (if (> (count s) n)
    (str "..." (clojure.string/join (take-last n s)))
    s))
