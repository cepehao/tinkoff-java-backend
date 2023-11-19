package edu.project3.metric;

import edu.project3.model.CConfiguration;
import edu.project3.model.CNginxData;
import java.util.ArrayList;
import java.util.List;

public class CMetricManager {
    private CConfiguration configuration;
    private IMetric generalInfo;
    private IMetric resourcesRequested;
//    private IMetric responseCodes;

    private List<IMetric> metricList;

    public CMetricManager(CConfiguration configuration) {
        this.configuration = configuration;

//        generalInfo = new CGeneralInfo(configuration);
//        resourcesRequested = new CResourcesRequested();
        metricList = new ArrayList<>();
        metricList.add(new CGeneralInfo(configuration));
        metricList.add(resourcesRequested = new CResourcesRequested());



//        responseCodes = new CResponseCodes();
    }

    public void manageNginxData(CNginxData nginxData) {
        for (var metric: metricList) {
            metric.ProcessNginxData(nginxData);
        }
    }

    public List<IMetric> getMetricList() {
        return metricList;
    }


}
