# Products Service

## Overview
Currently supports operations pertaining to Product. It uses PostgreSQL as database to store and retrieve the product data. The service uses flyway for database migrations.

* get all products 

### Build and Deploy
The service is deployed as part of docker-compose. docker-compose pulls the postgreSQL images, builds demo-products-service and runs the PostgreSQL database and the service as docker images.

#### Step 1 - Maven Build

```
mvn clean install
```

#### Step 2 - Run Docker Compose

```
docker-compose up --build --remove-orphans
```

Some helpful commands
One liner to stop / remove all of Docker containers:

* docker stop $(docker ps -a -q)
* docker rm $(docker ps -a -q)

#### Step 3 - Test
http://localhost:8090/api/products-service/products
