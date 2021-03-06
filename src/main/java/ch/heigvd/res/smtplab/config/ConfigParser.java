package ch.heigvd.res.smtplab.config;

import ch.heigvd.res.smtplab.model.mail.Person;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Parser for the MailPranker's configuration
 *
 * @author Claude-André Alves, Luc Wachter
 */
@Getter
public class ConfigParser {
    // Prank configuration
    private String smtpServerAddress;
    private int smtpServerPort;
    private int nbrOfGroups;

    // Prank parameters
    private LinkedList<Person> victims;
    private ArrayList<String> mails;

    /**
     * Constructor that reads configs
     *
     * @param configPath The path to the main properties file
     * @throws IOException if the path isn't valid
     */
    public ConfigParser(String configPath) throws IOException {
        String pathToVictims;
        String pathToMails;

        // Parse properties config file
        try (BufferedReader config = new BufferedReader(new InputStreamReader(
                new FileInputStream(configPath), StandardCharsets.UTF_8))) {
            Properties prop = new Properties();

            // Load a properties file
            prop.load(config);

            // Store main parameters
            smtpServerAddress = prop.getProperty("smtpServerAddress");
            smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
            nbrOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));

            // Get paths to the victims fil and messages file
            pathToVictims = prop.getProperty("pathToVictims");
            pathToMails = prop.getProperty("pathToMails");
        }

        victims = loadVictimsList(pathToVictims);
        mails = loadMailsList(pathToMails);
    }

    /**
     * Read victims from file and fill victim list
     *
     * @param pathToVictims The path to the list of victims
     * @return a list of victims
     * @throws IOException if the path isn't valid
     */
    private LinkedList<Person> loadVictimsList(String pathToVictims) throws IOException {
        LinkedList<Person> result = new LinkedList<>();

        // Parse victims file
        try (BufferedReader victimFile = new BufferedReader(new InputStreamReader(
                new FileInputStream(pathToVictims), StandardCharsets.UTF_8))) {
            // Read every victim's mail
            while (victimFile.ready()) {
                result.add(new Person(victimFile.readLine()));
            }
        }

        return result;
    }

    /**
     * Read mail messages from file and fill mails list
     *
     * @param pathToMails The path to the list of messages
     * @return a list of mail messages
     * @throws IOException if the path isn't valid
     */
    private ArrayList<String> loadMailsList(String pathToMails) throws IOException {
        ArrayList<String> result = new ArrayList<>();

        // Parse messages file
        try (BufferedReader mailFile = new BufferedReader(new InputStreamReader(
                new FileInputStream(pathToMails), StandardCharsets.UTF_8))) {
            String line = mailFile.readLine();

            // Read until the end of the file
            while (line != null) {
                StringBuilder sb = new StringBuilder();
                // Read the whole message, until the separator
                while ((line != null) && (!line.equals("=="))) {
                    sb.append(line);
                    sb.append("\r\n");
                    line = mailFile.readLine();
                }

                result.add(sb.toString());
                line = mailFile.readLine();
            }
        }

        return result;
    }
}
