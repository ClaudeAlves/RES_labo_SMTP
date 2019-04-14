package ch.heigvd.res.smtplab.model.prank;

import ch.heigvd.res.smtplab.model.mail.Mail;
import ch.heigvd.res.smtplab.model.mail.Person;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Prank {
    private Person victimSender;
    private LinkedList<Person> victimRecipients = new LinkedList<>();
    private LinkedList<Person> witnessRecipients = new LinkedList<>();
    private String message;

    public Person getVictimSender() {
        return victimSender;
    }

    public LinkedList<Person> getVictimRecipients() {
        return victimRecipients;
    }

    public LinkedList<Person> getWitnessRecipients() {
        return witnessRecipients;
    }

    public String getMessage() {
        return message;
    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public void addVictimRecipients(LinkedList<Person> victimRecipients) {
        this.victimRecipients.addAll(victimRecipients);
    }

    public void addWitnessRecipients(LinkedList<Person> witnessRecipients) {
        this.witnessRecipients.addAll(witnessRecipients);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Mail generateMailMessage() {
        Mail mail = new Mail();
        mail.setBody(this.message + "\r\n" + victimSender.getSurname());

        String[] to = victimRecipients
                .stream()
                .map(p -> p.getAddress())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        mail.setTo(to);

        String[] cc = witnessRecipients
                .stream()
                .map(p -> p.getAddress())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        mail.setCc(cc);

        mail.setFrom(victimSender.getAddress());

        return mail;
    }
}
