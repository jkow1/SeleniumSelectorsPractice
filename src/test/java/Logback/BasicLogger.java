package Logback;

import org.slf4j.Logger;

public class BasicLogger {

    public static void logAssertedValues(Logger logger, String expectedResult, String actualResult) {
        logger.info("Assertion of expected result : \"{}\" and actual result \"{}\" ", expectedResult, actualResult);
    }

    public static void logMethod(Logger logger, String method) {
        logger.debug("Execution of method: {}", method);
    }

}
