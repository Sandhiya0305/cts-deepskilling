# Task Description

Implement the Builder pattern. Create a Computer class with multiple optional fields: RAM, HDD, SSD, graphicsCard, bluetoothEnabled, wifiEnabled. Instead of telescoping constructors, create a ComputerBuilder inner static class with methods for each field that return the builder instance (fluent API). Include a build() method that returns the final Computer object. Demonstrate building two different configurations: a gaming PC (high RAM, dedicated graphics, SSD) and an office PC (basic specs). Show how Builder eliminates constructor parameter confusion.
