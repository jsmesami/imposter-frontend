(ns tests.core
  (:require
    [doo.runner :refer-macros [doo-tests]]
    [tests.test-flash]))


(doo-tests
  'tests.test-flash)
