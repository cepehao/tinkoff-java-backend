package edu.project3.metric;

import edu.project3.model.CNginxData;
import java.util.List;

public interface IMetric {
    int TOP_THREE_STATS = 3;

    void processNginxData(CNginxData nginxData);

    String getHeader();

    String[] getColumnTitles();

    List<String[]> getInfo();
}
