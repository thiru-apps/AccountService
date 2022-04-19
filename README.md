# Spring Boot "Microservice" Example Project

This is a Account Maintanence Service Java / Maven / Spring Boot (version 2.6.3)

## How to Run 

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 11 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=test target/spring-boot-rest-example-0.5.0.jar
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
```
* Check the logs @ c:/temp/logs/fss/application.log to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2017-08-29 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090 (http)
2017-08-29 17:31:23.097  INFO 19387 --- [           main] com.fss.example.Application        : Started Application in 22.285 seconds (JVM running for 23.032)
```

### Create a customer resource

```
POST http://localhost:8080/customer/add
Accept: application/json
Content-Type: application/json

{
    "customerID": "arasu.dhanush",
    "customerName": "Thirunavukkarasu",
    "customerPhone" : "123456789",
    "customerEmail" : "dhanush@fsspay.com"
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/customer/add
```

