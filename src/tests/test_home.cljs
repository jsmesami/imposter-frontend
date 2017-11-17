(ns tests.test-home
  (:require
    [cljs.test :refer-macros [deftest testing is]]
    [mkp.imposter.utils.url :refer [m->qs]]))


(deftest test-filter->qs
  (testing "filter dict -> query string generation"
    (let [f {:a 1, "b" 2, :c "žluťoučký kůň"}]
      (is (= "?a=1&b=2&c=%C5%BElu%C5%A5ou%C4%8Dk%C3%BD%20k%C5%AF%C5%88" (m->qs f))))))
