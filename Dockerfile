FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY ./target/*.jar /app/app.jar
RUN useradd -rm -d /home/javauser -s /bin/bash -g root -G sudo -u 1001 javauser
RUN chown -R javauser:root /app
USER javauser
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]



