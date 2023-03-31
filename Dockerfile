FROM openjdk:14-alpine
RUN mkdir app
WORKDIR /app
COPY /target/tpAchatProcjet-1.0.0-SNAPSHOT.jar tpAchatProcjet-1.0.0-SNAPSHOT.jar  
EXPOSE 8089
RUN chmod 777 tpAchatProcjet-1.0.0-SNAPSHOT.jar 
CMD ["java","-jar","tpAchatProcjet-1.0.0-SNAPSHOT.jar"]