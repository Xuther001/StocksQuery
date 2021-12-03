# StocksQuery

## About this Readme
This readme is currently for the purposes of setting up the app and testing the app.

## About the App
This app can query information about any publicly traded stocks.

## Setup
### Mysql
This app uses your local mysql database to store information. You need to create a database called "stockQuery" and add a table called "searchRecord" which has three fields called "Symbol" "Price" and "DateTime" which are of type "varchar(100)" "double" and "datetime" respectively.

Open your terminal and type in the following:
```
mysql -u root -p
```
"root" being your username by default (if your username is different, then replace it here), then enter the password for the username if you had set up a password.

To create the database:
```
CREATE DATABASE stockQuery;
```
To create the table:
```
CREATE TABLE IF NOT EXISTS searchRecord (
    Symbol varchar(100),
    Price double,
    DateTime datetime
);
```
Within the StocksQueryApplication.java change
```
Connection conn = DriverManager.getConnection(connectionUrl, "username", "password");
```
to reflect your local mysql username and password.

## Running the App & Commands
Run the app by right clicking on "StocksQueryApplication" then "Run as" -> "Java Application"
```
src/main/java/stocksquery/StocksQueryApplication.java
```
You will need to wait a few seconds for it to start up. Once started you will be prompted with a message introducing you to some commands.

## API Source
API source can be found below. Any feature is limited to the scope of these APIs.
```
https://finnhub.io/docs/api
```

## Files currently not used
```
src/main/java/stocksquery/HomeController.java
src/main/resources/templates/start.html
```
These files are there to potentially be used for another feature sometime in the future.

## Known Bugs
### Status: Actively working on it
Currently the app will process ALL inputs. Yes, even if you enter an invalid stock symbol it will process it as if it's a valid stock symbol and will be saving the invalid stock symbol as well as the incorrect data returned by the API.
### Status: Fixed
No Entries Yet
