
Rest Application
==============================

Microservice with a RESTful API using [Domain-Driven Design (DDD)](https://learn.microsoft.com/en-us/dotnet/architecture/microservices/microservice-ddd-cqrs-patterns/ddd-oriented-microservice) principles with Spring Boot and Java enabling scalable architecture and easier maintenance.

<img src="https://uploads-ssl.webflow.com/605b348209108c029fdcae2e/620f703f6fe24c32f5c09c70_Fundamental%20layers%20of%20domain%20driven%20design.jpg" width=60% height=80%>



Having identified the application's main domain, models were created using entities, value objects, aggregates created through mappers and using repositories for data persistence.
The implementation of domain services to encapsulate the business logic that does not naturally fit into a single entity and the transfer of information and decoupling between [DTO (Data Transfer Objects)](https://martinfowler.com/eaaCatalog/dataTransferObject.html) layers makes this architecture proposal more cohesive for a robust and effective evolution of this application .


This project uses SPRINGBOOT, JPA, IN_MEMORY DATABASE (H2 DB), MOCKITO for testing, SWAGGER to generate endpoints documentation and an initial setup for DOCKER.


For a classic execution you can execute by the command line into sm360 of project on folder the command below:

### `java -jar target/sm360-0.0.1-SNAPSHOT.jar`



Docker
==============================

This project already has initial settings needed to create an image to run in a docker container.
For docker mode, you can create an image with the command:

### `docker build -t spring-boot-docker-sm360:spring-docker .`


After you can run using:


### `docker run -p 9070:9070 spring-boot-docker-sm360:spring-docker .`



Automatic Initial Setup
==============================

There is a small setup with a script (schema.sql) that provides some records in the database for quick handling.


* `Keep in mind that the application has a small memory db, so when the application is shutdown, all data is gone.` 


H2 Console
==============================

You can access the db directly by the URL below:

### `http://localhost:9070/h2`

* Driver class -> org.h2.Driver
* JDBC URL -> jdbc:h2:mem:sm360db
* User Name -> sa
* Password -> empty


Endpoints
==============================

This project uses the swagger framework as a standard way of documentation.

The endpoints describe can be access by the URL below:

### `http://localhost:9070/swagger-ui.html`





Possible Future Improvements
==============================

* [Spring Security](https://spring.io/projects/spring-security) To security for authentication and access control
* [Lombok](https://projectlombok.org/) To avoid a repetitive code of getters and setters making a cleaner code easier to understand.
* [ModelMapper](https://modelmapper.org/) Provides a drop-in solution when the source and destination objects are similar to each other.
* [CQRS (Command Query Responsibility Segregation)](https://martinfowler.com/bliki/CQRS.html), and event sourcing, if necessary.





