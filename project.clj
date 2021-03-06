(defproject bomber-core "0.1.0-SNAPSHOT"
  :description "This is sms bomber on clojure lang. It made just for educational reasons"
  :license
  {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
   :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.json "1.0.0"]
                 [org.clojure/tools.logging "1.1.0"]
                 [clj-http "3.10.3"]
                 [cheshire "5.6.1"]
                 [io.randomseed/phone-number "8.12.4-2"]
                 [com.novemberain/langohr "5.2.0" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/slf4j-api "1.7.30"]
                 [org.slf4j/slf4j-simple "1.7.30"]]
  :repl-options {:init-ns bomber.core}
  :source-paths ["src"]
  :main bomber.core
  :profiles {:uberjar {:aot :all}})
