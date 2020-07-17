FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/games-0.0.1-SNAPSHOT.jar games-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","games-0.0.1-SNAPSHOT.jar"]
