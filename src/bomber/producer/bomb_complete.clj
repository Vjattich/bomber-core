(ns bomber.producer.bomb-complete
  (:require [langohr.basic :as lb]
            [clojure.tools.logging :as log])
  (:gen-class))


(defn on-bomb-complete
  [ch task]
  (log/info "sending" task "to " "bomb-complete")
  (lb/publish ch "bomb-complete" "" task {:content-type "application/json"}))