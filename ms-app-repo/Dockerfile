FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*"]
#CMD ["-Dspring.profiles.active=development","-Dspring.datasource.url=jdbc:postgresql://172.19.69.95:5434/postgres", "-Dspring.datasource.username=postgres", "-Dspring.datasource.password=nsdl1234","org.sj.msapprepo.MsAppRepoApplication"]
CMD ["-Dserver.port=7171","-Deureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/","-Dspring.profiles.active=development","-Dspring.datasource.url=jdbc:postgresql://172.19.69.95:5434/postgres", "-Dspring.datasource.username=postgres", "-Dspring.datasource.password=nsdl1234","org.sj.msapprepo.MsAppRepoApplication"]