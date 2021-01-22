(ns bomber.core
  (:require [bomber.rabbit-connector :as connector]
            [bomber.consumer.bomb-handler :as handler])
  (:gen-class))

(defn -main
  ([services-path]
   (->
    (connector/init-connect)))
  ([services-path rabbit-host]
   (->
    (connector/init-connect services-path {:host rabbit-host}))))