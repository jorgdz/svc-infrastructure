FROM openjdk:11
RUN mkdir /usr/src/serverconfigus
COPY /target/ServerConfigUS-0.0.1-SNAPSHOT.jar /usr/src/serverconfigus
WORKDIR /usr/src/serverconfigus
EXPOSE 8080
CMD ["java", "-Djava.security.egd=file:/dev/.urandom", "-Duser.timezone=EC", "-jar", "ServerConfigUS-0.0.1-SNAPSHOT.jar"]