package edu.project3.parser;

import edu.project3.metric.CMetricManager;
import edu.project3.metric.IMetric;
import edu.project3.model.CConfiguration;
import edu.project3.model.CNginxData;
import edu.project3.model.ERequestType;
import edu.project3.utils.CLogger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
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
    private CConfiguration configuration;
    // 217.168.17.5 - - [17/May/2015:08:05:42 +0000] "GET /downloads/product_1 HTTP/1.1" 404 332 "-" "Debian APT-HTTP/1.3 (0.8.10.3)"
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
        CMetricManager metricManager;

        try(BufferedReader reader = getBufferedReader()) {
            metricManager = new CMetricManager(configuration);

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);

                var matcher = NGNIX_LOG_PATTERN.matcher(line);

                if (matcher.find()) {
                    var nginxData = buildNginxData(matcher);
                    metricManager.manageNginxData(nginxData);
                } else {
                    LOGGER.warn("Некорректный формат лога: " + line);
                }
            }
        }catch (IOException ex) {
            LOGGER.error("Не удалось получить доступ к файлу");
            return null;
        }

        // todo проверка from to
        return metricManager.getMetricList();
    }

    private BufferedReader getBufferedReader() throws IOException {
        if (configuration.isLocalFile()) {
            return new BufferedReader(new FileReader(configuration.getStringPath()));
        } else {
            var url = new URL(configuration.getStringPath());
            var connection = (HttpURLConnection) url.openConnection();
            return new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
    }

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
        }catch (DateTimeParseException ex) {
            LOGGER.warn("Некорректный формат даты: "+ englishStringTime);
            return null;
        }
    }
}
