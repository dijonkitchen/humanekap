FROM openjdk:8-alpine

COPY target/uberjar/humanekap.jar /humanekap/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/humanekap/app.jar"]
