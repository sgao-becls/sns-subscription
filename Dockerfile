FROM adoptopenjdk/openjdk11

WORKDIR /app
COPY ./target/sns-subscription.jar .
RUN pwd
RUN ls

ENTRYPOINT java -jar sns-subscription.jar
