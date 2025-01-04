FROM gradle:jdk21-jammy AS build
WORKDIR /app

COPY build.gradle.kts settings.gradle.kts ./
COPY src ./src

RUN gradle clean build --no-daemon
RUN java -Djarmode=layertools -jar build/libs/is-rest-0.0.1-SNAPSHOT.jar extract --destination extracted

FROM eclipse-temurin:21-jre-jammy
ARG JAR_FILE=is-rest-0.0.1-SNAPSHOT.jar
WORKDIR /app

COPY --from=build /app/extracted/dependencies/ ./
COPY --from=build /app/extracted/spring-boot-loader/ ./
COPY --from=build /app/extracted/snapshot-dependencies/ ./
COPY --from=build /app/extracted/application/ ./

ENTRYPOINT exec java org.springframework.boot.loader.launch.JarLauncher ${0} ${@}