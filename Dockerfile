FROM openjdk:8
COPY ./target/case-study-0.0.1-SNAPSHOT.jar case-study.jar

ENTRYPOINT ["java", "-jar", "case-study.jar"]