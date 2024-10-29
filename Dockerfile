FROM openjdk:21-jdk-slim AS app

WORKDIR /app

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
COPY .env .env
#RUN CHMOD +x mvnw # just to be sure
RUN ./mvnw package -Duser.country=US -Duser.language=en

FROM openjdk:21-jdk-slim

ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser

COPY --from=app /app/target/*-exec.jar springcleanarch.jar

EXPOSE $8079

ENTRYPOINT ["java", "-jar", "springcleanarch.jar"]
