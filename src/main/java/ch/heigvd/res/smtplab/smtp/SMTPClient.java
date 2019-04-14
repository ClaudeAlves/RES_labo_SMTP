package ch.heigvd.res.smtplab.smtp;

import ch.heigvd.res.smtplab.model.mail.Mail;

import java.io.IOException;

public class SMTPClient implements InterfaceSMTPClient{
    private String address;
    private int port;

    public SMTPClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

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
