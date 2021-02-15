# mathoperations-api
Api rest for calculating math operations

# Build and run api
First, download commons poject, then download this project, build both with maven and run locally if you want.
For deploy in docker:
1. Execute command mvn clean install for generate jar
2. Build docker image execute docker build -t mathoperations-api . command into the folder project
3. Execute docker run -p 9095:9095 -t mathoperations-api command

# Use api
With a tool for consumes api rest, put the endpoint service http:ip:9095/mathoperations/api/v1/operation and set the all json properties

# Things I didn't get to do
- All documentacion api with Swagger, only somethings for that
- Code documentation

# Technologies, tools and libraries used
- Spring Boot
- H2 Database
- Docker 19.03.1
- Maven 3.6.3
- io.swagger
- javax.validation