#Base Image
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="arijitnath92@gmail.com"

#Port to expose
EXPOSE 3069

#Env varibale for Working Directory
ENV LOGIN_APP_HOME /usr/src/app

#Locate and Create JAR path
ARG LOGIN_APPLICATION_JAR=target/loginapplication*.jar

#Copy jar to container app directory
COPY $LOGIN_APPLICATION_JAR $LOGIN_APP_HOME/loginapplication.jar

#Set Working Directory
WORKDIR $LOGIN_APP_HOME

#Start Command
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","loginapplication.jar"]