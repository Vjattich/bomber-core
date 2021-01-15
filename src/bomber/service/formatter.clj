(ns bomber.service.formatter
  (:require [phone-number.core :as phone]
            [clojure.string :as string])
  (:gen-class))

(defn- phone-info
  [phonenumber
   region]
  (let [info               (phone/all-formats phonenumber region)
        default            (string/replace (:phone-number.format/e164 info) #"\+" "")
        simple-format      (string/replace default #"7" "8")]
    (assoc info
           :simple  simple-format
           :default default)))

(defn format-number
  "format number to correct formats, or :standart by default"
  [phonenumber
   region
   format]
  ((or format :default) (phone-info phonenumber region)))