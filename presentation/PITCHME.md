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

$ docker run -d --name spring_db -p 3306:3306 -v ~/tmp/spring_data:/var/lib/mysql spring_db
```

---?image=https://user-images.githubusercontent.com/5771924/33059835-554f6c8a-ced9-11e7-8056-7e37b0f1d258.png&size=auto 90%

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
### ocap/tutorial/dockerspring/web/HelloWorldController.java
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

	@RequestMapping(method=RequestMethod.GET)
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
<!-- .slide: class="center" -->
## Q & A
 


