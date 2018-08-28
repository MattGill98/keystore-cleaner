package fish.payara.internal.tools.keystore;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CustomLogger {

    private final static Logger rootLogger = Logger.getLogger("");

    public static void configure(boolean verbose) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$s] %5$s\n");

        Level level = Level.INFO;
        if (verbose) {
            level = Level.FINEST;
        }

        rootLogger.setUseParentHandlers(false);
        rootLogger.setLevel(level);
        for (Handler h : rootLogger.getHandlers()) {
            h.setLevel(level);
            h.setFormatter(new SimpleFormatter());
        }
    }

}