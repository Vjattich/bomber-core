(ns bomber.service.formatter-test
  (:require [clojure.test :refer :all]
            [bomber.service.formatter :refer :all]))

(deftest format-number-test []
  (doseq [number ["+79991231122"
                  "79991231122"
                  "89991231122"
                  "7 9991231122"
                  "8 9991231122"
                  "7 999 123 11 22"
                  "+7 999 123 11 22"]]
    (is (= (format-number number :ru :phone-number.format/rfc3966) "tel:+7-999-123-11-22"))
    (is (= (format-number number :ru :phone-number.format/e164) "+79991231122"))
    (is (= (format-number number :ru :phone-number.format/international) "+7 999 123-11-22"))
    (is (= (format-number number :ru :phone-number.format/national) "8 (999) 123-11-22"))
    (is (= (format-number number :ru :simple) "89991231122"))
    (is (= (format-number number :ru :default) "79991231122"))
    (is (= (format-number number :ru nil) "79991231122"))))