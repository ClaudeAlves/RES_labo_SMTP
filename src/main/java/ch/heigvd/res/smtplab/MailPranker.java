package ch.heigvd.res.smtplab;

import ch.heigvd.res.smtplab.config.ConfigParser;
import ch.heigvd.res.smtplab.model.prank.Pranker;

import java.io.IOException;

/**
 * Main entry point for the Mail Pranker app
 *
 * @author Claude-André Alves, Luc Wachter
 */
public class MailPranker {
    public static void main(String[] args) throws IOException {
        String configPath = "";

        // If main config is passed as parameter, use this
        if (args.length == 1) {
            configPath = args[0];
        } else {
            System.out.println("Usage: java -jar program.jar path/to/main/config/file");
            System.exit(1);
        }

        // Parse configuration files
        ConfigParser cp = null;
        cp = new ConfigParser(configPath);

        // Generate the prank
        Pranker p = new Pranker(cp);
        // And send the prank mails
        p.sendPranks(cp.getMails());
    }
}
