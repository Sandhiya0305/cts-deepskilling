import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Appender {
    private static final Logger logger = LoggerFactory.getLogger(Appender.class);

    public static void main(String[] args) {
        logger.debug("Debug message to console and file");
        logger.info("Info message to console and file");
        logger.warn("Warning message to console and file");
        logger.error("Error message to console and file");
    }
}