# Task Description

Configure Spring Boot Actuator for production monitoring. Add spring-boot-starter-actuator dependency. Expose endpoints in application.properties: management.endpoints.web.exposure.include=health,info,metrics,env,beans,loggers. Customize /actuator/info with build info and custom properties. Create a custom HealthIndicator that checks database connectivity. Show how to secure actuator endpoints with Spring Security. Explain which endpoints are safe for public exposure vs internal use.
