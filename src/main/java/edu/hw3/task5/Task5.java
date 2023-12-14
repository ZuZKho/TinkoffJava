package edu.hw3.task5;

import java.util.ArrayList;
import java.util.List;

public class Task5 {

    private Task5() {
    }


    public static Person[] parseContacts(String[] contacts, String modifier) {
        if (contacts == null) {
            return new Person[] {};
        }
        List<Person> list = new ArrayList<>();
        for (String contact : contacts) {
            list.add(new Person(contact));
        }

        if (modifier.equals("ASC")) {
            list.sort((p1, p2) -> p1.getValue().compareTo(p2.getValue()));
        } else {
            list.sort((p2, p1) -> p1.getValue().compareTo(p2.getValue()));
        }

        return list.toArray(new Person[contacts.length]);
    }
}
