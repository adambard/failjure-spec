(ns failjure.test-spec
  (:require [failjure.core :as f]
            [clojure.test :as t]
            [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest])
  )


(t/deftest test-fail
  (t/testing "Check fail"
    (stest/instrument `f/fail)
    (stest/check `f/fail))
  )
