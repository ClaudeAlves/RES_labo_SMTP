package ch.heigvd.res.smtplab.model.mail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mail {
    private String from;
    private String[] to = new String[0];
    private String[] cc = new String[0];
    private String[] bcc = new String[0];
    private String subject;
    private String body;
}
