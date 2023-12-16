package edu.project3.metric;

import edu.project3.model.CNginxData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CUserAgent implements IMetric {
    private static final String HEADER = "Пользовательские агенты";
    private static final String[] COLUMN_TITLES = new String[] {"Агент", "Количество"};

    private final Map<String, Integer> agentsMap;

    public CUserAgent() {
        agentsMap = new HashMap<>();
    }

    @Override
    public void processNginxData(CNginxData nginxData) {
        var agentName = getAgentName(nginxData.httpUserAgent());

        if (agentsMap.containsKey(agentName)) {
            agentsMap.put(agentName, agentsMap.get(agentName) + 1);
        } else {
            agentsMap.put(agentName, 1);
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
        var entryList = new ArrayList<>(agentsMap.entrySet());

        entryList.sort(Map.Entry.comparingByValue());

        List<String[]> infoList = new ArrayList<>();

        int i = 0;
        while (i < TOP_THREE_STATS && i < entryList.size()) {
            int j = entryList.size() - 1 - i;

            var agentName = entryList.get(j).getKey();
            var count = entryList.get(j).getValue();

            infoList.add(new String[] {
                agentName,
                count.toString()
            });

            i++;
        }

        return infoList;
    }

    private String getAgentName(String userAgent) {
        return userAgent.split(" ")[0];
    }
}
