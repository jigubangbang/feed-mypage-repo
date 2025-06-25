# 빌드 단계 (builder)
FROM openjdk:17-jdk-slim AS builder

# 1. 초기 작업 디렉토리 설정 및 전체 소스 복사
# /app 디렉토리를 만들고, Git 리포지토리의 모든 내용 (각 서비스 폴더 포함)을 /app으로 복사합니다.
# 이렇게 하면 feed-service와 mypage-service 폴더가 /app 아래에 있게 됩니다.
WORKDIR /app
COPY . /app

# 2. --- feed-service 빌드 ---
# feed-service 디렉토리로 이동합니다.
WORKDIR /app/feed-service
# 해당 서비스 디렉토리 내에 있는 mvnw에 실행 권한을 부여합니다.
RUN chmod +x ./mvnw || { echo "ERROR: mvnw script not found or permission denied in /app/feed-service"; exit 1; }
# 해당 서비스 디렉토리 내에 있는 mvnw를 사용하여 빌드를 수행합니다.
RUN ./mvnw dependency:go-offline -B
RUN ./mvnw package -DskipTests

# 빌드된 JAR 파일을 /app 디렉토리(빌더 스테이지의 루트)로 복사하고 이름을 명확히 합니다.
# 실제 JAR 파일 이름 패턴에 맞게 조정하세요.
ARG FEED_JAR_FILE_NAME=target/*.jar
RUN cp ${FEED_JAR_FILE_NAME} /app/feed-service-bundle.jar || { echo "Feed Service JAR not found in target. Check build logs."; exit 1;}


# 3. --- mypage-service 빌드 ---
# mypage-service 디렉토리로 이동합니다.
WORKDIR /app/mypage-service
# 해당 서비스 디렉토리 내에 있는 mvnw에 실행 권한을 부여합니다.
RUN chmod +x ./mvnw || { echo "ERROR: mvnw script not found or permission denied in /app/mypage-service"; exit 1; }
# 해당 서비스 디렉토리 내에 있는 mvnw를 사용하여 빌드를 수행합니다.
RUN ./mvnw dependency:go-offline -B
RUN ./mvnw package -DskipTests

# 빌드된 JAR 파일을 /app 디렉토리(빌더 스테이지의 루트)로 복사하고 이름을 명확히 합니다.
ARG MYPAGE_JAR_FILE_NAME=target/*.jar
RUN cp ${MYPAGE_JAR_FILE_NAME} /app/mypage-service-bundle.jar || { echo "Mypage Service JAR not found in target. Check build logs."; exit 1;}


# 실행 단계 (runtime)
FROM openjdk:17-slim
RUN useradd --system --uid 1000 spring
USER spring
VOLUME /tmp
EXPOSE 8084
EXPOSE 8085
COPY --from=builder /app/feed-service-bundle.jar /app/feed-service.jar
COPY --from=builder /app/mypage-service-bundle.jar /app/mypage-service.jar
COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh
ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
ENV SPRING_PROFILES_ACTIVE=production