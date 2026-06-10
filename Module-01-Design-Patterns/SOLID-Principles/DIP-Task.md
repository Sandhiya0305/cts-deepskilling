# Task Description

Implement the Dependency Inversion Principle (DIP). Create a Notification system. In the BEFORE version, the Notification class directly instantiates EmailService (hard dependency). In the AFTER version, create a MessageService interface. Inject EmailService or SMSService through constructor. Show that the high-level Notification module depends on abstraction, not concrete implementations. Add comments on testability benefits.
