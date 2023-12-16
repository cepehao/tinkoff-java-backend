package edu.project3.metric;

import edu.project3.model.CNginxData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CResourcesRequested implements IMetric {
    private static final String HEADER = "Запрашиваемые ресурсы";
    private static final String[] COLUMN_TITLES = new String[] {"Ресурс", "Количество"};

    private final Map<String, Integer> resourcesMap;

    public CResourcesRequested() {
        resourcesMap = new HashMap<>();
    }

    private String getResourceName(String uri) {
        return uri.substring(uri.lastIndexOf('/'));
    }

    @Override
    public void processNginxData(CNginxData nginxData) {
        var resourceName = getResourceName(nginxData.uri());

        if (resourcesMap.containsKey(resourceName)) {
            resourcesMap.put(resourceName, resourcesMap.get(resourceName) + 1);
        } else {
            resourcesMap.put(resourceName, 1);
        }
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
        var entryList = new ArrayList<>(resourcesMap.entrySet());

        entryList.sort(Map.Entry.comparingByValue());

        List<String[]> infoList = new ArrayList<>();

        int i = 0;
        while (i < TOP_THREE_STATS && i < entryList.size()) {
            int j = entryList.size() - 1 - i;

            var resourceName = entryList.get(j).getKey();
            var count = entryList.get(j).getValue();

            infoList.add(new String[] {
                "`" + resourceName + "`",
                count.toString()
            });

            i++;
        }

        return infoList;
    }
}
