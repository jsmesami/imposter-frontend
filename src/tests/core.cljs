(ns tests.core
  (:require
    [doo.runner :refer-macros [doo-tests]]
    [tests.test-flash]
    [tests.test-home]
    [tests.test-net]))


(doo-tests
  'tests.test-flash
  'tests.test-net
  'tests.test-home)
