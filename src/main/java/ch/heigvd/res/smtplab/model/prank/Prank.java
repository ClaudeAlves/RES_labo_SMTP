package ch.heigvd.res.smtplab.model.prank;

import ch.heigvd.res.smtplab.model.mail.Mail;
import ch.heigvd.res.smtplab.model.mail.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Representation of a mail prank
 *
 * @author Claude-André Alves, Luc Wachter
 */
@Getter
public class Prank {
    @Setter
    private Person victimSender;

    @Setter
    private String message;

    private LinkedList<Person> victimRecipients = new LinkedList<>();

    /**
     * Add a list of recipient victim
     * @param victimRecipients list of persons to be add to the prank
     */
    public void addVictimRecipients(LinkedList<Person> victimRecipients) {
        this.victimRecipients.addAll(victimRecipients);
    }

    /**
     * generates a pranked mail ready to be sent
     * @return the prank mail
     */
    public Mail generateMailMessage() {
        Mail mail = new Mail();
        mail.setBody(this.message + "\r\n" + victimSender.getName() + " " + victimSender.getSurname());

        String[] to = victimRecipients
                .stream()
                .map(p -> p.getAddress())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        mail.setTo(to);

        mail.setFrom(victimSender.getAddress());

        return mail;
    }
}
