(ns mkp.imposter.utils.events
  (:require
    [re-frame.core :refer [dispatch]]
    [goog.events.KeyCodes :refer [ESC]]))


(defn esc?
  [e]
  (= (.-keyCode e) ESC))


(defn click-dispatcher
  [event]
  (fn [e] (do (dispatch event)
              (.preventDefault e)
              false)))
