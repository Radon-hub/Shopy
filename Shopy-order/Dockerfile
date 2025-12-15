# syntax=docker/dockerfile:1

################################################################################
# Stage 1: Download Gradle Dependencies
################################################################################
FROM eclipse-temurin:21-jdk-jammy as deps

WORKDIR /build

# Copy Gradle wrapper and config
COPY --chmod=0755 gradlew gradlew
COPY gradle/ gradle/
COPY build.gradle settings.gradle ./

# Pre-fetch dependencies using Gradle cache
RUN --mount=type=cache,target=/root/.gradle \
    ./gradlew dependencies --no-daemon

################################################################################
# Stage 2: Build the Application (bootJar)
################################################################################
FROM deps as package

WORKDIR /build

# Copy source code
COPY src src/

# Build the Spring Boot jar
RUN --mount=type=cache,target=/root/.gradle \
    ./gradlew clean bootJar --no-daemon

# Move final jar to a known path
RUN cp build/libs/*.jar build/libs/app.jar

################################################################################
# Stage 3: Extract Spring Boot Layers
################################################################################
FROM package as extract

WORKDIR /build

RUN java -Djarmode=layertools -jar build/libs/app.jar extract --destination build/extracted

################################################################################
# Stage 4: Final Runtime Image
################################################################################
FROM eclipse-temurin:21-jdk-jammy AS final

ARG UID=10001

# Create non-privileged user
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser

USER appuser

WORKDIR /app

# Copy Spring Boot layers
COPY --from=extract /build/build/extracted/dependencies/ ./
COPY --from=extract /build/build/extracted/snapshot-dependencies/ ./
COPY --from=extract /build/build/extracted/spring-boot-loader/ ./
COPY --from=extract /build/build/extracted/application/ ./

EXPOSE 8090 2030

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
