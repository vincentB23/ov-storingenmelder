FROM openjdk:11-jdk
VOLUME /tmp
EXPOSE 8080
ADD build/libs/storingenmelder-0.0.1-SNAPSHOT.jar storingenmelder.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod" ,"-jar", "/storingenmelder.jar"]