FROM java:8
ADD target/games-0.0.1-SNAPSHOT.jar games-0.0.1-SNAPSHOT.jar
VOLUME /tmp

ENTRYPOINT ["java","-jar","games-0.0.1-SNAPSHOT.jar"]
