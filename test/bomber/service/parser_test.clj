(ns bomber.service.parser-test
  (:require [clojure.test :refer :all]
            [bomber.service.parser :refer :all]))


(deftest get-services-test []
  (is
   (= (get-services "test-resources/services.edn")
      [{:url          "https://example.ru"
        :method       :post
        :content-type :json
        :region       :ru
        :async?       true
        :form-params  {:phone :?}}])))