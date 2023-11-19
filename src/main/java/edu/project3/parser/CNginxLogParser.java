package edu.project3.parser;

import edu.project3.metric.IMetric;
import edu.project3.model.CConfiguration;
import edu.project3.utils.CLogger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

public class CNginxLogParser {
    private CConfiguration configuration;
    private static final Pattern NGNIX_LOG_PATTERN =
        Pattern.compile(
            "^(\\S+) (\\S+ \\S+) \\[([^]]+)] \"([A-Z]+) (\\S+) (\\S+)\" (\\d{3}) (\\d+) \"(\\S+)\" \"([^\"]*)\".*"
        );

    public CNginxLogParser(CConfiguration configuration) {
        this.configuration = configuration;
    }

    public List<IMetric> parse() {

        try(BufferedReader reader = getBufferedReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);

                var matcher = NGNIX_LOG_PATTERN.matcher(line);

                if (matcher.find()) {

                }
            }
        }catch (IOException ex) {
            CLogger.LOGGER.error("Не удалось получить доступ к файлу");
            return null;
        }

        return null;
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

}
