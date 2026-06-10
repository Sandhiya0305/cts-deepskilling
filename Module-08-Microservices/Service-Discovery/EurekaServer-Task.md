# Task Description

Create a Spring Boot Eureka Server for service registration and discovery. Use @EnableEurekaServer on main application class. Configure application.yml: server.port=8761, eureka.client.registerWithEureka=false, eureka.client.fetchRegistry=false, eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka. Explain Eureka'\''s role: services register on startup, heartbeat to stay alive, clients query for service locations. Show Eureka dashboard at http://localhost:8761.
