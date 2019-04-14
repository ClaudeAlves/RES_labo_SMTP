package ch.heigvd.res.smtplab.model.mail;

import java.util.LinkedList;

public class Group {
    private LinkedList<Person> members = new LinkedList<Person>();

    public void addMember(Person p) {
        members.add(p);
    }

    public LinkedList<Person> getMembers() {
        return members;
    }
}
