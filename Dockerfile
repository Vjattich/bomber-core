FROM bellsoft/liberica-openjdk-alpine:11.0.9-12
WORKDIR /
COPY target/bomber-core-0.1.0-SNAPSHOT-standalone.jar bomber-core-0.1.0-SNAPSHOT-standalone.jar
EXPOSE 3333
CMD java -jar bomber-core-0.1.0-SNAPSHOT-standalone.jar C: 172.18.0.1