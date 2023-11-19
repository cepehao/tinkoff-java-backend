package edu.project3.metric;

import edu.project3.model.CConfiguration;
import edu.project3.model.CNginxData;
import java.time.LocalDate;

public final class CGeneralInfo implements IMetric{
    private final String fileName;
    private final String startDate;
    private final String endDate;
    int countRequests;
    int sumWeightResponses;
    int avgWeightResponses;

    public CGeneralInfo(CConfiguration configuration) {
        fileName = buildFileName(configuration.getStringPath());
        startDate = buildStartDate(configuration.getFrom());
        endDate = buildEndDate(configuration.getTo());
    }

    @Override
    public void ProcessNginxData(CNginxData nginxData) {
        sumWeightResponses += nginxData.bodyBytesSent();
        avgWeightResponses = sumWeightResponses / ++countRequests;
    }

    private String buildFileName(String stringPath) {
        //todo
        return "";
    }

    private String buildStartDate(LocalDate startDate) {
        //todo
        return "";
    }

    private String buildEndDate(LocalDate endDate) {
        //todo
        return "";
    }
}
