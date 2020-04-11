FROM openjdk:8
ADD target/spring-boot-azure.jar spring-boot-azure.jar
EXPOSE 80
ENTRYPOINT ["java" ,"-jar", "spring-boot-azure.jar"]