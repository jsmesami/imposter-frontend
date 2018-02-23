(ns mkp.imposter.alert.core
  (:require
    [mkp.imposter.alert.events]
    [mkp.imposter.alert.subs]))


(defn status->kind
  [status]
  (cond
    (>= status 500) :server-error
    (>= status 400) :client-error
    (>= status 300) :redirect
    (>= status 200) :success
    :else :info))


(defn kind->severity
  [kind]
  (case kind
    :server-error :error
    :client-error :error
    :redirect :info
    :success :success
    :info :info))
