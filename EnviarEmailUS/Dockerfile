FROM openjdk:11
RUN mkdir /usr/src/enviaremailus
COPY /target/EnviarEmailUS-0.0.1-SNAPSHOT.jar /usr/src/enviaremailus
WORKDIR /usr/src/enviaremailus
EXPOSE 8085
CMD ["java", "-Djava.security.egd=file:/dev/.urandom", "-Duser.timezone=EC", "-jar", "EnviarEmailUS-0.0.1-SNAPSHOT.jar"]