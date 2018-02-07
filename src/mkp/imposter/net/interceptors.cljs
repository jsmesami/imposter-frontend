(ns mkp.imposter.net.interceptors
  (:require
    [re-frame.core :refer [enrich]]))


(def inc-loading-count (enrich (fn [db] (update-in db [:net :loading-count] inc))))


(def dec-loading-count (enrich (fn [db] (update-in db [:net :loading-count] #(if (pos? %) (dec %) 0)))))
