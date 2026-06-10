# Task Description

Implement security across microservices using JWT. Create an Auth Service that issues tokens. Other services validate tokens using a shared secret or public key. Configure Spring Security in each service to validate JWT from Authorization header. Use @PreAuthorize(""hasRole('ADMIN')"") for method-level security. Show how API Gateway can validate tokens centrally, reducing security logic in individual services. Include CORS configuration for cross-origin requests.
