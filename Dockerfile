FROM openjdk
WORKDIR /app
COPY target/*.jar web_jar_kocherga.jar
ENTRYPOINT ["java", "-jar", "web_jar_kocherga.jar"]