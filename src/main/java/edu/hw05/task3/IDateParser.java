package edu.hw05.task3;

import java.time.LocalDate;
import java.util.Optional;

public interface IDateParser {
    Optional<LocalDate> parseDate(String string);
}
