FROM openjdk:11
RUN mkdir /usr/src/marcaciones
COPY /target/MarcacionES-0.0.1-SNAPSHOT.jar /usr/src/marcaciones
WORKDIR /usr/src/marcaciones
EXPOSE 8071
CMD ["java", "-Djava.security.egd=file:/dev/.urandom", "-Duser.timezone=EC", "-jar", "MarcacionES-0.0.1-SNAPSHOT.jar"]