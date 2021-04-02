FROM openjdk:11
RUN mkdir /usr/src/importarmarcacionesms
COPY /target/ImportarMarcacionesMS-0.0.1-SNAPSHOT.jar /usr/src/importarmarcacionesms
WORKDIR /usr/src/importarmarcacionesms
EXPOSE 8090
CMD ["java", "-Djava.security.egd=file:/dev/.urandom", "-Duser.timezone=EC", "-jar", "ImportarMarcacionesMS-0.0.1-SNAPSHOT.jar"]