package edu.project3.metric;

import edu.project3.model.CNginxData;
import edu.project3.nginx.CConfiguration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class CGeneralInfo implements IMetric {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String HEADER = "Общая информация";
    private static final String[] COLUMN_TITLES = new String[] {"Метрика", "Значение"};

    private final String[] fileNames;
    private final String startDate;
    private final String endDate;
    long countRequests;
    long sumWeightResponses;
    long avgWeightResponses;

    public CGeneralInfo(CConfiguration configuration) {
        fileNames = buildFileNames(configuration.getStringPaths());
        startDate = buildStartDate(configuration.getFrom());
        endDate = buildEndDate(configuration.getTo());
    }

    private String[] buildFileNames(String[] stringPaths) {
        String[] resultMas = new String[stringPaths.length];

        for (int i = 0; i < stringPaths.length; i++) {
            resultMas[i] = stringPaths[i].substring(stringPaths[i].lastIndexOf('/') + 1);
        }
        return resultMas;
    }

    private String buildStartDate(LocalDate startDate) {
        if (startDate != null) {
            return startDate.format(FORMATTER);
        } else {
            return "-";
        }
    }

    private String buildEndDate(LocalDate endDate) {
        if (endDate != null) {
            return endDate.format(FORMATTER);
        } else {
            return "-";
        }
    }

    private String getFormattedFileNames() {
        var sb = new StringBuilder();

        for (var fileName : fileNames) {
            sb.append("`").append(fileName).append("` ");
        }

        return sb.toString().strip();
    }

    @Override
    public void processNginxData(CNginxData nginxData) {
        sumWeightResponses += nginxData.bodyBytesSent();
        avgWeightResponses = sumWeightResponses / ++countRequests;
    }

    @Override
    public String getHeader() {
        return HEADER;
    }

    @Override
    public String[] getColumnTitles() {
        return COLUMN_TITLES;
    }

    @Override
    public List<String[]> getInfo() {
        List<String[]> infoList = new ArrayList<>();

        infoList.add(new String[] {"Файл(ы)", getFormattedFileNames()});
        infoList.add(new String[] {"Начальная дата", startDate});
        infoList.add(new String[] {"Конечная дата", endDate});
        infoList.add(new String[] {"Количество запросов", String.valueOf(countRequests)});
        infoList.add(new String[] {"Средний размер ответа", String.valueOf(avgWeightResponses)});

        return infoList;
    }

}
