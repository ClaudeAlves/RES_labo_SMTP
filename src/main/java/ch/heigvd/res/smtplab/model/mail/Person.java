package ch.heigvd.res.smtplab.model.mail;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Person {
    private String address;
    private String name;
    private String surname;

    public Person(String adress) {
        this.address = adress;

        Pattern pattern = Pattern.compile("(.*)\\.(.*)@");
        Matcher matcher = pattern.matcher(address);
        boolean found = matcher.find();

        if (found) {
            this.name = matcher.group(1);
            this.surname = matcher.group(2);
        }
    }
}
