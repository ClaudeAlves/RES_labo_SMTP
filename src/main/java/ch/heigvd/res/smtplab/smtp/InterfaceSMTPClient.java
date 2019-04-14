package ch.heigvd.res.smtplab.smtp;
import ch.heigvd.res.smtplab.model.mail.Mail;

import java.io.IOException;

public interface InterfaceSMTPClient {
    public void sendMessage(Mail mail) throws IOException;
}
