(ns bildigo.date-test
  (:require
   [clojure.test :refer [deftest is]]
   [bildigo.date :refer [iso-8601-format]])
  (:import [java.util Date]))

(deftest format-test
  (is (= "2023-07-21T20:02:42.000+02:00" (iso-8601-format (Date. 1689962562000)))))