FROM eclipse-temurin:17-alpine

WORKDIR /app

COPY ./target/blogapplication-0.0.1-SNAPSHOT.jar /app/blogapplication-0.0.1.jar

ENTRYPOINT ["java","-jar","blogapplication-0.0.1.jar"]

