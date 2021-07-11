# Kawal Kan
REST API that serves as a pivot for different communication channels

## 1. Technologies

- Java 8
- Spring boot 2.0
- PostgreSQL
- Lombok
- Maven
- Docker

## 2. Starting the environment
To run the complete solution clone the GitHub repository project and run the following commands in the terminal


```bash

- mvn install -o -DskipTests

- docker compose up --build

```

## 3. Endpoints



### Create new contact

URL

| POST         | http://localhost:8080/api/v1/contacts  |
| ------------ | -------------------------------------- |
| Content-Type | application/json                       |

Request

| Key          | Type              | Value sample       |
| ------------ | ----------------- | ------------------ |
| name         | String            | Paul Schmidt       |
| email        | String            | pschmidt@gmail.com |
| telephone    | String            | +502 1020 3040     |
| age          | Number            | 20                 |


### Get list of contacts

URL

| GET           | http://localhost:8080/api/v1/contacts |
| ------------- | ------------------------------------- |


### Create a new chat channel

URL

| POST         | http://localhost:8080/api/v1/chats     |
| ------------ | -------------------------------------- |
| Content-Type | application/json                       |

Request

| Key           | Type             | Value sample       |
| ------------- | ---------------- | ------------------ |
| title         | String           | New chat channel   |


### Send a message to a contact

URL

| POST          | http://localhost:8080/api/v1/messages |
| ------------- | ------------------------------------- |
| Content-Type  | application/json                      |

Request

| Key           | Type             | Value sample       |
| ------------- | ---------------- | ------------------ |
| fromContactId | Number           | 1                  |
| toContactId   | Number           | 2                  |
| message       | String           | This is a message  |
| chatId        | Number           | 1                  |


### Get list of previous conversations by Sender Contact ID

URL

| GET           | http://localhost:8080/api/v1/chats/:ID |
| ------------- | -------------------------------------- |


### Webhook endpoint

URL

| POST          | http://localhost:8080/api/v1/webhook  |
| ------------- | ------------------------------------- |
| Content-Type  | application/json                      |

Request

| Key           | Type             | Value sample       |
| ------------- | ---------------- | ------------------ |
| fromContactId | Number           | 1                  |
| toContactId   | Number           | 2                  |
| message       | String           | This is a message  |
| chatId        | Number           | 1                  |


## 4. Placeholder Workflow
 
The system has de ability to replace some the content of a message by using the special placeholders.
The placeholders are stored as Key-Value pair elements, and a status to indicate whether a placeholder is active or not.

### Internal Configuration

There are two types of placeholders that can be configured.

#### 
- a) The first one has its value directly attached to the 'key'. 
  e.g. 
  bitcoin - € 28,440.43
  eur_usd_rate - 1.18

#### 
- b) The second one, the value is going to be retrieved from the Contact attributes, the 'Key' should contain the word 'contact_attributes', and the 'Value' has the contact attribute. 
  e.g.
  name - contact_attributes
  telephone - contact_attributes

### Usage

The user can apply placeholders by using the following notation within the message ${PLACEHOLDER_KEY}.

E.g.
```javascript
{
    "fromContactId": 1,
    "message": "My name is ${name}, and this is the current value of bitcoin ${bitcoin}"
}
```

Output result
```javascript
{
    "fromContactId": 1,
    "message": "My name is Maximilian Müller, and this is the current value of bitcoin € 28,440.43"
}
```




