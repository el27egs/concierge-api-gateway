FROM openjdk:8-jdk-oraclelinux7
WORKDIR /app
RUN useradd -rm -d /home/javauser -s /bin/bash -g root -G users -u 1001 javauser
RUN chown -R javauser:root /app
USER javauser
COPY ./target/*.jar /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]



