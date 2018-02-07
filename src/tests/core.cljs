(ns tests.core
  (:require
    [doo.runner :refer-macros [doo-tests]]
    [tests.test-alert]
    [tests.test-utils]))


(doo-tests
  'tests.test-alert
  'tests.test-utils)
