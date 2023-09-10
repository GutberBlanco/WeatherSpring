FROM openjdk:20
COPY target/apiconn-0.0.1.jar app.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/app.jar"]

