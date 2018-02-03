(ns tests.core
  (:require
    [doo.runner :refer-macros [doo-tests]]
    [tests.test-alert]
    [tests.test-home]
    [tests.test-net]))


(doo-tests
  'tests.test-alert
  'tests.test-net
  'tests.test-home)
