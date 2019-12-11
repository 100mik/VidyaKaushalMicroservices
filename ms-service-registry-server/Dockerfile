# ms-service-registry
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*"]
CMD ["-Dspring.profiles.active=server1","-Deureka.client.serviceUrl.defaultZone=http://localhost:8762/eureka","org.sj.msserviceregistryserver.MsServiceRegistryServerApplication"]
# -Dspring.profiles.active=server1 -Deureka.client.serviceUrl.defaultZone=http://localhost:8762/eureka org.sj.msserviceregistryserver.MsServiceRegistryServerApplication
# -Dspring.profiles.active=server2 -Deureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka org.sj.msserviceregistryserver.MsServiceRegistryServerApplication
# docker run -p 8761:8761 org.sj/ms-service-registry-server:latest --entrypoint "java -cp app:app/lib/* -Dspring.profiles.active=server1 -Deureka.client.serviceUrl.defaultZone=http://localhost:8762/eureka org.sj.msserviceregistryserver.MsServiceRegistryServerApplication"