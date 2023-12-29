package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;


public class Person {

    private String phone;
    private String name;
    private int id;
    private int age;

    Person(@NotNull String name, @Min(0)int id, @Min(1)@Max(100)int age, String phone) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.phone = phone;
    }

    Person(@NotNull String name, @Min(0)int id, @Min(1)@Max(100)int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    static Person create(@NotNull String name, int id, @Min(1)@Max(100)int age, String phone) {
        return new Person(name, id, age, phone);
    }

    public String toString() {
        return "name: " + name + "\nid: " + id + "\nage: " + age + "\nphone: " + phone;
    }

    public String name() {
        return this.name;
    }

    public String phone() {
        return this.name;
    }

    public int id() {
        return this.id;
    }

    public int age() {
        return this.age;
    }
}
