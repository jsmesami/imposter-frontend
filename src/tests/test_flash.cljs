(ns tests.test-flash
  (:require
    [cljs.test :refer-macros [deftest is]]
    [day8.re-frame.test :as rf-test]
    [re-frame.core :refer [dispatch subscribe]]
    [app.effects]
    [flash.events]
    [flash.subs]))


(deftest test-flash
  (rf-test/run-test-sync
    (let [messages (subscribe [:flash/messages])]
      (is (= 0 (count @messages)))

      (dispatch [:flash/add-message :success "success"])
      (is (= 1 (count @messages)))

      (dispatch [:flash/add-message :error "error"])
      (is (= 2 (count @messages)))

      (let [id1 (-> @messages keys (nth 0))
            id2 (-> @messages keys (nth 1))]
        (is (= :success (get-in @messages [id1 :severity])))
        (is (= "success" (get-in @messages [id1 :text])))
        (is (= :error (get-in @messages [id2 :severity])))
        (is (= "error" (get-in @messages [id2 :text])))

        (dispatch [:flash/remove-message id1])
        (is (= 1 (count @messages)))
        (is (= :error (get-in @messages [id2 :severity])))
        (is (= "error" (get-in @messages [id2 :text])))

        (dispatch [:flash/remove-message id2])
        (is (= 0 (count @messages)))))))
