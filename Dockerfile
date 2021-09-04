FROM openjdk:11.0.12-jdk-slim

ADD target/forobackend-mysql.jar forobackend-mysql.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "forobackend-mysql.jar"]