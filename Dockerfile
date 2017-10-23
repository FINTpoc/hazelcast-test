FROM openjdk:8-jre-alpine
ADD build/libs/hazelcast-test-*.jar /data/app.jar
CMD ["java", "-jar", "/data/app.jar"]