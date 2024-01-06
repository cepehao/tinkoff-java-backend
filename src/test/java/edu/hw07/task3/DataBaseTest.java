package edu.hw07.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DataBaseTest {
    private final static Logger LOGGER = LogManager.getLogger();

    private List<Person> personList;

    @BeforeEach
    @SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
    void setUp() {
        personList = new ArrayList<>();
        personList.add(new Person(1, "Person1", "Street1", "Phone1"));
        personList.add(new Person(2, "Person2", "", "Phone2"));
        personList.add(new Person(3, "Person3", "", null));
        personList.add(new Person(4, "Person4", "Street4", "Phone4"));
        personList.add(new Person(4, "newPerson4", "newStreet4", "newPhone4"));
        personList.add(new Person(5, "Person5", "Street5", "Phone5"));
    }

    private static Stream<Arguments> getImplClasses() {
        return Stream.of(
            Arguments.of(new SynchronizedCache()),
            Arguments.of(new ReadWriteLockCache())
        );
    }

    @ParameterizedTest
    @MethodSource("getImplClasses")
    void testReadWriteLockPersonOperations(PersonDataBase personDatabase) {
        for (var person : personList) {
            personDatabase.add(person);
        }

        Thread thread1 = new Thread(() -> {
            var persons = personDatabase.findByName("Person1");
            Assertions.assertThat(persons).isNotEmpty();

            persons = personDatabase.findByName("Person3");
            Assertions.assertThat(persons).isEmpty();
        });

        @SuppressWarnings("MagicNumber")
        Thread thread2 = new Thread(() -> {
            personDatabase.add(new Person(3, "Person3", "Street3", "Phone3"));
        });

        @SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
        Thread thread3 = new Thread(() -> {
            personDatabase.add(new Person(10, "Alex", "Street", "10"));
            personDatabase.add(new Person(11, "Alex", "Street", "11"));
            personDatabase.add(new Person(12, "Ivan", "Street", "12"));
        });

        @SuppressWarnings("MagicNumber")
        Thread thread4 = new Thread(() -> {
            var persons = personDatabase.findByName("Alex");
            Assertions.assertThat(persons.size()).isEqualTo(2);

            persons = personDatabase.findByAddress("Street");
            Assertions.assertThat(persons.size()).isEqualTo(3);
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();

        } catch (InterruptedException e) {
            LOGGER.error(e.getStackTrace());
        }
    }
}
