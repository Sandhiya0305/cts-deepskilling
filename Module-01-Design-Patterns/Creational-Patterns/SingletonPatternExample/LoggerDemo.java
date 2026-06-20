
public class LoggerDemo {

    private static LoggerDemo instance = new LoggerDemo();

    private LoggerDemo() {
        System.out.println("Logger instance created.");
    }

    public static LoggerDemo getInstance() {
        return instance;
    }

    public void log(String message) {
        System.out.println("Log: " + message);
    }
}