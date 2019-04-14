package ch.heigvd.res.smtplab.model.prank;

import ch.heigvd.res.smtplab.config.ConfigParser;
import ch.heigvd.res.smtplab.model.mail.Group;
import ch.heigvd.res.smtplab.model.mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Main pranker class
 *
 * @author Claude-André Alves, Luc Wachter
 */
public class Pranker {
    String smtpServerAddress;
    int smtpServerPort;

    LinkedList<Group> groups;

    public Pranker(ConfigParser config) {
        smtpServerAddress = config.getSmtpServerAddress();
        smtpServerPort = config.getSmtpServerPort();

        // Check number of victims
        if (config.getVictims().size() / config.getNbrOfGroups() < 3) {
            throw new RuntimeException("Number of victims should be higher for that number of groups to work.");
        }

        // Generate groups
        createGroups(config.getVictims(), config.getNbrOfGroups());
    }

    public void sendMails(ArrayList<String> mails) {
        int mailIndex = 0;

        for (Group group : groups) {
            Prank prank = new Prank();
            Person sender = group.getMembers().pop();

            prank.setVictimSender(sender);
            prank.setMessage(mails.get(mailIndex));

            mailIndex = (mailIndex + 1) % mails.size();
        }
    }

    public void createGroups(LinkedList<Person> victims, int nbrOfGroups) {
        int nbrOfVictimsPerGroup = victims.size() / nbrOfGroups;
        groups = new LinkedList<>();

        // Shuffle the people, so that the groups are random
        Collections.shuffle(victims);

        // Build groups
        for (int i = 0; i < nbrOfGroups; i++) {
            Group group = new Group();

            for (int j = 0; j < nbrOfVictimsPerGroup; j++) {
                group.addMember(victims.pop());
            }

            groups.add(group);
        }

        // Add the last person, if the number of victims is odd
        if (!victims.isEmpty()) {
            groups.getLast().addMember(victims.pop());
        }
    }
}
