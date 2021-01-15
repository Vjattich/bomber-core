(ns bomber.consumer.bomb-handler
  (:require [bomber.service.parser :as parser]
            [bomber.executor :as executor]
            [bomber.service.formatter :as formatter]
            [clojure.data.json :as json])
  (:gen-class))

(def services (parser/get-services))

(defn on-simple-message
  [ch {:keys [routing-key] :as meta} ^bytes payload]
  (let [numbers (json/read-json (String. payload "UTF-8"))]
    (doseq [number numbers]
      (println numbers)
      (executor/do-bomb services number))))