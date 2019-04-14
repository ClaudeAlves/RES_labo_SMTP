package ch.heigvd.res.smtplab.smtp;

import ch.heigvd.res.smtplab.model.mail.Mail;

import java.io.IOException;

/**
 * SMTPClient helps connecting to a
 */
public class SMTPClient implements InterfaceSMTPClient{
    private String address;
    private int port;

    /**
     * SMTPClient constructor
     * @param address SMTP server address
     * @param port SMTP server port
     */
    public SMTPClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    /**
     * Sends mail with the SMTP structure
     * @param mail mail to be sent
     * @throws IOException
     */
    @Override
    public void sendMessage(Mail mail) throws IOException {
        System.out.println("FROM: " + mail.getFrom());
        for (String to : mail.getTo()) {
            System.out.println("TO: " + to);
        }
        System.out.println("SUBJECT: " + mail.getSubject());
        System.out.println("MESSAGE: " + mail.getBody());
    }
}
