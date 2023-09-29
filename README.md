# Spring-Microservice-II

## How Spring works internally?
1. When we create a Springboot project, it has the spring factories added to the META-INF folder and all the configuration, all the jar files required are mentioned there. Whenever you add any properties or any configuration and it matches with the spring factories, it tries to add that configuration to it.
2. Based on the different dependency that you add the different configuration takes place inside.
![How normal web application runs](./images/springboot.jpg)
*How normal web application runs*
![](./images/springboot1.jpg)
*Springboot combines application and server in one jar file and run that jar file*

## How to change webserver?
1. Add exclude tomcat web server from springboot-starter-web dependency
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        <exclusion>
    </exclusions>
</dependency>
```
2. Add the dependency of the webserver that you want your application should run on.

## Springboot Actuator
- Allows us to monitor our springboot application.
- Whatever happens in our springboot application that can be handle using actuator
- Add the actuator dependency to perform this operation.

## Microservice Architecture Built in this project
![](./images/Architecture.jpg)

## Microservice
1. Service Registry
   - Service Registry using Eureka server
   - Order, Product and Payment will be Eureka client
2. 
## Questions/Doubts
1. How spring works internally?
2. How springboot application runs?
3. How annotations play and important role in springboot application?
4. What are embedded server?
5. what is Springboot Actuator?
6. What is Springboot devtools?
