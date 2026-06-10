# Task Description

Create a Spring AOP aspect for cross-cutting logging concerns. Create @Aspect and @Component annotated class. Define pointcuts: @Pointcut(""execution(* com.example.service.*.*(..))"") for all service methods, @Pointcut(""@annotation(com.example.annotation.Loggable)"") for custom annotation. Implement advices: @Before to log method name and arguments, @AfterReturning to log return value, @Around to measure and log execution time in milliseconds, @AfterThrowing to log exceptions. Show how AOP separates logging from business logic without modifying service classes.
