package edu.project3.metric;

import edu.project3.model.CNginxData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CRequestTypes implements IMetric {
    private static final String HEADER = "Типы запросов";
    private static final String[] COLUMN_TITLES = new String[] {"Тип запроса", "Количество"};

    private final Map<String, Integer> requestsMap;

    public CRequestTypes() {
        requestsMap = new HashMap<>();
    }


    @Override
    public void processNginxData(CNginxData nginxData) {
        var requestType = nginxData.requestType().toString();

        if (requestsMap.containsKey(requestType)) {
            requestsMap.put(requestType, requestsMap.get(requestType) + 1);
        } else {
            requestsMap.put(requestType, 1);
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
        var entryList = new ArrayList<>(requestsMap.entrySet());

        entryList.sort(Map.Entry.comparingByValue());

        List<String[]> infoList = new ArrayList<>();

        int i = 0;
        while (i < TOP_THREE_STATS && i < entryList.size()) {
            int j = entryList.size() - 1 - i;

            var requestType = entryList.get(j).getKey();
            var count = entryList.get(j).getValue();

            infoList.add(new String[] {
                requestType,
                count.toString()
            });

            i++;
        }

        return infoList;
    }
}
