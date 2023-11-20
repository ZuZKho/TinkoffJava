package edu.hw7.task3;

import java.util.HashMap;
import org.jetbrains.annotations.Nullable;

public class SynchronizedPersonDatabase implements PersonDatabaseInterface {

    public void add(Person person) {
        synchronized (database) {
            database.personById.put(person.id(), person);
            database.personByName.put(person.name(), person);
            database.personByAddress.put(person.address(), person);
            database.personByPhone.put(person.phoneNumber(), person);
        }
    }

    public void delete(int id) {
        synchronized (database) {
            Person person = database.personById.get(id);

            database.personById.remove(id);
            database.personByName.remove(person.name(), person);
            database.personByAddress.remove(person.address(), person);
            database.personByPhone.remove(person.phoneNumber(), person);
        }
    }

    @Nullable
    public Person findByName(String name) {
        synchronized (database) {
            return database.personByName.get(name);
        }
    }

    @Nullable
    public Person findByAddress(String address) {
        synchronized (database) {
            return database.personByAddress.get(address);
        }
    }

    @Nullable
    public Person findByPhone(String phone) {
        synchronized (database) {
            return database.personByPhone.get(phone);
        }
    }

    private final Database database = new Database();

    private class Database {
        HashMap<Integer, Person> personById = new HashMap<>();
        HashMap<String, Person> personByName = new HashMap<>();
        HashMap<String, Person> personByAddress = new HashMap<>();
        HashMap<String, Person> personByPhone = new HashMap<>();
    }

}
