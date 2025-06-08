## spring-feign-demo project

This is an example project which has a Spring Boot REST API and a Spring-Feign client.
Both the client and server share code through commons module.

The directory structure is as 
```
-/
  - client
  - commons
  - src (server / backend)
```

### Why Feign?
Feign helps write a declarative HTTP client without manually writing HTTP calls and parsing responses.
Spring Cloud Feign is a wrapper over OpenFeign that helps us use Spring MVC contracts and ResponseEntity parsers which are not available in OpenFeign.
This helps in much easier code-sharing between the backend and client.

Code sharing helps maintain API contract and ensure that the client is maintained in the same repo as the server.