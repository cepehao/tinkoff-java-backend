package edu.hw03.Task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Task5 {

    private static final String ASC = "ASC";
    private static final String DESC = "DESC";

    public List<CContact> parseContacts(String[] people, String sort) {

        if (!sort.equals(ASC) && !sort.equals(DESC)) {
            throw new IllegalArgumentException("Некорректный тип сортировки");
        }

        List<CContact> contactList = new ArrayList<>();

        if (people == null || people.length == 0) {
            return contactList;
        }

        var name = "";
        var surname = "";

        for (var fullName: people) {
            var splitName = fullName.split(" ");

            name = splitName[0];

            try {
                surname = splitName[1];
            } catch (ArrayIndexOutOfBoundsException ex) {
                surname = "";
            }

            contactList.add(new CContact(name, surname));
        }

        Comparator<CContact> comparator = Comparator
            .comparing(CContact::surname)
            .thenComparing(CContact::name);

        if (sort.equals(DESC)) {
            comparator = comparator.reversed();
        }

        contactList.sort(comparator);

        return contactList;
    }
}
