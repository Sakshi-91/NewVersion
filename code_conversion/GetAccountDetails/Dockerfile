# Stage 1: Build Stage
#FROM eclipse-temurin:8-jdk-jammy as BUILD
FROM sudhirkr92/java17-soap-ui-base
WORKDIR /opt/app
COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
COPY pom.xml ./
#RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN mvn clean package
#FROM eclipse-temurin:8-jre-jammy
WORKDIR /opt/app
EXPOSE 8088 8085
# COPY /opt/app/target/*.jar /opt/app/*.jar
# Start SOAP UI MockService and your application
# COPY ./soapui/*.tar.gz /opt
# RUN cd /opt && tar -xvf /opt/*.tar.gz && rm /opt/*.tar.gz
COPY ./soapui/AccountDetails.xml /opt/app/AccountDetails.xml
CMD /opt/SoapUI-5.2.0/bin/mockservicerunner.sh -p 8088 /opt/app/AccountDetails.xml & java -jar /opt/app/target/*.jar
# ENTRYPOINT ["java", "-jar", "/opt/app/*.jar" ]
