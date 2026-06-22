# Task Description

Implement the Factory Method pattern. Create a Notification interface with a send() method. Create concrete classes: EmailNotification, SMSNotification, PushNotification. Create a NotificationFactory class with a createNotification(String type) method that returns the appropriate concrete instance based on the input string. The client code should only depend on the Notification interface, never on concrete classes. Show how adding a new notification type requires only adding a new class and updating the factory, without touching client code.

Exercise 2: Implementing the Factory Method Pattern
Scenario: 
You are developing a document management system that needs to create different types of documents (e.g., Word, PDF, Excel). Use the Factory Method Pattern to achieve this.
Steps:
1.	Create a New Java Project:
o	Create a new Java project named FactoryMethodPatternExample.
2.	Define Document Classes:
o	Create interfaces or abstract classes for different document types such as WordDocument, PdfDocument, and ExcelDocument.
3.	Create Concrete Document Classes:
o	Implement concrete classes for each document type that implements or extends the above interfaces or abstract classes.
4.	Implement the Factory Method:
o	Create an abstract class DocumentFactory with a method createDocument().
o	Create concrete factory classes for each document type that extends DocumentFactory and implements the createDocument() method.
5.	Test the Factory Method Implementation:
o	Create a test class to demonstrate the creation of different document types using the factory method.
