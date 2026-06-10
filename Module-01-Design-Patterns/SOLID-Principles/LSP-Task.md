# Task Description

Implement the Liskov Substitution Principle (LSP). Create a Bird hierarchy. In the BEFORE version, have Penguin extend Bird with a fly() method that throws an exception or does nothing (breaking substitution). In the AFTER version, create Flyable and NonFlyable interfaces. Make Sparrow implement Flyable, Penguin implement NonFlyable. Show that any subclass can substitute its parent without breaking client code. Add comments on contract adherence.
