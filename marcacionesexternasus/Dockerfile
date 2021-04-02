FROM openjdk:11
RUN mkdir /usr/src/marcacionesexternasus
COPY /target/MarcacionesExternasUS-0.0.1-SNAPSHOT.jar /usr/src/marcacionesexternasus
WORKDIR /usr/src/marcacionesexternasus
EXPOSE 8080
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Duser.timezone=EC", "-jar", "MarcacionesExternasUS-0.0.1-SNAPSHOT.jar"]
