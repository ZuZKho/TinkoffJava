package edu.hw3.task5;

import java.util.Objects;

public class Person {

    private final String firstName;
    private final String lastName;

    public Person(String string) {
        if (string.contains(" ")) {
            firstName = string.split(" ")[0];
            lastName = string.split(" ")[1];
        } else {
            firstName = string;
            lastName = null;
        }
    }

    public String getValue() {
        if (lastName == null) {
            return firstName;
        }
        return lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person second)) {
            return false;
        }

        if (this.lastName == null) {
            if (second.lastName == null) {
                return this.firstName.equals(second.firstName);
            }
            return false;
        }
        return this.firstName.equals(second.firstName) && this.lastName.equals(second.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
