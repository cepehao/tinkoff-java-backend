package edu.hw5.task3;

import static edu.hw5.task3.CDateParser1.DATE_PARSER_1_REGEX;
import static edu.hw5.task3.CDateParser2.DATE_PARSER_2_REGEX;
import static edu.hw5.task3.CDateParser3.DATE_PARSER_3_REGEX;
import static edu.hw5.task3.CDateParser4.DATE_PARSER_4_REGEX;

public final class CDateParserFactory {

    private CDateParserFactory() {

    }

    public static IDateParser getDateParser(String stringDate) {
        IDateParser dateParser = new CDateParserEmpty();

        if (stringDate.matches(DATE_PARSER_1_REGEX)) {
            dateParser = new CDateParser1();
        }

        if (stringDate.matches(DATE_PARSER_2_REGEX)) {
            dateParser = new CDateParser2();
        }

        if (stringDate.matches(DATE_PARSER_3_REGEX)) {
            dateParser = new CDateParser3();
        }

        if (stringDate.matches(DATE_PARSER_4_REGEX)) {
            dateParser = new CDateParser4();
        }

        return dateParser;
    }
}
