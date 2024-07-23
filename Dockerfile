FROM maven as BUILDER
ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src/

#RUN mvn clean package
RUN mvn clean compile generate-sources package -Dmaven.test.skip=true
RUN cp target/rabbit-mq-test-${VERSION}.jar target/application.jar

FROM openjdk
WORKDIR /app/

# Expose port 80 to the outside world
EXPOSE 80

COPY --from=BUILDER /build/target/application.jar /app/
CMD java -jar /app/application.jar 