package edu.hw07.task3;

import java.util.List;

public interface PersonDataBase {
    void add(Person person);

    void delete(int id);

    List<Person> findByName(String name);

    List<Person> findByAddress(String address);

    List<Person> findByPhone(String phone);

    static boolean isCorrectValue(String value) {
        return value != null && !value.isEmpty();
    }

    static boolean isCorrectValues(Person person) {
        return isCorrectValue(person.name())
            && isCorrectValue(person.address())
            && isCorrectValue(person.phoneNumber());
    }
}
