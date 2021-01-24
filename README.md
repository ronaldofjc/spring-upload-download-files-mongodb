# Getting Started

This project performs the actions of Upload and Download of images, regardless of the file format. The generated REST endpoints can be
consumed in any front-end framework, such as React js, Angular or Vue js, etc. Technologies used: Java, Spring Boot, Maven, Docker, MongoDB,
Lombok

### Pre-Requires

- JDK 11
- Maven 3
- Docker ([Install](https://docs.docker.com/v17.09/engine/installation/linux/docker-ce/ubuntu/ "Install")
  | [Configure](https://docs.docker.com/v17.09/engine/installation/linux/linux-postinstall/ "Configure"))

### Pre-Requires Local

- MongoDB
    - Create MongoDB instance with docker compose
        - Directory: `/misc/docker/mongodb/`
        - Command: `docker-compose up -d`
    - To access mongoDB admin interface(mongo-express) access localhost:8086, user dev, password dev!

## Commands

- Compile project

  `mvn compile`

- Clear target directory

  `mvn clean`

- Test project

  `mvn test`

- Start project

  `mvn spring-boot:run`

- Install packages

  `mvn install`

---

## Swagger

- The generated swagger html page is available in the following address
  
  `http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/`
