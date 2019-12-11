# ms-config-server
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*"]
CMD ["-Dserver.port=9999","-Dspring.profiles.active=native","-Dspring.cloud.config.server.native.searchLocations=file:///e://_fsconfig","-Deureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/","org.sj.msconfigserver.MsConfigServerApplication"]
# -Dserver.port=9999 -Dspring.profiles.active=native -Dspring.cloud.config.server.native.searchLocations=file:///e://_fsconfig -Deureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/