# Task Description

Implement the Singleton design pattern. Create a DatabaseConnection class that ensures only one instance exists across the entire application. Implement thread-safe version using either double-checked locking with volatile keyword, or Bill Pugh singleton (static inner class), or enum-based singleton. Show why lazy initialization matters and how to handle multi-threaded access. Include a main method demonstrating that multiple getInstance() calls return the same object.

Exercise 1: Implementing the Singleton Pattern
Scenario: 
You need to ensure that a logging utility class in your application has only one instance throughout the application lifecycle to ensure consistent logging.
Steps:
1.	Create a New Java Project:
o	Create a new Java project named SingletonPatternExample.
2.	Define a Singleton Class:
o	Create a class named Logger that has a private static instance of itself.
o	Ensure the constructor of Logger is private.
o	Provide a public static method to get the instance of the Logger class.
3.	Implement the Singleton Pattern:
o	Write code to ensure that the Logger class follows the Singleton design pattern.
4.	Test the Singleton Implementation:
o	Create a test class to verify that only one instance of Logger is created and used across the application.
