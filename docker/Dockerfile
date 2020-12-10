FROM openjdk:8-jre-alpine

ARG JAR_FILE

EXPOSE 8180

VOLUME /tmp

ADD /rss-service.jar /rss-service.jar

RUN sh -c 'touch /rss-service.jar'

CMD ["java", "-jar", "/rss-service.jar"]