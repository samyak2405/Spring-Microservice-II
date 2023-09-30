# Spring-Microservice-II

## Key Learnings so far
1. How springboot works?
2. What are embedded servers?
3. What is spring actuator?
4. Service registry using Eureka server
5. Exception handling in spring
6. How to handle repetitive configurations using spring config server
7. How to call API of one microservice from another microservice using Feign Client
8. Implement ErrorDecoder to handle exception and adding exception handler
9. Zipkin and Sleuth to enable distributed tracing in microservices architectures and to track and diagnose performance issues by providing visibility into request flow and dependencies.
10. How to call API of one microservice from another microservice using RestTemplate
11. Implemented API Gateway

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

## Config server
- helps manage and centralize configuration settings for multiple microservices
- **Centralized Configuration Management:** In a microservices architecture, you may have numerous microservices that require various configuration settings such as database connection strings, API keys, feature toggles, and more. Managing these configurations individually in each microservice can be cumbersome and error-prone. A Config Server centralizes these configurations in one place, making it easier to manage and update them.

- **Dynamic Configuration Updates:** Config Servers are capable of providing dynamic configuration updates to microservices without requiring them to be restarted. This means you can change configuration settings in real-time without interrupting the services, which is essential for maintaining high availability.

- **Environment-Specific Configurations:** With a Config Server, you can store configurations for different environments (e.g., development, testing, production) and ensure that each microservice uses the appropriate configuration for its environment. This simplifies the deployment process and reduces the risk of configuration errors.

- **Versioned Configurations:** Config Servers often support versioning of configuration settings. This means you can track changes to configurations over time, making it easier to understand what changed and when. It also allows you to roll back to previous configurations if needed.

- **Security:** Config Servers can provide security features such as encryption and access control, ensuring that sensitive configuration data is protected. Access to configurations can be restricted based on roles and permissions.

- **Integration with Spring Cloud:** Spring Boot's Config Server is part of the Spring Cloud ecosystem, which provides a set of tools and libraries for building microservices-based applications. It seamlessly integrates with other Spring Cloud components, such as Eureka (Service Discovery) and Ribbon (Load Balancing), to create a robust microservices architecture.

- **Simplifies Microservices Deployment:** When deploying microservices, you don't need to redeploy each service when configuration changes are made. Instead, the microservices fetch updated configurations from the Config Server when needed. This simplifies deployment and reduces downtime.

- **Consistency and Standardization:** Centralized configuration management promotes consistency and standardization across microservices. It ensures that all services adhere to the same set of configuration principles and best practices.

## Feign Client
- Feign is a declarative web service client developed by Netflix as part of the Spring Cloud ecosystem. It simplifies the process of making HTTP requests to RESTful web services by allowing you to write HTTP clients in a more concise and expressive manner. Feign is particularly useful in microservices architectures where one microservice needs to communicate with another over HTTP.

## Zipkin and Sleuth

- Zipkin and Sleuth are two popular tools used in the field of distributed systems and microservices to assist with distributed tracing, which is the practice of tracking and monitoring requests as they flow through a complex network of services. These tools help developers and system administrators gain insights into how requests propagate through various microservices and diagnose performance issues and bottlenecks in a distributed architecture.

### Zipkin
- Zipkin is an open-source distributed tracing system originally developed by Twitter. It provides a framework for collecting, analyzing, and visualizing traces of requests as they travel through different services.
- Zipkin allows you to instrument your applications by adding tracing code to capture information about requests and responses as they pass through different services.
- It stores and indexes trace data, making it possible to search and analyze traces to diagnose performance problems.
- Zipkin provides a web-based user interface that allows you to visualize the traces and understand the timing and dependencies between services.

### Sleuth
- Sleuth is a distributed tracing library for Java applications, primarily designed for use with the Spring Framework. It is part of the Spring Cloud ecosystem.
- Spring Cloud Sleuth integrates with Zipkin or other distributed tracing systems and simplifies the process of adding trace information to your Spring Boot applications.
- Sleuth provides automatic instrumentation for common components like HTTP requests, message queues, and database interactions. It adds trace and span information to log entries.
- Sleuth makes it easier to correlate and trace requests across microservices in a Spring-based application.

**Here's how these tools typically work together:**

1. Developers use Sleuth to instrument their Spring Boot applications, adding trace and span information to requests.
2. Trace data generated by Sleuth is sent to a distributed tracing system like Zipkin.
3. Zipkin stores and indexes this trace data, allowing users to visualize and analyze the flow of requests through the system, including details like timing, dependencies, and error information.

- By using Zipkin and Sleuth, developers can gain valuable insights into the behavior of their microservices and troubleshoot issues related to latency, errors, and dependencies in a distributed environment. This helps in improving the overall performance and reliability of microservices-based applications.

Steps
1. To install zipkin on ubuntu/macOS: `docker run -d -p 9411:9411 openzipkin/zipkin`
2. Run zipkin: `http://localhost:9411/zipkin/`
3. Dependencies to add
   ```
   <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
      <groupId>io.micrometer</groupId>
      <artifactId>micrometer-tracing-bridge-brave</artifactId>
    </dependency>
    <dependency>
      <groupId>io.zipkin.reporter2</groupId>
      <artifactId>zipkin-reporter-brave</artifactId>
    </dependency>
   ```"CASH"
4. Configuration to add
```
management:
  tracing:
    sampling:
      probability: 1.0
```

## RestTemplate
- RestTemplate is a class provided by the Spring Framework that simplifies the process of making HTTP requests to remote services. It is a part of the Spring Web module and serves as a higher-level alternative to manually managing HTTP connections and requests.
  
## API Gateway
- An API Gateway is a server or service that acts as an API front-end, receiving API requests, enforcing throttling and security policies, passing requests to the back-end service, and then passing the response back to the requester. It acts as a reverse proxy to accept all application programming interface (API) calls, aggregate the various services required to fulfill them, and return the appropriate result.

**Here are some key functions and features of an API Gateway:**

- *Request Routing:* The API Gateway directs client requests to the appropriate microservices or backend services based on the request URL, HTTP method, or other criteria. It can perform URL-based routing, path-based routing, and even header-based routing.

- *Load Balancing:* It can distribute incoming requests across multiple instances of a service to ensure high availability and scalability. Load balancing can be based on various algorithms, such as round-robin, least connections, or custom rules.

- *Authentication and Authorization:* API Gateways can enforce authentication and authorization policies for incoming requests. This includes verifying API keys, OAuth tokens, or other forms of authentication, as well as checking if the requester has the necessary permissions to access a resource.

- *Security:* They provide a centralized location for implementing security measures such as rate limiting, IP whitelisting, and request/response encryption (HTTPS).

- *Logging and Monitoring:* API Gateways can log requests and responses, making it easier to monitor and troubleshoot issues. They can also integrate with monitoring and analytics tools to provide insights into API usage and performance.

- *Request and Response Transformation:* They can modify requests and responses as they pass through, including data format conversion (e.g., JSON to XML), payload manipulation, and header modification.

- *Caching:* API Gateways can cache responses to reduce the load on backend services and improve response times for frequently requested data.

- *Analytics and Metrics:* They often include built-in analytics and metrics generation, allowing you to track API usage, performance, and errors.

- *Versioning:* API Gateways can support API versioning, allowing you to release new versions of your API without breaking existing clients.

- *Error Handling:* They can provide standardized error messages and error codes to clients, improving the clarity of error responses.

- *Transformation and Aggregation:* In some cases, they can transform and aggregate data from multiple services into a single response, reducing the number of requests a client needs to make.

- API Gateways are particularly valuable in microservices architectures, where numerous small services may be interacting with each other. They serve as a single entry point for client applications, simplifying the client-side experience and providing a layer of abstraction that can shield clients from the complexity of the underlying microservices network. Popular API Gateway solutions include NGINX, Amazon API Gateway, Apigee, and Kong, among others

## Circuit Breaker
- A circuit breaker is a software design pattern used in distributed systems and microservices architectures to enhance the stability and resilience of applications. It is inspired by the electrical circuit breaker, which stops the flow of electricity when there's a fault to prevent damage. In software, the circuit breaker serves a similar purpose, helping to manage and handle failures gracefully when interacting with remote services or components.

**The circuit breaker pattern consists of three primary states:**

- Closed State: In the closed state, the circuit breaker allows requests to pass through to the underlying service. It monitors the responses for errors or failures. If the error rate or failure rate remains below a certain threshold during a specified time period, the circuit breaker remains closed, indicating that the service is healthy.

- Open State: When the circuit breaker detects that the error rate or failure rate exceeds the defined threshold, it transitions to the open state. In this state, the circuit breaker prevents requests from reaching the underlying service. Instead, it immediately returns an error or fallback response to the caller. This helps to protect the service from further stress or overload when it's experiencing problems.

- Half-Open State: After a predefined period of time, the circuit breaker transitions from the open state to the half-open state. In this state, the circuit breaker allows a limited number of test requests to pass through to the underlying service. If these test requests succeed without errors, the circuit breaker transitions back to the closed state, indicating that the service has recovered. If any test requests fail, the circuit breaker remains open, allowing for additional testing in the future.

**Benefits of using the circuit breaker pattern:**

- Fault Tolerance: Circuit breakers help prevent cascading failures in distributed systems by isolating and protecting the healthy components from malfunctioning ones.

- Improved User Experience: Users receive quicker responses when a circuit breaker is open, which is typically better than waiting for requests to time out or fail.

- Efficient Resource Usage: It reduces unnecessary load on failing services and avoids resource exhaustion.

- Visibility and Monitoring: Circuit breakers often provide metrics and monitoring information, making it easier to detect and diagnose service issues.

- Popular libraries and frameworks, such as Netflix Hystrix (now in maintenance mode) and Resilience4j, offer implementations of the circuit breaker pattern in Java applications. These libraries provide a way to easily configure and use circuit breakers to handle failures in microservices architectures and other distributed systems.

## Questions/Doubts
1. How spring works internally?
2. How springboot application runs?
3. How annotations play and important role in springboot application?
4. What are embedded server?
5. what is Springboot Actuator?
6. What is Springboot devtools?
7. What is the use of static inner class?
8. 