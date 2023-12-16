package edu.hw7.task3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import static edu.hw7.task3.PersonDataBase.isCorrectValues;

public final class ReadWriteLockCache implements PersonDataBase {
    Map<Integer, Person> idMap = new HashMap<>();
    Map<String, List<Person>> nameMap = new HashMap<>();
    Map<String, List<Person>> addressMap = new HashMap<>();
    Map<String, List<Person>> phoneNumberMap = new HashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();

        try {
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
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();

        try {
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
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();

        try {
            return nameMap.getOrDefault(name, List.of());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();

        try {
            return addressMap.getOrDefault(address, List.of());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phoneNumber) {
        lock.readLock().lock();

        try {
            return phoneNumberMap.getOrDefault(phoneNumber, List.of());
        } finally {
            lock.readLock().unlock();
        }
    }
}
