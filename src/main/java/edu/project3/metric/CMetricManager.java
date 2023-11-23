package edu.project3.metric;

import edu.project3.model.CNginxData;
import edu.project3.nginx.CConfiguration;
import java.util.ArrayList;
import java.util.List;

public class CMetricManager {
    private final List<IMetric> metricList;

    public CMetricManager(CConfiguration configuration) {
        metricList = new ArrayList<>();
        metricList.add(new CGeneralInfo(configuration));
        metricList.add(new CResponseCodes());
        metricList.add(new CResourcesRequested());
        metricList.add(new CUserAgent());
        metricList.add(new CRequestTypes());
    }

    public void manageNginxData(CNginxData nginxData) {
        for (var metric: metricList) {
            metric.processNginxData(nginxData);
        }
    }

    public List<IMetric> getMetricList() {
        return metricList;
    }
}
