SpringBoot Application on Docker 
===

* Younggyu Kim (younggyu.kim@oracle.com)
* Oracle Cloud Adoption Platform
* Principal Sales Consultant

---
# Spring Boot Application on Docker
<!-- .slide: class="center" -->
* PT: [https://gitpitch.com/credemol/docker-spring-tutorial?p=presentation#/](https://gitpitch.com/credemol/docker-spring-tutorial?p=presentation#/)

* Slack: cloudnativeapp.slack.com

---
## Pre requisites

* JDK 1.8: [http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Maven 3.5: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
* Spring Tool Suite: [https://spring.io/tools/sts](https://spring.io/tools/sts)
* Postman: [https://www.getpostman.com/apps](https://www.getpostman.com/apps)
---
### set environment variables: 

  * JAVA_HOME: specify the location where java has been installed
  * MAVEN_HOME: specify the location where you unzipped the maven.zip file
  * PATH: $JAVA_HOME/bin:$M2_HOME/bin:   

```sh
$ java -version
$ mvn -v
$ echo $PATH  
```

---
## Create a Spring Boot Project

1. Run Spring Tool Suite and see where the workspace is
1. Run: File > New > Spring Starter Project

---?image=https://user-images.githubusercontent.com/5771924/33048475-68d85cfc-ce9e-11e7-9056-27de3acef363.png&size=auto 90%


---?image=https://user-images.githubusercontent.com/5771924/33048495-8ce01edc-ce9e-11e7-867f-d287025bc4e8.png&size=auto 90%

---
### Project Properties

Property Name | value
--------------|----------------
Name          | docker-spring-tutorial
Type          | Maven
Group         | ocap.tutorial
Artifact      | docker-spring-tutorial
Package       | ocap.tutorial.dockerspring

---
### Project Dependencies

* SQL > JPA
* SQL > MySQL
* Web > Web

---
### Create Dockerfile-mysql

```sh
$ mkdir docker
$ cd docker
$ vi Dockerfile-mysql
```
---
### Dockerfile-mysql

```dockerfile
FROM mysql:5.7
ENV MYSQL_ROOT_PASSWORD="springdb"
ENV MYSQL_DATABASE="springdb"
ENV MYSQL_USER="springdb"
ENV MYSQL_PASSWORD="springdb"
EXPOSE 3306
```

---
### Build Docker Image and Run

```sh
$ docker build -t spring_db -f Dockerfile-mysql .
$ mkdir -p ~/tmp/spring_data

$ docker run -d --name spring_db -p 3306:3306 \
 -v ~/tmp/spring_data:/var/lib/mysql spring_db
```

---?image=https://user-images.githubusercontent.com/5771924/33059835-554f6c8a-ced9-11e7-8056-7e37b0f1d258.png&size=auto 90%

---
### src/main/resources/application.properties

```properties
spring.datasource.url = jdbc:mysql://localhost:3306/springdb?useSSL=false

# Username and password
spring.datasource.username = springdb
spring.datasource.password = springdb
```

---
### Compile your project with Maven

1. Open a terminal window
1. Change directory to docker-spring-tutorial under Workspace Directory
1. run _mvn compile_. It takes a while to download required library from the Internet.

```sh
$ mvn clean
$ ls -l

$ mvn compile
$ ls -l target

$ mvn clean
$ ls -l
$ mvn package
$ ls -l target
```

---
### Create a Controller

1. select ocap.tutorial.dockerspring under src/main/java directory
1. Run New > File > Class

Property Name | Property Value
--------------|--------------------
Package       | ocap.tutorial.dockerspring.web
Name          | HelloWorldController



--- 
### Controller
File: ocap/tutorial/dockerspring/web/HelloWorldController.java
```java
package ocap.tutorial.dockerspring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/helloworld")
public class HelloWorldController {

	@RequestMapping(method=RequestMethod.GET, path="/simple")
	public @ResponseBody String 
		sayHello(@RequestParam(name="name", defaultValue="World") String name) {
		
		String msg = "Hello " + name;
		return "<h1>" + msg  + "</h1>";
	}
}
```
@[1-7](HelloWorldController.java - import required classes)
@[8-20](HelloWorldController.java - Controller)

--- 
### Test HelloWorldController 

```sh
$ mvn clean package
$ java -jar target/docker-spring-tutorial-0.0.1-SNAPSHOT.jar
```

* [http://localhost:8080/helloworld/simple](http://localhost:8080/helloworld/simple)
* [http://localhost:8080/helloworld/simple?name=Kim](http://localhost:8080/helloworld/simple?name=Kim)

<!--

### MVC

### create helloworld.jsp
SKIP

```sh
$ mkdir -p src/main/webapp/WEB-INF/jsp
```

1. select jsp folder that you've just created right before
1. Run File > New > Other
  * Select Web > JSP File
  * Filename: helloworld.jsp 


-->
---
### Entity
JPA: Java Persistence API

1. select ocap.tutorial.dockerspring under src/main/java directory
1. Run New > File > Class

Property Name | Property Value
--------------|--------------------
Package       | ocap.tutorial.dockerspring.entity
Name          | User


--- 
### User.java

```java
package ocap.tutorial.dockerspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id @GeneratedValue
	private long id;
	
	@Column
	private String username;
	
	@Column
	private String email;
	
	@Column
	private String password;
}
```
@[1-6](User.java - import required classes)
@[8-21](User.java - Entity columns)

---
#### Use Eclipse shortcut keys

1. Run Source > Generate Constructors from superclass
1. Run Source > Generate Constructor using Fields
  * select id, username, email, password
  * select username, email, password
1. Run Source > Generate Getters and Setters
  * click Select All button
1. Run Source > Generate toString
1. Run Source > Generate hashCode and equals
  * select id

---
### Repository

Repository: 

1. select ocap.tutorial.dockerspring under src/main/java directory
1. Run New > File > Interface

Property Name | Property Value
--------------|--------------------
Package       | ocap.tutorial.dockerspring.repo
Name          | UserRepository


---
### UserRepository.java
```java
package ocap.tutorial.dockerspring.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ocap.tutorial.dockerspring.entity.User;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long>{
}
```

---
### add JPA Properties to application.properties
```properties
spring.datasource.url = jdbc:mysql://localhost:3306/springdb?useSSL=false

# Username and password
spring.datasource.username = springdb
spring.datasource.password = springdb

#JPA properties
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update
```
@[7-9](JPA Properties) 

---
### Rebuild 

```sh
$ mvc clean package
$ mvc spring-boot:run
```

---
### Insert Sample Users
Run these sql statements through Mysql Workbench
```sql
insert into springdb.user (username, email, password) values ('kim', 'kim@gmail.com', 'passwd1');
insert into springdb.user (username, email, password) values ('lee', 'lee@gmail.com', 'passwd1');
insert into springdb.user (username, email, password) values ('ko', 'ko@gmail.com', 'passwd1');
```

---
### Get User Information

* [http://localhost:8080](http://localhost:8080)
* [http://localhost:8080/users](http://localhost:8080/users)
* [http://localhost:8080/users/1](http://localhost:8080/users/1)

---
### Create User through Postman

* **HTTP Method**: POST
* **URL**: localhost:8080/users
* **Headers**
  * Content-Type: application/json
* **Body**: raw

```json
{
	"username": "nicholas",
	"email": "nicholas@oracle.com",
	"password": "passwd1"
}
```

---image=https://user-images.githubusercontent.com/5771924/33069307-78e97e06-cef7-11e7-9395-0df561813c60.png&size=auto 90%


---
### Create User through Postman

* **HTTP Method**: PUT
* **URL**: localhost:8080/users/4
* **Headers**
  * Content-Type: application/json
* **Body**: raw

```json
{
	"username": "nicholas",
	"email": "nicholas@gmail.com",
	"password": "mypasswd1"
}
```
@[3-4](email and password have been changed)

---image=https://user-images.githubusercontent.com/5771924/33070398-37af6244-cefb-11e7-8cc1-10911ecee524.png&size=auto 90%

### Delete User through Postman
* **HTTP Method**: DELETE
* **URL**: localhost:8080/users/4

---image=https://user-images.githubusercontent.com/5771924/33070524-9d2c3e44-cefb-11e7-80ad-367d96b80a01.png&size=auto 90%

---
<!-- .slide: class="center" -->
## Q & A
 


