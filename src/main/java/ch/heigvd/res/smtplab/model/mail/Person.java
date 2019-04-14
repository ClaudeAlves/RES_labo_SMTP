package ch.heigvd.res.smtplab.model.mail;

public class Person {
    private String adresse;
    private String name;
    private String surname;

    public Person(String adresse, String name, String surname) {
        this.adresse = adresse;
        this.name = name;
        this.surname = surname;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
