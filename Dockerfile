FROM eclipse-temurin:21
LABEL manteiner="júlio"
WORKDIR /apirest
COPY target/Biblioteca-0.0.1-SNAPSHOT.jar /apirest/biblioteca.jar
ENTRYPOINT ["java", "-jar", "biblioteca.jar"]

