# 빌드 단계 (builder)
FROM openjdk:17-jdk-slim AS builder

WORKDIR /app
COPY . /app

# --- feed-service 빌드 ---
WORKDIR /app/feed-service
RUN chmod +x ./mvnw || { echo "ERROR: mvnw script not found or permission denied in /app/feed-service"; exit 1; }
RUN ./mvnw dependency:go-offline -B
RUN ./mvnw package -DskipTests
ARG FEED_JAR_FILE_NAME=target/*.jar
RUN cp ${FEED_JAR_FILE_NAME} /app/feed-service-bundle.jar || { echo "Feed Service JAR not found in target. Check build logs."; exit 1;}


# --- mypage-service 빌드 ---
WORKDIR /app/mypage-service
RUN chmod +x ./mvnw || { echo "ERROR: mvnw script not found or permission denied in /app/mypage-service"; exit 1; }
RUN ./mvnw dependency:go-offline -B
RUN ./mvnw package -DskipTests
ARG MYPAGE_JAR_FILE_NAME=target/*.jar
RUN cp ${MYPAGE_JAR_FILE_NAME} /app/mypage-service-bundle.jar || { echo "Mypage Service JAR not found in target. Check build logs."; exit 1;}


# 실행 단계 (runtime)
FROM openjdk:17-slim

# 사용자 생성은 ROOT 권한으로 실행되어야 합니다.
RUN useradd --system --uid 1000 spring

# entrypoint.sh 복사 및 실행 권한 부여는 ROOT 권한으로 실행되어야 합니다.
COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh # <-- 이 부분이 USER spring 위로 올라왔습니다.

# 이제부터 spring 사용자로 전환합니다.
USER spring 

VOLUME /tmp
EXPOSE 8084
EXPOSE 8085
COPY --from=builder /app/feed-service-bundle.jar /app/feed-service.jar
COPY --from=builder /app/mypage-service-bundle.jar /app/mypage-service.jar

ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
ENV SPRING_PROFILES_ACTIVE=production