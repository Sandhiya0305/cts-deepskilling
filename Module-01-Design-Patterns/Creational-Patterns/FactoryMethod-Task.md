# Task Description

Implement the Factory Method pattern. Create a Notification interface with a send() method. Create concrete classes: EmailNotification, SMSNotification, PushNotification. Create a NotificationFactory class with a createNotification(String type) method that returns the appropriate concrete instance based on the input string. The client code should only depend on the Notification interface, never on concrete classes. Show how adding a new notification type requires only adding a new class and updating the factory, without touching client code.
