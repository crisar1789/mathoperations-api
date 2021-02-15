FROM java:8
EXPOSE 9095
ADD ./target/mathoperations-api-1.0.0.jar mathoperations-api.jar
ENTRYPOINT ["java", "-jar", "mathoperations-api.jar"]