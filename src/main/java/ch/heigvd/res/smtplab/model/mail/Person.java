package ch.heigvd.res.smtplab.model.mail;

public class Person {
    private String address;
    private String name;
    private String surname;

    public Person(String adresse, String name, String surname) {
        this.address = adresse;
        this.name = name;
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
