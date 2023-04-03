FROM maven:3.8.3-openjdk-17
RUN mkdir /app
WORKDIR /app
COPY . /app
RUN mvn package
ENTRYPOINT ["java","-jar","target/api-0.0.1-SNAPSHOT.jar"]