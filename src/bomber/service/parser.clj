(ns bomber.service.parser
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn])
  (:gen-class))

(defn- add-async
  "Add async keyword to requests"
  [services]
  (vec (map (fn [x] (assoc x :async? true)) services)))

(defn get-services
  "parse resources for usage"
  ([]
   (add-async
    (edn/read-string
     (slurp
      (io/resource "services.edn")))))
  ([filepath]
   (add-async
    (edn/read-string
     (slurp
      (io/file filepath))))))