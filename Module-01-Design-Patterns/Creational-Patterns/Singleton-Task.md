# Task Description

Implement the Singleton design pattern. Create a DatabaseConnection class that ensures only one instance exists across the entire application. Implement thread-safe version using either double-checked locking with volatile keyword, or Bill Pugh singleton (static inner class), or enum-based singleton. Show why lazy initialization matters and how to handle multi-threaded access. Include a main method demonstrating that multiple getInstance() calls return the same object.
