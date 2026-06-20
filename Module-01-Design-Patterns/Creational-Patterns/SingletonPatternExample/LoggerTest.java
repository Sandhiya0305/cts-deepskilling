
public class LoggerTest {
    public static void main(String[] args) {
        LoggerDemo logger1 = LoggerDemo.getInstance();
        LoggerDemo logger2 = LoggerDemo.getInstance();

        logger1.log("This is a log message from logger1.");
        logger2.log("This is a log message from logger2.");
        System.out.println("Logger1 hash code: " + logger1.hashCode());
        System.out.println("Logger2 hash code: " + logger2.hashCode());
    }
}

//output:
// Logger instance created.
// Log: This is a log message from logger1.
// Log: This is a log message from logger2.
// Logger1 hash code: 1450495309
// Logger2 hash code: 1450495309