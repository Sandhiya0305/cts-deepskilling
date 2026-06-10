# Task Description

Create a docker-compose.yml for a microservices stack. Services: app (Spring Boot, build from Dockerfile, ports 8080:8080, depends_on db, environment variables for DB connection), db (MySQL 8, environment MYSQL_ROOT_PASSWORD, MYSQL_DATABASE, volumes for data persistence), nginx (reverse proxy, ports 80:80, volumes for nginx.conf), redis (caching, ports 6379:6379). Configure networks (bridge) for service communication. Add health checks for database readiness. Show commands: docker-compose up -d, docker-compose logs -f, docker-compose down -v.
