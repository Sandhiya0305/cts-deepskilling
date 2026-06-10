# Task Description

Implement the Observer pattern. Create a WeatherStation (Subject) class that maintains temperature, humidity, and pressure data. Create an Observer interface with update() method. Create concrete observers: PhoneDisplay, TVDisplay, WebDisplay that print weather data in different formats. The WeatherStation should maintain a list of observers and notify all of them when data changes. Demonstrate registering multiple observers, updating weather data, and all observers receiving the update automatically. Show how to unregister an observer.
