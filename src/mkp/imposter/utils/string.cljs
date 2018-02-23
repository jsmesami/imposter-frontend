(ns mkp.imposter.utils.string
  (:require
    [clojure.string :refer [blank?]]))


(defn shorten
  [s n]
  (if (> (count s) n)
    (str "..." (clojure.string/join (take-last n s)))
    s))


(defn filled?
  [s]
  (and (string? s) (not (blank? s))))
