package ch.heigvd.res.smtplab.model.prank;

import ch.heigvd.res.smtplab.config.ConfigParser;
import ch.heigvd.res.smtplab.model.mail.Group;
import ch.heigvd.res.smtplab.model.mail.Mail;
import ch.heigvd.res.smtplab.model.mail.Person;
import ch.heigvd.res.smtplab.smtp.SMTPClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Main pranker class
 *
 * @author Claude-André Alves, Luc Wachter
 */
public class Pranker {
    private String smtpServerAddress;
    private int smtpServerPort;

    private LinkedList<Group> groups;

    /**
     * Pranker constructor
     * @param config
     */
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

    /**
     *  Sends all mails setting the sender and the recipients
     * @param mails mails to be sent
     * @throws IOException
     */
    public void sendPranks(ArrayList<String> mails) throws IOException {
        LinkedList<Prank> pranks = new LinkedList<>();
        int mailIndex = 0;

        Collections.shuffle(mails);

        for (Group group : groups) {
            Prank prank = new Prank();
            Person sender = group.getMembers().pop();

            // Set sender
            prank.setVictimSender(sender);
            // Set recipients
            prank.addVictimRecipients(group.getMembers());
            // Set message
            prank.setMessage(mails.get(mailIndex));

            mailIndex = (mailIndex + 1) % mails.size();

            pranks.add(prank);
        }

        // Send the pranks through SMTP
        SMTPClient sender = new SMTPClient(smtpServerAddress, smtpServerPort);

        for (Prank prank : pranks) {
            sender.sendMail(prank.generateMailMessage());
        }
    }

    /**
     * Creates groups of victims to be prank
     * @param victims all persons to be split into groups
     * @param nbrOfGroups number of groups
     */
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
