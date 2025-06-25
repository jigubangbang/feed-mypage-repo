# 빌드 단계 (builder)
FROM openjdk:17-jdk-slim AS builder
WORKDIR /app
COPY . /app
RUN chmod +x mvnw

# --- feed-service 빌드 ---
WORKDIR /app/feed-service
RUN ../mvnw dependency:go-offline -B
RUN ../mvnw package -DskipTests
ARG FEED_JAR_FILE_NAME=target/*.jar
RUN cp ${FEED_JAR_FILE_NAME} /app/feed-service.jar || { echo "Feed Service JAR not found"; exit 1;}

# --- mypage-service 빌드 ---
WORKDIR /app/mypage-service
RUN ../mvnw dependency:go-offline -B
RUN ../mvnw package -DskipTests
ARG MYPAGE_JAR_FILE_NAME=target/*.jar
RUN cp ${MYPAGE_JAR_FILE_NAME} /app/mypage-service.jar || { echo "Mypage Service JAR not found"; exit 1;}


# 실행 단계 (runtime)
FROM openjdk:17-slim
RUN useradd --system --uid 1000 spring
USER spring
VOLUME /tmp
EXPOSE 8084
EXPOSE 8085
COPY --from=builder /app/feed-service.jar /app/feed-service.jar
COPY --from=builder /app/mypage-service.jar /app/mypage-service.jar
COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh
ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
ENV SPRING_PROFILES_ACTIVE=production