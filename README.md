# Projeto Parking
DIO Bootcamp - Quebec Java Digital - Projeto Parking

This project is based on a LAB from DIO Quebec Java Digital Bootcamp

## Important problem solving

Create the file 'system.properties' to inform Heroku about the Java version that should be used to deploy the Application
[Heroku Java Support](https://devcenter.heroku.com/articles/java-support)

## Other important docs

### Heroku

git push heroku main

[The Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli)

[Deploying Spring Boot Applications to Heroku](https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku)

[Preparing a Spring Boot App for Production on Heroku](https://devcenter.heroku.com/articles/preparing-a-spring-boot-app-for-production-on-heroku)

[Working with Spring Boot](https://devcenter.heroku.com/categories/working-with-spring-boot)

[GitHub Integration (Heroku GitHub Deploys)](https://devcenter.heroku.com/articles/github-integration)

### Transactions

[Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
[Managing Transactions with Spring and Spring Data JPA](https://thorben-janssen.com/transactions-spring-data-jpa/)
[Spring Boot Transactions: Understanding Transaction Propagation](https://dzone.com/articles/spring-boot-transactions-tutorial-understanding-tr)

### Other resources

[HTTP Methods](https://restfulapi.net/http-methods/)
[Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
[Test Containers](https://www.testcontainers.org)
[Basic Authentication Header Generator](https://www.blitter.se/utils/basic-authentication-header-generator/)
[Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/index.html)

## Steps necessary

### Run database

[Install Docker](https://github.com/codeedu/wsl2-docker-quickstart)
[Get started with Docker remote containers on WSL 2](https://learn.microsoft.com/en-us/windows/wsl/tutorials/wsl-containers)
[Install Docker Engine on Ubuntu](https://docs.docker.com/engine/install/ubuntu/)
[Post-installation steps for Linux](https://docs.docker.com/engine/install/linux-postinstall/)

docker run --name parking-db -p 5432:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d postgres:10-alpine

sudo lsof -i -P -n | grep LIST

docker stop parking-db
docker start partking-db

### Test App Using Terminal

curl -u "user:12345" -X GET "http://localhost:8080/parking" -H "accept: */*" (com autenticação ativada)
curl -X GET "http://localhost:8080/parking" -H "accept: */*" (sem autenticação ativada)
curl -v -X GET "http://localhost:8080/parking" -H "accept: */*" (com autenticação ativada)

echo -n user:12345| base64 (BASIC Authentication -> usar o resultado na linha abaixo)
curl -X GET "http://localhost:8080/parking" -H "accept: */*" -H "authorization: Basic dXNlcjoxMjM0NQ=="
