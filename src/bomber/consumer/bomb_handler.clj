(ns bomber.consumer.bomb-handler
  (:require [bomber.service.parser :as parser]
            [bomber.executor :as executor]
            [bomber.service.formatter :as formatter]
            [bomber.producer.bomb-complete :as producer]
            [clojure.data.json :as json])
  (:use clojure.tools.logging)
  (:gen-class))

(defn- read-task
  [bytes]
  (json/read-str (String. bytes "UTF-8") :key-fn keyword :eof-error? true :eof-value nil))

(defn- get-numbers
  [task]
  (map :number (:phonenumbers task)))

(defn on-simple-message
  "Recieve a path to services file, return a function that use parsed services"
  [services-path]
  (let [services (parser/get-services services-path)]
    (fn [ch {:keys [routing-key] :as meta}, ^bytes payload]
      (let [task    (read-task payload)
            numbers (get-numbers task)]
        (info "Recieved task " task)
        (doseq [number numbers]
          (info "bomb for " number)
          (executor/do-bomb services number))
        (producer/on-bomb-complete ch (json/write-str (assoc task :status "DONE")))))))