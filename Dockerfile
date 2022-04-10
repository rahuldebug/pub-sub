FROM openjdk:17.0.1-jdk
RUN mkdir -p /tmp/heapdumps /app/libs /app/build

WORKDIR /app
#copy specific jar
COPY ./target/pub-sub-*.jar  ./build/pub-sub.jar
COPY ./run.sh .

