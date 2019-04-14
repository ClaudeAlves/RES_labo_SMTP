package ch.heigvd.res.smtplab;

import ch.heigvd.res.smtplab.config.ConfigParser;
import ch.heigvd.res.smtplab.model.prank.Pranker;

import java.io.IOException;

/**
 * Main entry point for the Mail Pranker app
 *
 * @author Luc Wachter
 */
public class MailPranker {
    private static String PATH_TO_CONFIG = "src/main/resources/config.properties";

    public static void main(String[] args) throws IOException {
        // Parse configuration files
        ConfigParser cp = null;
        cp = new ConfigParser(PATH_TO_CONFIG);

        // Generate the prank
        Pranker p = new Pranker(cp);
        // And send the prank mails
        p.sendPranks(cp.getMails());
    }
}
