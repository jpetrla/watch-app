# WatchApp

## Architecture
- Spring Boot application
- Starters: web, data-jpa, validation
- Other dependencies:
  - jackson-dataformat-xml - XML processing
  - lombok & mapstruct - reducing some boilerplate code
  - postgresql - JDBC driver
- DB:
  - Postgre SQL for runtime
  - H2 for unit and integration tests
- Build and dependency management tool: Maven

## Run application
1) Running database is needed, so you have two options now:
- if you have your own database, just update datasource properties in application.properties, connect to it and run following SQL command:
  - `CREATE DATABASE watch_app;`
- or you can run following command in terminal window which will start docker container with Postgre SQL DB and prepare database watch_app
  - `docker run --name postgres-watchapp -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=watch_app -d postgres`
2) Run the following command in terminal window from root folder of the application:
- MacOS / Linux:
  - `./mvnw spring-boot:run`
- Windows:
  - `mvnw spring-boot:run`

## REST service description
### Upload watch item to the e-shop warehouse
Success:
- Request:
  - method: POST
  - url: http://localhost:8080/api/v1/watch
  - header: Content-Type: application/json
  - body:
`{
  "title": "Prim",
  "price": "1",
  "description": "A watch with a water fountain picture",
  "fountain": "R0lGODlhAQABAIAAAAUEBA=="
}`

- Response:
  - status: 201 Created
  - body:
`{
  "timestamp": "2021-09-07T17:33:13.854+00:00",
  "status": 201,
  "message": "Upload successful"
}`

Error:
- Request:
  - method: POST
  - url: http://localhost:8080/api/v1/watch
  - header: Content-Type: application/json
  - body:
`{
  "title": null,
  "price": "0",
  "description": "A watch with a water fountain picture",
  "fountain": "R0lGODlhAQABAIAAAAUEBA=="
}`

- Response:
  - status: 400 Bad Request
  - body:
`{
  "timestamp": "2021-09-07T17:38:24.181+00:00",
  "status": 400,
  "errors": [
    "title: must not be blank",
    "price: must be greater than 0"
  ]
}`
