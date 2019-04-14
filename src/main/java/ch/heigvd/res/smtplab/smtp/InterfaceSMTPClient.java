package ch.heigvd.res.smtplab.smtp;
import ch.heigvd.res.smtplab.model.mail.Mail;

import java.io.IOException;

/**
 * SMTPClient interface
 */
public interface InterfaceSMTPClient {
    /**
     * Sends a message with SMTP structure
     * @param mail mail to be sent
     * @throws IOException
     */
    void sendMessage(Mail mail) throws IOException;
}
