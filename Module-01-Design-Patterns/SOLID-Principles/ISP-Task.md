# Task Description

Implement the Interface Segregation Principle (ISP). Create a Worker interface with methods: work(), eat(), sleep(). In the BEFORE version, a Robot class implements Worker but is forced to provide dummy implementations for eat() and sleep(). In the AFTER version, split into Workable, Eatable, and Sleepable interfaces. Human implements all three, Robot implements only Workable. Show how ISP prevents fat interfaces and reduces coupling.
