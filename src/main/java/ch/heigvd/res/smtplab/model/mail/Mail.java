package ch.heigvd.res.smtplab.model.mail;

import lombok.Getter;
import lombok.Setter;

/**
 * SMTP structured mail
 *
 * @author Claude-André Alves, Luc Wachter
 */
@Getter
@Setter
public class Mail {
    private String from;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject = "Important email";
    private String body;
}
