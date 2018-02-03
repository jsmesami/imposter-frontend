(ns tests.test-alert
  (:require
    [cljs.test :refer-macros [deftest testing is are]]
    [day8.re-frame.test :as rf-test]
    [re-frame.core :refer [dispatch subscribe]]
    [mkp.imposter.alert.events]
    [mkp.imposter.alert.subs]
    [mkp.imposter.app.effects]))


(deftest test-alert
  (testing "alert messages"
    (rf-test/run-test-sync

      (let [messages (subscribe [:alert/messages])]
        (is (zero? (count @messages)))

        (testing "adding (different kinds of) messages"
          (dispatch [:alert/add-message :success "success"])
          (is (= 1 (count @messages)))

          (dispatch [:alert/add-message :error "error"])
          (is (= 2 (count @messages))))

        (let [id1 (-> @messages keys (nth 0))
              id2 (-> @messages keys (nth 1))]

          (testing "messages content"
            (are [a b] (= a b)
              :success (get-in @messages [id1 :severity])
              "success" (get-in @messages [id1 :text])
              :error (get-in @messages [id2 :severity])
              "error" (get-in @messages [id2 :text])))

          (testing "removing messages"
            (dispatch [:alert/remove-message id1])
            (are [a b] (= a b)
              1 (count @messages)
              :error (get-in @messages [id2 :severity])
              "error" (get-in @messages [id2 :text]))

            (dispatch [:alert/remove-message id2])
            (is (zero? (count @messages)))))))))
