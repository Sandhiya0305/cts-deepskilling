# Task Description

Configure Spring Cloud Gateway as API Gateway. Define routes in application.yml or Java config: route id, uri (lb://service-name for load-balanced), predicates (Path=/api/users/**), filters (StripPrefix=1, AddRequestHeader, CircuitBreaker). Add global filters for logging request/response. Configure discovery locator to auto-create routes from Eureka-registered services. Show how the gateway acts as a single entry point, handles cross-cutting concerns, and routes to appropriate microservices.
