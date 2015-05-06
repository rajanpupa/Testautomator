package tutorials;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Rajan Prasad Upadhyay
 */
public class LogTest {

    static Logger log = LogManager.getLogger(LogTest.class.getName());

    public static void main(String[] args) {
        log.trace("Trace Message!");
        log.debug("Debug Message!");
        log.info("Info Message!");
        log.warn("Warn Message!");
        log.error("Error Message!");
        log.fatal("Fatal Message!");
    }
}
