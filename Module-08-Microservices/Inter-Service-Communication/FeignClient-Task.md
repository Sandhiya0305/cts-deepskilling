# Task Description

Create a Feign client for declarative HTTP calls between microservices. Add spring-cloud-starter-openfeign dependency. Use @EnableFeignClients on main class. Create @FeignClient(name=""order-service"", path=""/api/orders"") interface with methods matching order-service endpoints: getOrderById(@PathVariable Long id), getOrdersByUserId(@PathVariable Long userId). Autowire the Feign client in UserService to fetch user orders. Show how Feign integrates with Ribbon for load balancing and Hystrix for fallback.
