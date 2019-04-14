package ch.heigvd.res.smtplab.model.mail;

import lombok.Getter;
import lombok.Setter;

/**
 * SMTP structured mail
 */
@Getter
@Setter
public class Mail {
    private String from;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String body;
}
