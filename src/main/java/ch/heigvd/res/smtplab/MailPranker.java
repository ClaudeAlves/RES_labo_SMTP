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

    public static void main(String[] args) {
        // Parse configuration files
        ConfigParser cp = null;
        try {
            cp = new ConfigParser(PATH_TO_CONFIG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // And generate the prank
        Pranker p = new Pranker(cp);
        // And send the prank mails
        p.sendMails(cp.getMails());
    }
}
