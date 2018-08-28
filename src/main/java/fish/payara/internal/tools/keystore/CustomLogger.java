package fish.payara.internal.tools.keystore;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CustomLogger {

    private final static Logger rootLogger = Logger.getLogger("uk.me.mattgill");

    public static void configure(boolean verbose) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$s] %5$s\n");

        Level level = Level.INFO;
        if (verbose) {
            level = Level.FINEST;
        }

        rootLogger.setUseParentHandlers(false);
        final ConsoleHandler handler = new ConsoleHandler();
        rootLogger.setLevel(level);
        handler.setLevel(level);
        handler.setFormatter(new SimpleFormatter());
        rootLogger.addHandler(handler);
    }

}