FROM openjdk:17-jdk AS build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

COPY src src
RUN chmod +x mvnw
RUN ./mvnw install -DskipTests

FROM openjdk:17-jdk-slim
VOLUME /tmp

COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]