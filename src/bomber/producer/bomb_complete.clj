(ns bomber.producer.bomb-complete
  (:require [langohr.basic :as lb])
  (:use clojure.tools.logging)
  (:gen-class))


(defn on-bomb-complete
  [ch task]
  (info "sending" task "to " "bomb-complete")
  (lb/publish ch "bomb-complete" "" task {:content-type "application/json"}))