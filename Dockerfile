FROM openjdk:14-alpine
RUN mkdir app
WORKDIR /app
COPY /target/achat-1.0.0-SNAPSHOT.jar achat-1.0.0-SNAPSHOT.jar  
EXPOSE 8089
RUN chmod 777 achat-1.0.0-SNAPSHOT.jar 
CMD ["java","-jar","achat-1.0.0-SNAPSHOT.jar"]