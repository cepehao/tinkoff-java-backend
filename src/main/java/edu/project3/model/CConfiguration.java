package edu.project3.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public final class CConfiguration {
    private static final String URL_REGEX = "^https://.*";

    private String stringPath;
    private LocalDate from;
    private LocalDate to;
    private EPrintFormat format;

    public CConfiguration(String[] args) {
        Options options = new Options();
        options.addOption("p", "path", true, "Путь");
        options.addOption("f", "from", true, "Дата начала");
        options.addOption("t", "to", true, "Дата окончания");
        options.addOption("ft", "format", true, "Формат ответа");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (!cmd.hasOption("path")) {
                throw new IllegalArgumentException("Укажите путь до файла с логами");
            } else {
                stringPath = cmd.getOptionValue("path");

                from = LocalDate.parse(cmd.getOptionValue("from"), DateTimeFormatter.ISO_DATE);

                to = LocalDate.parse(cmd.getOptionValue("to"), DateTimeFormatter.ISO_DATE);

                format = EPrintFormat.buildFormatType(cmd.getOptionValue("format"));
            }

        } catch (ParseException | DateTimeParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getStringPath() {
        return stringPath;
    }

    public void setStringPath(String stringPath) {
        this.stringPath = stringPath;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public EPrintFormat getFormat() {
        return format;
    }

    public void setFormat(EPrintFormat format) {
        this.format = format;
    }

    public boolean isLocalFile() {
        return !stringPath.matches(URL_REGEX);
    }
}
