package edu.project3.nginx;

import edu.project3.metric.IMetric;
import edu.project3.model.EPrintFormat;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class CPrinter {
    private static final String OUTPUT_PATH = "src/main/resources/project3/outputFiles/";
    private static final String ADOC_SEPARATOR = "|===";

    private final EPrintFormat printFormat;
    private final List<IMetric> metrics;

    public CPrinter(CConfiguration configuration, List<IMetric> metrics) {
        printFormat = configuration.getFormat();
        this.metrics = metrics;
        print();
    }

    public void print() {
        switch (printFormat) {
            case MD -> printMD();
            case ADOC -> printADOC();
            default -> printMD();
        }
    }

    private void printMD() {
        try (FileWriter writer = new FileWriter(OUTPUT_PATH + "res.md")) {
            for (var metric: metrics) {
                writer.write("#### " + metric.getHeader() + System.lineSeparator());

                var columnTitles = metric.getColumnTitles();
                for (var columnTitle: columnTitles) {
                    writer.write("|" + columnTitle);
                }
                writer.write("|" + System.lineSeparator());

                for (int i = 0; i < columnTitles.length; i++) {
                    writer.write("|:-:");
                }
                writer.write("|" + System.lineSeparator());

                var infoList = metric.getInfo();
                for (var columnValues: infoList) {
                    for (var columnValue: columnValues) {
                        writer.write("|" + columnValue);
                    }
                    writer.write("|" + System.lineSeparator());
                }
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void printADOC() {
        try (FileWriter writer = new FileWriter(OUTPUT_PATH + "res.adoc")) {
            for (var metric: metrics) {
                writer.write("*" + metric.getHeader() + "*" + System.lineSeparator());

                writer.write(ADOC_SEPARATOR + System.lineSeparator());

                var columnTitles = metric.getColumnTitles();
                for (var columnTitle: columnTitles) {
                    writer.write("|" + columnTitle);
                }
                writer.write(System.lineSeparator());

                var infoList = metric.getInfo();
                for (var columnValues: infoList) {
                    for (var columnValue: columnValues) {
                        writer.write("|" + columnValue);
                    }
                    writer.write(System.lineSeparator());
                }

                writer.write(ADOC_SEPARATOR + System.lineSeparator());
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
