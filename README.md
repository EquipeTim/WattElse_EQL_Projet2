# WattElse_EQL_Projet2 by Team TIM
Arvydas Sepetys
Mazir Ouahioune
Miroslava Castillo
Cyrine Said Ali


## ENDPOINTS:
Starts by: /api/rest

1. Authenticate using user already registered in the system (DB)
/connection/authenticate
POST: send credentials
{
"email":"example@email.eql",
"password":"mysecretWord"
}

2.1 Register user in the system (DB)
/user/registration
POST: 
{
"name":"myName",
"surname":"mySillySurname",
"birthdate":"1990-08-01",
"email":"example@mail.mdr",
"password":"theSecretWord",
"address":"141 Boulevard Mortier",
"phone_number":"123456-89",
"city":"Paris",
"postal_code":"75020"
}
2.2 Close the User Account
*Header must contain token
/user/close
POST:
{
"userId":15,
"token":"4802895",
"reasonId":1
}
2.3 Modify user details
*Header must contain token
/user/modify
POST
{
"id":54,
"name":"GRator",
"surname":"travaillor",
"birthdate":"2050-08-01",
"email":"normoi@mail.mdr",
"password":"a",
"address":"BRRRAAAA",
"phone_number":"00000-00000",
"city":"Lyon",
"postal_code":"85"
}
2.4 GET user details
*Header must contain token
/user/details
GET

3.1 Add credit card to DB
/payment_methods/card/add
POST
{
"numberCard":"1234-4321-5689-1312",
"cardHolderName" : "Chipmunk Le great",
"expirationDate" : "2025-10-12",
"cvvNumber" : 666,
"userId":55
}

4.1 Add Bank Account to DB
/payment_methods/account/add
POST
{
"numberCard":"1234-4321-5689-1312",
"cardHolderName" : "Chipmunk Le great",
"expirationDate" : "2025-10-12",
"cvvNumber" : 666,
"userId":55
}

5.1 Add Car to DB
/car/add
POST
{
"brand":"Tesla",
"carModel":"MODEL S",
"userId":55,
"licensePlateNumber" : "999999",
"maxElectricPower" : 15
}


### Architecture

> See `architecture.pdf` at the root of the project.

Application in **3 layers** (presentation, business, data access),
implementing the **MVC** design pattern.

Backend part in **Java** (separation between model and controllers) and frontend part in **HTML** and **JavaScript** (separation between controllers and views).

Maven project containing:

- 1 parent project, of type `pom` (*WattElse-Parent*)
- 1 module of type `jar` for entities (*WattElse-Entity*)
- 2 modules of type `jar` containing interfaces
  (*WattElse-Dao-Int*, *WattElse-Business-Int*)
- 2 modules of type `ejb` containing implementations
  (*WattElse-Dao-Impl*, *WattElse-Business-Impl*)
- 1 module of type `war` for the presentation part (*WattElse-Web*)
- 1 module of type `ear` containing all the other modules in dependency,
  and which will be deployed on the application server.

The web module contains controllers in Java, and static resources
(HTML, CSS, JavaScript) in the *webapp* folder.

### Technology used

With JDK 1.8:
- **JBoss `7.4`**: Application server
- **EJB `3.1`**: inversion of control by dependency injection in
  the business and data access layers to indicate
  to the application server the implementations to use for each
  interface.
- **JDBC `4.2`**: To interface the application with a MySql
  type database.
- **Rest**: API points in the *WattElse-Web* controllers,
  requests to these points in the controllers in JavaScript.

***Backend***
- **javax `7.0`**: dependency declared in the `pom.xml` of the parent,
  to do JEE. Scope of type `provided`,
  because provided by ***JBoss***.
- **log4j api + core `2.17.2`**: dependency declared in the `pom.xml` of the parent,
  `log4j2.xml` file in the parent project (*src > main > resources*).
  Scope of type `provided`, because provided by ***JBoss***.
- **mysql-connector-java `8.0.29`**: dependency declared in the `pom.xml` of
  *WattElse-Dao-Impl*, to interface with a MySql type database.
- **json `20210307`**: Dependency declared in the `pom.xml` of
  *WattElse-Business-Impl*, in order to transform the `json` files requested by the
  glossary in the Wikipedia API.
- **jersey-core `1.4-ea04`**: Dependency declared in the `pom.xml` of
  *WattElse-Web*, for the security filter related to authorizations.

### Deployment

- Import the SQL database (DB) using the commands in watt_else_db_import.sam
- Install the **JDK `1.8`** and **Maven `3.9.5`**, update the Windows *path* environment variable to point to each of their *bin* folders.
- Start an instance of **MySql `5.7 or +`** and run the *cats_club_db_import.sql* file,
  located at the root of the project.
- Install **JBoss `7.4`** and enter the path to **JBoss** in the *deploy_on_jboss.bat* file,
  located at the root of the project.
- Run *deploy_on_jboss.bat*.
- Access the application via the following URL: http://localhost:8080/home.html.