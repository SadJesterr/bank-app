# Этап сборки
FROM maven:3.9.9-eclipse-temurin-21 AS build
RUN mkdir /app
COPY . /bank-app
WORKDIR /bank-app
RUN mvn -B clean package -Dmaven.test.skip=true

# Этап запуска
FROM openjdk:21-jdk-slim
WORKDIR /bank-app
COPY --from=build /bank-app/target/*.jar /bank-app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]