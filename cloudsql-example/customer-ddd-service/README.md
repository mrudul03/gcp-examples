#### Customer Service

This is an implementation of Spring Data JDBC to show case DDD principles such as Aggregate, ValueObject. The service uses Postgres database to store customer records. It uses Flyway for database changes versioning.


#### Build
```
mvn clean install
docker build -t customer-ddd-service:v01 .
docker up
```


#### Database 

##### Connect to database 
```
# Start datbase
pg_ctl -D /usr/local/var/postgres start

# Open postgres client
psql postgres

# Connect default database
postgres=# \c postgres

# Show all databases
\l

# Create user for new database
create user customerdbuser;
alter user customerdbuser with encrypted password 'ChangeMe';

# Create database and grant privilege to user
create database customerdb;
grant all privileges on database customerdb to customerdbuser;

# Connect to newly created database
\c customerdb customerdbuser;

# Show tables
\dt

# Quit from Postgre Database
\q
```
#### Test
```
CURL GET http://localhost:8083/api/customer-service/customers
POST http://localhost:8083/api/customer-service/customers
{
	
	"firstname": "FName123",
	"lastname":"LName123",
	"mobilenumber":"1212121212123",
	"address":{
		"city":"Pune",
		"state":"Maharashtra",
		"street":"Baner Road",
		"zipcode":"411123"
	}
}
```

#### References
* Ref- https://lumberjackdev.com/spring-data-jdbc
* Ref- https://github.com/lumberjackdev/getting-started-spring-data-jdbc

* Introduction to Spring Data JDBC - https://lumberjackdev.com/spring-data-jdbc

* Ref- https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#reference

* Ref- Domain-Driven Design with Relational Databases Using Spring Data JDBC- https://www.infoq.com/presentations/ddd-spring-data-jdbc/?utm_source=notification_email&utm_campaign=notifications&utm_medium=link&utm_content=&utm_term=weekly

* https://github.com/spring-petclinic/spring-petclinic-data-jdbc

