# define base docker image
FROM openjdk:8
LABEL maintainer="liferay-evp-api"
COPY liferay-pd-backend-0.0.1-SNAPSHOT.jar liferay-pd-backend.jar
ENTRYPOINT ["java", "-jar", "liferay-pd-backend.jar"]