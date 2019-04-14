package ch.heigvd.res.smtplab.model.mail;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Person class for SMTP usage only name/surname and address
 */
@Getter
public class Person {
    private String address;
    private String name;
    private String surname;

    /**
     * Person constructor based on his address
     * @param address
     */
    public Person(String address) {
        this.address = address;

        Pattern pattern = Pattern.compile("(.*)\\.(.*)@");
        Matcher matcher = pattern.matcher(this.address);
        boolean found = matcher.find();

        if (found) {
            name = matcher.group(1);
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            surname = matcher.group(2);
            surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
        }
    }
}
