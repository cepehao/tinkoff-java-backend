package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class CDateParserEmpty implements IDateParser {
    @Override
    public Optional<LocalDate> parseDate(String string) {
        return Optional.empty();
    }
}
