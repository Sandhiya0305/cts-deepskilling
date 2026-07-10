# Exercise 02 Microservices Plan

## Goal
Implement Exercise 02 - microservices with an API gateway.

## Scope Inferred From Materials
The available module file names indicate this exercise is centered on the API gateway microservices lab.

## Implementation Plan

### 1. Gateway Project Setup
- Create the API gateway service project structure.
- Define consistent package names and configuration files.
- Add module-level notes for ports, service names, and startup order.

### 2. Backend Service Targets
- Identify the backend microservices exposed through the gateway.
- Confirm each service has a stable base path and health endpoint.
- Document the request paths that the gateway must route.

### 3. Route Configuration
- Configure path-based routes in the gateway.
- Map each external route to the correct backend service.
- Add route predicates and filters only where required by the exercise.

### 4. Gateway Behavior Validation
- Verify requests flow through the gateway to the target services.
- Test success, 4xx, and 5xx responses.
- Confirm response payloads match the backend services.

### 5. Error Handling and Stability
- Define how the gateway should behave when a backend service is unavailable.
- Capture fallback or error response expectations if the exercise requires them.
- Check logging so routed requests can be traced end to end.

### 6. Verification
- Run the gateway and backend services together.
- Test routes with browser, Postman, or curl.
- Record sample request URLs and expected responses.

## Done Criteria
- The API gateway starts successfully.
- All required routes forward to the correct backend services.
- Error handling behaves as expected.
- Verification steps are documented in the module folder.
