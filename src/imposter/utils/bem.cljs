(ns utils.bem
  (:require
    [clojure.string :refer [blank? join]]))


(defn be
  [block element]
  (str (name block) "__" (name element)))


(defn bm
  [block modifiers]
  (let [block-name (name block)]
    (->> modifiers
         (remove blank?)
         (map name)
         (map (partial str block-name "--"))
         (into [block-name])
         (join " "))))


(defn bem
  [block element modifiers]
  (bm (be block element) modifiers))
