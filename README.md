# WattElse_EQL_Projet2 by Team TIM
Arvydas Sepetys
Mazir Ouahioune
Miroslava Castillo
Cyrine Said Ali


## ENDPOINTS:
Starts by: /api/rest
## Authentification
1. Authenticate using user already registered in the system (DB)
*/connection/authenticate
POST: send credentials
{
"email":"example@mail.mdr",
"password":"theSecretWord"
}
## User details
2.1 Register user in the system (DB)\
*/user/registration\
POST:\
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
}\
2.2 Close the User Account\
Header must contain token\
*/user/close\
POST:\
{
"userId":15,
"token":"4802895",
"reasonId":1
}\
2.3 Modify user details\
Header must contain token\
*/user/modify\
POST\
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
}\
2.4 GET user details\
*Header must contain token\
*/user/details\
GET\
## Bank Card
3.1 Add credit card to DB\
*/payment_methods/card/add\
POST\
{
"numberCard":"1234-4321-5689-1312",
"cardHolderName" : "Chipmunk Le great",
"expirationDate" : "2025-10-12",
"cvvNumber" : 666,
"userId":55
}\

3.2 show all credit cards per user\
*/payment_methods/card/all\
GET\
/activate bearer token\
3.3 Remove a credit card from DB\
*/payment_methods/card/close
POST\
{
"bankCardId":6
}\
## Bank Account
4.1 Add Bank Account to DB\
Header must contain token\
*/payment_methods/account/add\
POST\
{
"iban":"1234-4321-5689-1312",
"ownerName" : "Chipmunk Le great",
"swift" : 666,
"userId":2
}\
\
4.2 Show all accounts \
*/payment_methods/account/all
GET\
\
4.3 Remove an account from DB\
*/payment_methods/account/close\
POST\
{
"idBankAccount":10
}\
## CARS
5.1 Add Car to DB\
Detect plug type by "brand" and "carModel". If one or both are missing uses "plug"
*/car/add\
POST\
{
"brand":"Tesla",
"carModel":"MODEL S",
"userId":3,
"licensePlateNumber" : "999999",
"maxElectricPower" : 15,
"plug" : "NACS"
}\

5.2 Get all user cars\
Header must contain token\
*/car/get/all
GET\

## COMPONENTS
6.1 Recover all plug types recorded in system\
/components/plugs/all\
GET\

6.2 Get plugs used by specific car model\
/components/plugs/by_car\
POST\
{
"brand": "Tesla",
"carModel" : "MODEL 3"
}\

6.3 Get all brands registered in the system\
/components/brands\
GET\

6.4 Get all models registered under a brand\
/components/{brand}/models\
GET\

6.5 Get account closing reasons\
components/reasons/accountClose\
GET\

6.6 Get car withdrawal reasons\
components/reasons/car_withdrawal\
GET\

6.7 Get transaction evaluation criteria\
components/evaluation_types\
GET\

6.8 Get payment refusal reasons\
components/reasons/payment_refusal\
GET\

6.9 Get price type\
components/reasons/pricing_type\
GET\

6.10 Get reservation cancel reasons\
components/reasons/reservation_cancellation_type\
GET\

6.12 Get station closing reasons\
components/reasons/station_closing_type\
GET\

6.13 Get weekday\
components/reasons/day\
GET\


## TERMINALS
7.1 Find charging stations\
Time must be provided in "HH:mm form"
*/terminals/find\
POST\
{
"searchRadius":5,
"startingLat":48.81633462767654,
"startingLong": 2.327039836437512,
"plugType" : "NACS",
"timeZone":"Europe/Paris",
"date":"2025-02-19",
"time":"09:00"
}\
*time parameter is optional, if not provided, current server time is used\

7.2 Get info of specific Charging Station\
*/terminals/info/{id}\
GET/

7.3 Get opening hours of specific Charging Station\
*/terminals/info/hours\
POST\
{
"stationId" :1,
"timeZone" : "Europe/Paris"
}\

7.4 Get reservation hours of specific charging station\
*/terminals/info/occupied\
POST\
{
"stationId" :1,
"date" : "2025-02-19"
}\

7.5 Get opening hours of specific terminal on specific day\
*/terminals/info/day/hours\
POST\
{
"stationId" :1,
"date" : "2025-02-19"
}\

7.6 Get closed days of the charging station
*/terminals/info/day/occupied/{id}\
GET\



## TRANSACTIONS
8.1 Reserve a charging station\
Reservation is refused if no payment method is provided "idUserBankAccount" or "idUserBankCard"
/transaction/reservation\
POST\
{
"idStation":1,
"idUser":"2",
"reservationDate":"2025-03-01",
"reservationTime":"12:00",
"timeZone":"Europe/Paris",
"reservationDuration":30,
"idUserBankAccount":1
}

8.2 Indicate start charging\
/transaction/start\
POST\
{
"idReservation":10
}\
8.3 Indicate end charging\
/transaction/stop\
POST\
{
"idReservation":10
}
8.4 Get information on specific transaction\
/transaction/info/reservation/{reservation_id}\
GET\
8.5 Get transaction made by user from date\
/transaction/info/user/history\
POST\
{
"userId":2,
"date":"2025-02-18"
}\
8.6 Pay for the transaction\
Need to send again the method of payment:\
"idUserBankAccount" or "idUserBankCard". This permits to change the method of payment just before the payment.\

/transaction/pay\
POST\
{
"idReservation":38,
"idAccountForPayment":1
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