package ch.heigvd.res.smtplab.model.mail;

import lombok.Getter;

import java.util.LinkedList;

/**
 * Class representing a group of persons
 */
public class Group {
    @Getter
    private LinkedList<Person> members = new LinkedList<Person>();

    /**
     * Add a person in the group
     *
     * @param p person to add
     */
    public void addMember(Person p) {
        members.add(p);
    }
}
