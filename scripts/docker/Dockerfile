FROM openjdk:8-jre-alpine
MAINTAINER no_f

ENV PARAMS=""

ADD bugly-boot-*.jar /app.jar

ENTRYPOINT ["sh","-c","java -Xmx256m -Xms100m -jar /app.jar $PARAMS"]