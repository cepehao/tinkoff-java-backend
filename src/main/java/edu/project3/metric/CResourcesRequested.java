package edu.project3.metric;

import edu.project3.model.CNginxData;
import java.util.Map;
import java.util.TreeMap;

public final class CResourcesRequested implements IMetric{
    private Map<String, Integer> resourcesMap;

    public CResourcesRequested() {
        resourcesMap = new TreeMap<>();
    }

    @Override
    public void ProcessNginxData(CNginxData nginxData) {
        var resourceName = getResourceName(nginxData.uri());

        if (resourcesMap.containsKey(resourceName)) {
            resourcesMap.put(resourceName, resourcesMap.get(resourceName) + nginxData.bodyBytesSent());
        } else {
            resourcesMap.put(resourceName, nginxData.bodyBytesSent());
        }
    }

    private String getResourceName(String uri) {
        return uri.substring(uri.lastIndexOf('/'));
    }
}
