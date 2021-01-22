FROM clojure
RUN mkdir -p /usr/src/bomber-core
WORKDIR /usr/src//bomber-core
COPY project.clj /usr/src/bomber-core/
RUN lein deps
COPY . /usr/src/bomber-core
RUN mv "$(lein uberjar | sed -n 's/^Created \(.*standalone\.jar\)/\1/p')" bomber-core-standalone.jar
CMD ["sh", "-c", "java -jar bomber-core-standalone.jar ${services_path} ${rabbit_host}"]