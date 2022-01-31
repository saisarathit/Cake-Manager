FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cake-manager.jar
ENTRYPOINT ["java","-jar","/cake-manager.jar"]