# Task Description

Implement circuit breaker using Resilience4j. Add spring-cloud-starter-circuitbreaker-resilience4j dependency. Configure circuit breaker in application.yml: failureRateThreshold, waitDurationInOpenState, slidingWindowSize. Annotate service method with @CircuitBreaker(name=""orderService"", fallbackMethod=""fallbackGetOrder""). Implement fallback method that returns cached data or default response. Show states: CLOSED (normal), OPEN (failing fast), HALF_OPEN (testing recovery). Demonstrate with a simulated failing service.
