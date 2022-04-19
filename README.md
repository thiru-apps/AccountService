# Spring Boot Account Maintanence Service

Account Maintanence Service developed under Java / Maven / Spring Boot (version 2.6.3)

## How to Run 

This application is packaged as a jar which has Tomcat embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone/fork this repository 
* Make sure you are using JDK 11 and Maven 3.x.
* This project is embedded with H2 DB
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=dev target/AccountService-0.0.1-SNAPSHOT
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=dev"
```

## How to navigate to log path 

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

RESPONSE: HTTP 200 (OK)
{
    "id": "arasu.dhanush",
    "customerName": "Thirunavukkarasu",
    "customerPhone": "123456789",
    "customerEmail": "dhanush@fsspay.com,
    "dateOpened": "04/20/2022",
    "dateClosed": null
}
```


### To list all the account holder

```
GET http://localhost:8080/account/list


RESPONSE: HTTP 200 (OK)
[
    {
        "id": "rfL2I37j",
        "customers": {
            "id": "arasu.dhanush",
            "customerName": "Thirunavukkarasu",
            "customerPhone": "123456789",
            "customerEmail": "arasu@fsspay.com",
            "dateOpened": "04/20/2022",
            "dateClosed": null
        },
        "dateOpened": "2022-04-20",
        "dateClosed": null,
        "minimumBalance": 5000.00,
        "currentBalance": 5000.00
    },
    {
        "id": "qlP8uhzC",
        "customers": {
            "id": "arasu.nilan",
            "customerName": "Thirunavukkarasu",
            "customerPhone": "123456789",
            "customerEmail": "nilan@fsspay.com",
            "dateOpened": "04/20/2022",
            "dateClosed": null
        },
        "dateOpened": "2022-04-20",
        "dateClosed": null,
        "minimumBalance": 5000.00,
        "currentBalance": 5000.00
    }
]
```

### Credit / Debit Service [ ../debit or ../credit ]

```
POST http://localhost:8080/transaction/debit
Accept: application/json
Content-Type: application/json

{
    "accountID": "EOaLJdsH",
    "transactionType": "CREDIT",
    "transactionAmount" : "500",
    "transactionNotes" : "to Arasu"
}

RESPONSE: HTTP 200 (OK)
"Transaction completed"
```

### To list all the transaction

```
GET http://localhost:8080/transaction/list


RESPONSE: HTTP 200 (OK)
[
    {
        "id": 1,
        "accounts": {
            "id": "dPgWZPxe",
            "customers": {
                "id": "arasu.nilan",
                "customerName": "Thirunavukkarasu",
                "customerPhone": "123456789",
                "customerEmail": "nilan@fsspay.com",
                "dateOpened": "04/20/2022",
                "dateClosed": null
            },
            "dateOpened": "2022-04-20",
            "dateClosed": null,
            "minimumBalance": 5000.00,
            "currentBalance": 17500.00
        },
        "transactionDate": "04/20/2022",
        "transactionTypes": {
            "id": 1,
            "transactionType": "CREDIT"
        },
        "transactionAmount": 12500.00,
        "transactionNotes": "Get Amount",
        "balance": 17500.00
    }
]

```


### To Get Balance

```
GET http://localhost:8080/account/balance
Accept: application/json
Content-Type: application/json

{
    "accountID" : "rfL2I37j"
}

RESPONSE: HTTP 200 (OK)
Your Account Balance : 5000.00
```

### To close the account

```
PUT http://localhost:8080/account/close
Accept: application/json
Content-Type: application/json

{
    "accountID" : "rfL2I37j"
}

RESPONSE: HTTP 200 (OK)
"Account has been closed"
```


### Insufficient Fund Test

```
POST http://localhost:8080/transaction/debit
Accept: application/json
Content-Type: application/json

{
    "accountID": "71ersIUN",
    "transactionType": "DEBIT",
    "transactionAmount" : "12000",
    "transactionNotes" : "Get Amount"
}

RESPONSE: HTTP 200 (OK)
"In sufficient balance : Overdraw with minimum balance"
```

### Debit / Credit transaction after closing account

```
POST http://localhost:8080/transaction/debit
Accept: application/json
Content-Type: application/json

{
    "accountID": "71ersIUN",
    "transactionType": "CREDIT",
    "transactionAmount" : "500",
    "transactionNotes" : "to Arasu"
}

RESPONSE: HTTP 200 (OK)
"Account has been closed so you can't do the transaction"
```
