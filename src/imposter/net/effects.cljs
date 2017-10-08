(ns imposter.net.effects
  (:require
    [re-frame.core :refer [reg-fx]]))


(reg-fx
  :net/log
  (fn [message]
    (js/console.log message)))
