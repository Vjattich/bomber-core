(ns bomber.rabbit-connector
  (:require [langohr.core :as rmq]
            [langohr.channel :as lch]
            [langohr.queue :as lq]
            [langohr.consumers :as lc]
            [langohr.basic :as lb]
            [bomber.consumer.bomb-handler :as handler])
  (:gen-class))

(defn init-connect
  [settings]
  (let [conn  (rmq/connect settings)
        ch    (lch/open conn)
        qname "simple-bomb"]
    (lq/declare ch qname {:exclusive false :auto-delete false})
    (lc/subscribe ch qname handler/on-simple-message {:auto-ack true})))

