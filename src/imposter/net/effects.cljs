(ns imposter.net.effects
  (:require
    [re-frame.core :refer [reg-fx]]))


(reg-fx
  :net/log-error
  (fn [message]
    (js/console.log message)))
