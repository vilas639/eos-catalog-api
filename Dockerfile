FROM openjdk:17-jdk-slim
MAINTAINER vilasjdhv639 "vilasjdhv639@gmail.com"
ADD target/catalog-0.0.1-RELEASE.jar eos-catalog-api.jar
CMD ["java","-jar","eos-catalog-api.jar"]
