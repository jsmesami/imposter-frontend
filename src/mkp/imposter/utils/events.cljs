(ns mkp.imposter.utils.events
  (:require [goog.events.KeyCodes :refer [ESC]]))


(defn esc?
  [e]
  (= (.-keyCode e) ESC))
