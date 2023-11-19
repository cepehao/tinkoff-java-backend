package edu.project3;

import edu.project3.model.CConfiguration;
import edu.project3.parser.CNginxLogParser;

public class CApplication {
    public void run(String[] args) {
        var configuration = new CConfiguration(args);
        var parser = new CNginxLogParser(configuration);
        // --path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs --from 2022-05-05 --to 2022-07-07 --format md
        parser.parse();

        var x = 10;
    }
}
