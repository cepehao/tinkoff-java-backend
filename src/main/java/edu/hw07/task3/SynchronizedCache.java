package edu.hw07.task3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static edu.hw07.task3.PersonDataBase.isCorrectValues;

public final class SynchronizedCache implements PersonDataBase {
    Map<Integer, Person> idMap = new HashMap<>();
    Map<String, List<Person>> nameMap = new HashMap<>();
    Map<String, List<Person>> addressMap = new HashMap<>();
    Map<String, List<Person>> phoneNumberMap = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        var id = person.id();
        var name = person.name();
        var address = person.address();
        var phoneNumber = person.phoneNumber();

        delete(id);

        idMap.put(id, person);

        if (isCorrectValues(person)) {
            if (!nameMap.containsKey(name)) {
                nameMap.put(name, new LinkedList<>());
            }
            nameMap.get(name).add(person);

            if (!addressMap.containsKey(address)) {
                addressMap.put(address, new LinkedList<>());
            }
            addressMap.get(address).add(person);

            if (!phoneNumberMap.containsKey(phoneNumber)) {
                phoneNumberMap.put(phoneNumber, new LinkedList<>());
            }
            phoneNumberMap.get(phoneNumber).add(person);
        }
    }

    @Override
    public synchronized void delete(int id) {
        var person = idMap.getOrDefault(id, null);

        if (person == null) {
            return;
        }

        idMap.remove(id);

        var name = person.name();
        var address = person.address();
        var phoneNumber = person.phoneNumber();

        if (isCorrectValues(person)) {
            nameMap.get(name).remove(person);
            addressMap.get(address).remove(person);
            phoneNumberMap.get(phoneNumber).remove(person);
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return nameMap.getOrDefault(name, List.of());
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return addressMap.getOrDefault(address, List.of());
    }

    @Override
    public synchronized List<Person> findByPhone(String phoneNumber) {
        return phoneNumberMap.getOrDefault(phoneNumber, List.of());
    }
}
