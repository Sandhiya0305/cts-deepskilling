# Task Description

Implement the Proxy pattern. Create an Image interface with display() method. Create a RealImage class that loads a high-resolution image from disk (simulate with a sleep delay). Create a ProxyImage class that holds a reference to RealImage but only creates it when display() is first called (lazy initialization). Include a cache mechanism so subsequent display() calls use the already-loaded image. Show how proxy controls access, reduces memory footprint, and delays expensive operations.
