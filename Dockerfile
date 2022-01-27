FROM openjdk:11
COPY target/datastore-*.jar /home/dev/app.jar
CMD ["java","-jar","/home/dev/app.jar"]