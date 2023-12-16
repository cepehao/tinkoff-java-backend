package edu.project3.metric;

import edu.project3.model.CNginxData;
import edu.project3.utils.CHttpStatusCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CResponseCodes implements IMetric {
    private static final String HEADER = "Коды ответа";
    private static final String[] COLUMN_TITLES = new String[] {"Код", "Имя", "Количество"};

    private final Map<Integer, Integer> responsesMap;

    public CResponseCodes() {
        responsesMap = new HashMap<>();
    }

    @Override
    public void processNginxData(CNginxData nginxData) {
        var statusCode = nginxData.status();

        if (responsesMap.containsKey(statusCode)) {
            responsesMap.put(statusCode, responsesMap.get(statusCode) + 1);
        } else {
            responsesMap.put(statusCode, 1);
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
        var entryList = new ArrayList<>(responsesMap.entrySet());

        entryList.sort(Map.Entry.comparingByValue());

        List<String[]> infoList = new ArrayList<>();

        int i = 0;
        while (i < TOP_THREE_STATS && i < entryList.size()) {
            int j = entryList.size() - 1 - i;

            var code = entryList.get(j).getKey();
            var codeName = CHttpStatusCode.getStatusCodeName(code);
            var count = entryList.get(j).getValue();

            infoList.add(new String[] {
                code.toString(),
                codeName,
                count.toString()
            });

            i++;
        }

        return infoList;
    }
}
