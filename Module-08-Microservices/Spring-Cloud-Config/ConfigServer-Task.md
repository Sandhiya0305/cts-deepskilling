# Task Description

Create a Spring Cloud Config Server for centralized configuration. Use @EnableConfigServer. Configure Git backend in application.yml: spring.cloud.config.server.git.uri=https://github.com/yourrepo/config-repo. Create application-dev.yml and application-prod.yml in the config repo with different database URLs and logging levels. Client services use bootstrap.yml with spring.cloud.config.uri and spring.application.name. Show @RefreshScope for dynamic configuration updates without restart via /actuator/refresh endpoint.
