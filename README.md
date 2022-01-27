# Data Store
Provides a data store interface to save key value pairs. This application is stateless and can be scaled to multi-instance setup.

# Features
Register new key value pair
Remove key value pair
Get a value from provided key

## Prerequisite
1. Maven 3.5.3 or above
2. Java 11
## How To Run

### Steps to build and run
1. `cd database; docker-compose up` This will start the mysql database at port 3306
2. `mvn clean install`
3. `mvn spring-boot:run`
### Optional docker run with 2 or more instances
mvn clean install will create a docker image with the name datastore:1.0.0. You can run and play with the application using the following command<br>
`docker run --network=host datastore:1.0.0`<br>
This will run the application on port 8080.<br>
Create a new file application.properties with value server.port=8081<br>
`docker run --network=host ${PWD}/application.properties:/config/application.properties datastore:1.0.0`
## Application Access
curl -X PUT -H "Content-Type: application/json" -d '{"value":"\<yourvalue\>"}' http://localhost:8080/rest/api/v1/values/{key} <br>
curl http://localhost:8080/rest/api/v1/values/{key} <br>
curl -X DELETE http://localhost:8080/rest/api/v1/values/{key}<br>

curl -X PUT -H "Content-Type: application/json" -d '{"value":"\<yourvalue\>"}' http://localhost:8081/rest/api/v1/values/{key} <br>
curl 'http://localhost:8081/rest/api/v1/values/{key}' <br>
curl -X DELETE http://localhost:8081/rest/api/v1/values/{key}<br>