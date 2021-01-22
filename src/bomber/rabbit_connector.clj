(ns bomber.rabbit-connector
  (:require [langohr.core :as rmq]
            [langohr.channel :as lch]
            [langohr.queue :as lq]
            [langohr.consumers :as lc]
            [langohr.exchange :as lex]
            [bomber.consumer.bomb-handler :as handler])
  (:gen-class))

(defn init-connect
  [services-path rabbit-settings]
  (let [conn   (rmq/connect rabbit-settings)
        ch     (lch/open conn)
        qname  "simple-bomb-queue"
        exname "simple-bomb"
        queue  (.getQueue (lq/declare ch qname {:exclusive false :auto-delete false}))]
    (lex/fanout ch exname {:auto-delete false})
    (lq/bind ch queue exname)
    (lc/subscribe ch qname (handler/on-simple-message services-path) {:auto-ack true})))