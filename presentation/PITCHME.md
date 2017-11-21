SpringBoot Application on Docker 
===

* Younggyu Kim (younggyu.kim@oracle.com)
* Oracle Cloud Adoption Platform
* Principal Sales Consultant

---
<!-- .slide: class="center" -->
* PT: [https://gitpitch.com/credemol/docker-spring-tutorial?p=presentation#/](https://gitpitch.com/credemol/docker-spring-tutorial?p=presentation#/)

* Slack: cloudnativeapp.slack.com

---
## Pre requisites

* JDK 1.8: [http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Maven 3.5: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
* Spring Tool Suite: [https://spring.io/tools/sts](https://spring.io/tools/sts)

* set environment variables: 
  * JAVA_HOME: specify the location where java has been installed
  * MAVEN_HOME: specify the location where you unzipped the maven.zip file
  * PATH: $JAVA_HOME/bin:$M2_HOME/bin:
   
> Run Spring Tool Suite and see where the workspace is

---
### verify if JAVA and MAVEN have been installed properly

```sh
$ java -version
$ mvn -v
$ echo $PATH  
```

---
## Create a Spring Boot Project

Run: File > New > Spring Starter Project

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
### Compile your project with Maven

1. Open a terminal window
1. Change directory to docker-spring-tutorial under Workspace Directory
1. run _mvn compile_. It takes a while to download required library from the Internet.


---
<!-- .slide: class="center" -->
## Q & A
 


