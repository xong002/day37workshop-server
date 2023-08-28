#
# First stage
#

FROM maven:3.8.3-openjdk-17 AS build

COPY src /home/app/src
COPY pom.xml /home/app

ARG RAILWAYSQL_URL
ARG RAILWAYSQL_USER
ARG RAILWAYSQL_PASSWORD

RUN mvn -f /home/app/pom.xml clean package

#
# second stage
#

FROM openjdk:17-oracle

ARG RAILWAYSQL_URL
ARG RAILWAYSQL_USER
ARG RAILWAYSQL_PASSWORD

COPY --from=build /home/app/target/day37workshop-0.0.1-SNAPSHOT.jar /usr/local/lib/day37workshop.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/day37workshop.jar"]