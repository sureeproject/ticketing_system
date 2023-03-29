FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . /app
RUN apt-get update && apt-get install -y maven
ENV PATH="/usr/share/maven/bin:${PATH}"
RUN mvn -B dependency:go-offline
CMD ["mvn", "spring-boot:run"]


#docker network create ticketing_system


#docker pull mysql
#docker run --name mysql-container --network ticketing_system -p 3308:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=ticket_system -e MYSQL_USER=suresh -e MYSQL_PASSWORD=suresh -d mysql:latest


#docker build -t ticketing_system:latest .
#docker run --name ticketing_system --network ticketing_system  -p 8080:8080  ticketing_system:latest

#spring.datasource.url = jdbc:mysql://mysql-container:3306/ticket_system [ 3308:3306 port belongs to container here we will use 3306 belongs to contaienr and 3308 exposed to host system]
#spring.datasource.url = jdbc:mysql://localhost:3308/ticket_system

#docker build -t ticketing_system_ui .
#docker run --name ticketing_system_ui --network ticketing_system  -p 4200:4200  ticketing_system_ui:latest