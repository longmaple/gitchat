FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/authenticationservice
ARG JAR_FILE
ADD ${JAR_FILE} /usr/local/authenticationservice/
ARG RUN_SH_FILE
ADD ${RUN_SH_FILE} run.sh
RUN chmod +x run.sh
CMD ./run.sh
