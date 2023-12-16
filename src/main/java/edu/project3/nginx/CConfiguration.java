package edu.project3.nginx;

import edu.project3.model.EPrintFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import static edu.project3.utils.CLogger.LOGGER;

public final class CConfiguration {
    private static final String URL_REGEX = "^https://.*";
    private static final String PATH = "path";
    private static final String FROM = "from";
    private static final String TO = "to";
    private static final String FORMAT = "format";

    private String[] stringPaths;
    private LocalDate from;
    private LocalDate to;
    private EPrintFormat format;

    public CConfiguration(String[] args) {
        Options options = new Options();
        options.addOption("p", PATH, true, "Путь");
        options.addOption("f", FROM, true, "Дата начала");
        options.addOption("t", TO, true, "Дата окончания");
        options.addOption("ft", FORMAT, true, "Формат ответа");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (!cmd.hasOption(PATH)) {
                throw new IllegalArgumentException("Укажите путь до файла с логами");
            } else {
                stringPaths = cmd.getOptionValues(PATH);

                var fromString = cmd.getOptionValue(FROM);
                if (fromString != null) {
                    try {
                        from = LocalDate.parse(fromString, DateTimeFormatter.ISO_DATE);
                    } catch (DateTimeParseException ex) {
                        LOGGER.warn("Не получилось преобразовать аргумент from в дату: " + from);
                    }
                }

                var toString = cmd.getOptionValue(TO);
                if (toString != null) {
                    try {
                        to = LocalDate.parse(toString, DateTimeFormatter.ISO_DATE);
                    } catch (DateTimeParseException ex) {
                        LOGGER.warn("Не получилось преобразовать аргумент to в дату: " + to);
                    }
                }

                format = EPrintFormat.buildFormatType(cmd.getOptionValue(FORMAT));
            }

        } catch (ParseException | DateTimeParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String[] getStringPaths() {
        return stringPaths;
    }

    public String getStringPath(int pathIndex) {
        return stringPaths[pathIndex];
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public EPrintFormat getFormat() {
        return format;
    }

    public boolean isLocalFile(int pathIndex) {
        return !stringPaths[pathIndex].matches(URL_REGEX);
    }
}
