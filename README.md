## Functionalities and configuration files for the back-end
This project already has some libraries and functionalities ready, such as the following:
* [x] Java 21
* [x] Spring-boot-JPA
* [x] Spring Boot
* [x] Postgres SQL
* [x] Spring-boot-starter-validation
* [x] Hibernate
* [x] Lombok
* [x] Junit
* [x] Swagger for documentation
* [x] Fly for migration
* [x] WebFlux 

## Settings

# Change the application.properties file to the postgres database settings and API port if necessary
# API is listening on port 8080
# The token needs to be changed in the api-x-key property to extract the CoinCap api, the token has limitations of 2500 requests
# Upload the database container in docker-compose

## Considerations

# CoinCap's API 2.0 is deprecated, so I'm using version 3. If there is an httpclient error, change to a new token, as the token has limitations.