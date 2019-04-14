package ch.heigvd.res.smtplab.model.mail;

import lombok.Getter;

@Getter
public class Person {
    private String address;
    private String name;
    private String surname;

    public Person(String adresse, String name, String surname) {
        this.address = adresse;
        this.name = name;
        this.surname = surname;
    }
}
