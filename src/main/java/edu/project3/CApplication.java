package edu.project3;

import edu.project3.nginx.CConfiguration;
import edu.project3.nginx.CNginxLogParser;
import edu.project3.nginx.CPrinter;

public final class CApplication {
    public void run(String[] args) {
        var configuration = new CConfiguration(args);
        var parser = new CNginxLogParser(configuration);
        // --path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs --from 2022-05-05 --to 2022-07-07 --format md
        var metrics = parser.parse();

        var printer = new CPrinter(configuration, metrics);

        printer.print();
    }
}
