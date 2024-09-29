FROM openjdk:17-jdk
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} mju-festival.jar

ENTRYPOINT ["java", "-jar", "mju-festival.jar"]