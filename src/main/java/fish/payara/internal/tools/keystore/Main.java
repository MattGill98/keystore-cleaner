package fish.payara.internal.tools.keystore;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

class Main {

    private Logger logger = Logger.getLogger(Main.class.getName());

    public void run(JCommander commander, Args args) {
        if (args.help || args.targetPath == null) {
            commander.usage();
        }
        CustomLogger.configure(args.verbose);
        File keyStoreFile = new File(args.targetPath);
        KeystoreManager storeManager;
        try {
            storeManager = new KeystoreManager(keyStoreFile, args.keystorePassword);
        } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.SEVERE, "The algorithm used for checking the integrity of the keystore cannot be found.",
                    ex);
            return;
        } catch (CertificateException ex) {
            logger.log(Level.SEVERE, "Could not load certificate from keystore.", ex);
            return;
        } catch (KeyStoreException ex) {
            logger.log(Level.SEVERE, "Could not create copy of keystore.", ex);
            return;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Incorrect password provided.", ex);
            return;
        } catch (IllegalArgumentException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            return;
        }

        try {
            storeManager.filterExpiredKeys();
        } catch (KeyStoreException ex) {
            logger.log(Level.SEVERE, "Error removing certificate from the store.", ex);
            return;
        }

        try {
            storeManager.save();
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException ex) {
            logger.log(Level.SEVERE, "Unexpected error writing back to keystore.", ex);
        }
    }

    public void removeExpiredCertificates(KeyStore store) {

    }

    public static void main(String[] args) {
        Args parameters = new Args();
        JCommander commander = JCommander.newBuilder().addObject(parameters).build();
        try {
            commander.parse(args);
        } catch (ParameterException ex) {
            System.out.println(ex.getMessage());
            commander.usage();
            System.exit(1);
        }
        new Main().run(commander, parameters);
    }

}