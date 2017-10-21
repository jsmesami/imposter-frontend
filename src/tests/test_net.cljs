(ns tests.test-net
  (:require
    [cljs.test :refer-macros [deftest is]]
    [day8.re-frame.test :as rf-test]
    [re-frame.core :refer [dispatch subscribe reg-sub]]
    [flash.subs]
    [net.events]))


(reg-sub
  :net/db
  (fn [db _]
    db))


(deftest test-flash
  (rf-test/run-test-sync
    (let [db (subscribe [:net/db])
          save-path [:test :path]
          response [1 2 3]
          translate (fn [r] {:response r})
          messages (subscribe [:flash/messages])]

      (dispatch [:net/success save-path translate nil response])
      (is (= response (:response (get-in @db save-path))))

      (dispatch [:net/failure "error" {}])
      (let [msg-id (-> @messages keys first)]
        (is (= :error (get-in @messages [msg-id :severity])))
        (is (= "error" (get-in @messages [msg-id :text])))))))
