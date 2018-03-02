(ns mkp.imposter.utils.string
  (:require
    [clojure.string :as str]))


(defn shorten
  [s n]
  (if (> (count s) n)
    (str "..." (str/join (take-last n s)))
    s))


(defn filled?
  [s]
  (and (string? s) (not (str/blank? s))))


(defn prepos
  "Inserts non-breakable space after one-letter prepositions."
  [s]
  (str/replace s #"([ \u00a0]+)([kosuvzia]) " "$1$2\u00a0"))
