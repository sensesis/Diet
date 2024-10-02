# 단계 1: 빌드 단계
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

# Gradle Wrapper 스크립트 및 설정 파일 복사
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# 소스 코드 복사
COPY src ./src

# Gradle Wrapper 실행 권한 부여
RUN chmod +x gradlew

# 애플리케이션 빌드 (테스트 스킵)
RUN ./gradlew clean build -x test --no-daemon

# 단계 2: 실행 단계
FROM eclipse-temurin:17-jdk

WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 포트 개방
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
