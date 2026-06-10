# Task Description

Create a Java-based Spring configuration class using @Configuration. Define beans using @Bean annotation for: a DatabaseConnection (with URL, username, password properties), an EmailService (depends on DatabaseConnection), and a UserService (depends on EmailService). Demonstrate constructor injection by having one bean method accept another bean as parameter. Create a main method using AnnotationConfigApplicationContext to load the configuration and retrieve beans. Show that Spring manages object lifecycle and dependencies automatically.
