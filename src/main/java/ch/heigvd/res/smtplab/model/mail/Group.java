package ch.heigvd.res.smtplab.model.mail;

import lombok.Getter;

import java.util.LinkedList;

public class Group {
    @Getter
    private LinkedList<Person> members = new LinkedList<Person>();

    public void addMember(Person p) {
        members.add(p);
    }
}
