package edu.project3.nginx;

import edu.project3.metric.CMetricManager;
import edu.project3.metric.IMetric;
import edu.project3.model.CNginxData;
import edu.project3.model.ERequestType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static edu.project3.utils.CLogger.LOGGER;

public class CNginxLogParser {
    private final CConfiguration configuration;
    private static final Pattern NGNIX_LOG_PATTERN =
        Pattern.compile(
            "^(\\S+) (\\S+ \\S+) \\[([^]]+)] \"([A-Z]+) (\\S+) (\\S+)\" (\\d{3}) (\\d+) \"(\\S+)\" \"([^\"]*)\".*"
        );
    private static final DateTimeFormatter FORMATTER_ENGLISH =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);

    public CNginxLogParser(CConfiguration configuration) {
        this.configuration = configuration;
    }

    public List<IMetric> parse() {
        var metricManager = new CMetricManager(configuration);

        for (int i = 0; i < configuration.getStringPaths().length; i++) {
            try (BufferedReader reader = getBufferedReader(i)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    var matcher = NGNIX_LOG_PATTERN.matcher(line);

                    if (matcher.find()) {
                        var nginxData = buildNginxData(matcher);

                        if (isGoodDate(nginxData.timeLocal().toLocalDate())) {
                            metricManager.manageNginxData(nginxData);
                        }
                    } else {
                        LOGGER.warn("Некорректный формат лога: " + line);
                    }
                }
            } catch (IOException ex) {
                LOGGER.error("Не удалось получить доступ к файлу");
                return null;
            }
        }
        return metricManager.getMetricList();
    }

    private BufferedReader getBufferedReader(int pathIndex) throws IOException {
        if (configuration.isLocalFile(pathIndex)) {
            return new BufferedReader(new FileReader(configuration.getStringPath(pathIndex)));
        } else {
            var url = new URL(configuration.getStringPath(pathIndex));
            var connection = (HttpURLConnection) url.openConnection();
            return new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
    }

    @SuppressWarnings("MagicNumber")
    private CNginxData buildNginxData(Matcher logMatcher) {

     return new CNginxData(
         logMatcher.group(1),
         englishTimeToLocal(logMatcher.group(3)),
         ERequestType.buildRequestType(logMatcher.group(4)),
         logMatcher.group(5),
         logMatcher.group(6),
         Integer.parseInt(logMatcher.group(7)),
         Integer.parseInt(logMatcher.group(8)),
         logMatcher.group(10)
     );
    }

    private LocalDateTime englishTimeToLocal(String englishStringTime) {
        try {
            var offsetDateTime = OffsetDateTime.parse(englishStringTime, FORMATTER_ENGLISH);
            return offsetDateTime.toLocalDateTime();
        } catch (DateTimeParseException ex) {
            LOGGER.warn("Некорректный формат даты: " + englishStringTime);
            return null;
        }
    }

    private boolean isGoodDate(LocalDate nginxLocalDate) {
        var start = configuration.getFrom();
        var end = configuration.getTo();

        if (start == null && end == null) {
            return true;
        }

        if (start == null && end != null) {
            return nginxLocalDate.isBefore(end);
        }

        if (start != null && end == null) {
            return nginxLocalDate.isAfter(start);
        }

        return (nginxLocalDate.isBefore(end) && nginxLocalDate.isAfter(start));
    }
}
