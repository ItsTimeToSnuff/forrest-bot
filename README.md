# Forrest-bot

It`s a CRM telegram bot for "For.Rest" paintball club. This bot uses 
Google Spreadsheets like DB.

## Functional
- create a record of the new game in Google Calendar
- write down statistics after the game
- write down replenishment/costs from stock
- get warehouse balance
- write down of embezzlement/replenishment of cashbook
- get cashbook balance
- get statistics for the current month/year

## Release notes

- ### **_v1.2:_**
  - add new items to bot: smoke S, smoke XL, and plastic grenade
  - refactor smoke L to smoke M

- ### **_v1.1:_**
  - add roles: ```ADMIN```, ```USER```
  - add environmental variable ```ADMIN_ID_LIST``` to manage list of admins
  - fix bug with concurrent user access
  - add some small fixes and improvements 

## Building project

JDK 11 need to be installed and ```environmental variables``` need to be created:

- ```BOT_TOKEN```
- ```CALENDAR_ID```
- ```SPREADSHEETS_ID```
- ```PORT```
- ```ADMIN_ID_LIST```

and had a ```google-credentials.json``` file at ```{PROJECT_ROOT_DIR}/token/```.
Also ```src/main/resources/dev.properties or.prod properties``` need to be edited, specifically: ```externalUrl```, ```internalUrl```. 
And if you running it from the local machine, ```ngrok``` or similar program need to be installed.

Next from the project root dir run:

- Windows: 
```gradlew clean build```
- Mac, Linux: 
```./gradlew clean build```

to skip test phase add `-x test` to the previous command.

## Running project

From project root dir run:
- Windows: 
```java -jar build/libs/forrest-bot-{VERSION}.jar prod/dev.properties```
- Mac, Linux: 
```./java -jar build/libs/forrest-bot-{VERSION}.jar prod/dev.properties```



