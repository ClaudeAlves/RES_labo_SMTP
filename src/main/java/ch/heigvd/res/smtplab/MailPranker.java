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
    public static void main(String[] args) {
        // Parse configuration files
        ConfigParser cp = null;
        try {
            cp = new ConfigParser("src/main/resources/config.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // And generate prank
        Pranker mp = new Pranker(cp);
    }
}
