(ns tests.test-net
  (:require
    [cljs.test :refer-macros [deftest testing is are]]
    [day8.re-frame.test :as rf-test]
    [re-frame.core :refer [dispatch subscribe reg-sub]]
    [mkp.imposter.flash.subs]
    [mkp.imposter.net.events]))


(reg-sub
  :net/db
  (fn [db _]
    db))


(deftest test-net
  (testing "resources fetching"
    (rf-test/run-test-sync

      (let [db (subscribe [:net/db])
            save-path [:test :path]
            response [1 2 3]
            transform (fn [r] {:response r})
            messages (subscribe [:flash/messages])]

        (testing "successful response saving and transformation"
          (dispatch [:net/success save-path transform nil response])
          (is (= response (:response (get-in @db save-path)))))

        (testing "failed request behaviour"
          (dispatch [:net/failure "error"])
          (let [msg-id (-> @messages keys first)]
            (are [a b] (= a b)
              :error (get-in @messages [msg-id :severity])
              "error" (get-in @messages [msg-id :text]))))))))
