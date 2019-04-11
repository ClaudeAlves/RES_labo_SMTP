package ch.heigvd.res.smtplab.config;

import ch.heigvd.res.smtplab.model.mail.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Parser for the MailPranker's configuration
 *
 * @author Luc Wachter
 */
public class ConfigParser {
    // Prank configuration
    private String smtpServerAddress;
    private int smtpServerPort;
    private int nbrOfGroups;

    // Prank parameters
    private LinkedList<Person> victims;
    private LinkedList<Group> groups;
    private LinkedList<Mail> mails;

    /**
     * Constructor that reads configs
     *
     * @param configPath The path to the main properties file
     */
    public ConfigParser(String configPath) {
        String pathToVictims;
        String pathToMails;

        try (InputStream config = new FileInputStream(configPath)) {
            Properties prop = new Properties();

            // Load a properties file
            prop.load(config);

            // Store main parameters
            smtpServerAddress = prop.getProperty("smtpServerAddress");
            smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
            nbrOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups")); // TODO Test if the number is valid

            pathToVictims = prop.getProperty("pathToVictims");
            pathToMails = prop.getProperty("pathToMails");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
