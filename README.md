# Banking System API
<div align="center">
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" width="128" />
</div>


## Features

### Database tables
- [x] Customer information
- [x] Transaction information
- 
## Requirements

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Spring](https://spring.io/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## Running the application

### Docker

```bash
docker-compose up
```

### Spring boot application

```bash
$ mvn spring-boot:run or ./mvnw spring-boot:run
$ mvn clean package -DskipTests or ./mvnw clean package -DskipTests 
$ java -jar JAR_FILE_NAME.jar
```


## Application.properties example

```yml
spring:
    datasource:
        url: jdbc:postgresql://localhost:5432/db_name
        username: postgres
        password: postgrespw
        driver-class-name: org.postgresql.Driver
    jpa:
        hibernate:
        ddl-auto: update
        show-sql: true
        database-platform: org.hibernate.dialect.PostgreSQLDialect
 ```

## Postman API testing



[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/1b1b1b1b1b1b1b1b1b1b)

