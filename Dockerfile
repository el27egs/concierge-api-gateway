FROM openjdk:8-jdk-oraclelinux7
WORKDIR /app
RUN useradd -rm -d /home/concierge -s /bin/bash -g root -G users -u 1001 concierge
RUN chown -R concierge:root /app
USER concierge
COPY ./target/*.jar /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]



