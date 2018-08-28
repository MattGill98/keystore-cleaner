package fish.payara.internal.tools.keystore;

import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names = {"--help", "help"}, description = "Shows the help options", help = true)
    public boolean help;

    @Parameter(names = {"--verbose", "-v"}, description = "Enables verbose logging")
    public boolean verbose = false;

    @Parameter(names = {"--target", "-t"}, description = "The target keystore", required = true)
    public String targetPath;

    @Parameter(names = {"--password", "-p"}, description = "The password for the target keystore", required= true, password = true)
    public String keystorePassword;

}