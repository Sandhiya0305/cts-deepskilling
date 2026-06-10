# Task Description

Create a microservice that registers itself with Eureka. Use @EnableDiscoveryClient. Configure application.yml with spring.application.name=user-service, eureka.client.serviceUrl.defaultZone. Create a REST endpoint GET /users returning a list. The service should automatically register on startup and deregister on shutdown. Show how multiple instances of the same service register with unique instance IDs. Test by checking Eureka dashboard after startup.
