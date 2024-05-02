FROM openjdk:8-jdk-alpine
WORKDIR /iapps-xmlreader-build
RUN addgroup --system appuser && adduser -S -s /iapps-xmlreader-build -G appuser appuser
COPY target/iapps-xmlreader.jar iapps-xmlreader.jar
RUN chown -R appuser:appuser .
USER appuser
ENTRYPOINT ["java", "-jar", "iapps-xmlreader.jar"]
