(ns bomber.consumer.bomb-handler
  (:require [bomber.service.parser :as parser]
            [bomber.executor :as executor]
            [bomber.service.formatter :as formatter]
            [bomber.producer.bomb-complete :as producer]
            [clojure.data.json :as json])
  (:use clojure.tools.logging)
  (:gen-class))

(defn on-simple-message
  [services-path]
  (let [services (parser/get-services services-path)]
    (fn [ch {:keys [routing-key] :as meta}, ^bytes payload]
      (let [task    (json/read-str (String. payload "UTF-8"))
            numbers (map :number (:phonenumbers task))]
        (info "Recieved task " task)
        (doseq [number numbers]
          (info "bomb for " number)
          (executor/do-bomb services number))
        (producer/on-bomb-complete ch (json/write-str (assoc task :status "DONE")))))))