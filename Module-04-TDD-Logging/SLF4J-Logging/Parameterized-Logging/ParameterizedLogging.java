import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLogging {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLogging.class);

    public static void main(String[] args) {
        String user = "Alice";
        int age = 30;
        double balance = 15000.50;

        logger.info("User {} is {} years old", user, age);
        logger.info("User {} has balance ${}", user, balance);
        logger.warn("User {} attempted invalid transaction. Amount: ${}", user, 5000);
        logger.error("Failed to process payment for user {}. Error code: {}", user, 404);
    }
}