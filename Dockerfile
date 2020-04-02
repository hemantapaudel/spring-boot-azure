FROM openjdk:8
ADD target/spring-boot-jpa-docker.jar spring-boot-jpa-docker.jar
EXPOSE 8082
ENTRYPOINT ["java" ,"-jar", "spring-boot-jpa-docker.jar"]