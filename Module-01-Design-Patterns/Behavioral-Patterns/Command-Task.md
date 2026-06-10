# Task Description

Implement the Command pattern. Create a Light class with on() and off() methods. Create a Command interface with execute() and undo() methods. Create LightOnCommand and LightOffCommand that wrap the Light object. Create a RemoteControl invoker that holds a Command and can pressButton() to execute it. Add a command history stack so you can undo the last command. Demonstrate turning light on, turning light off, and undoing both operations. Show how commands encapsulate requests as objects, enabling queuing and logging.
