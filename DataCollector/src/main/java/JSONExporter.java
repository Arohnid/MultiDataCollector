import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.FileWriter;
import java.util.*;

public class JSONExporter {
    Map<String, String[]> stationMap = new LinkedHashMap<>();
    Map<String, String> linesMap = new LinkedHashMap<>();

    public void jsonExportStationsLines(String url, String path) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        WebPageParser webParser = new WebPageParser();
        webParser.stationsParser(url);
        webParser.linesParser(url);
        linesMap = webParser.getLinesMap();
        Lines[] lines = new Lines[webParser.getLinesMoscow().size()];
        webParser.getLinesMoscow().toArray(lines);
        Map<String, String> stationsMap = new LinkedHashMap<>(webParser.getStationsMap());
        for (Map.Entry<String, String> entry : stationsMap.entrySet()) {
            stationMap.put(entry.getKey(), entry.getValue().split(", "));
        }
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.set("stations", objectMapper.valueToTree(stationMap));
        jsonNode.set("lines", objectMapper.valueToTree(lines));
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(jsonNode.toString());
        fileWriter.close();
    }

    public void jsonExportStations(String url, String csvPath, String jsonPath, String path) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Stations> stationsList = new ArrayList<>();
        for (Map.Entry<String, String> entry1 : linesMap.entrySet()) {
            for (Map.Entry<String, String[]> entry2 : stationMap.entrySet()) {
                if (entry1.getKey().equals(entry2.getKey())) {
                    String[] lines = entry2.getValue();
                    for (String line : lines) {
                        stationsList.add(new Stations(line, entry1.getValue()));
                    }
                }
            }
        }
        CSVParser csvParser = new CSVParser();
        csvParser.csvParse("data/4/6/dates-1.csv");
        Map<String, String> datesMap = csvParser.getDatesMap();
        for (Map.Entry<String, String> entry : datesMap.entrySet()) {
            for (Stations stations : stationsList) {
                if (stations.getName().equals(entry.getKey())) {
                    stations.setDate(entry.getValue());
                }
            }
        }
        JSONParser jsonParser = new JSONParser();
        jsonParser.jsonParse("data/2/4/depths-1.json");
        Map<String, String> depthMap = jsonParser.getDepthMap();
        for (Map.Entry<String, String> entry : depthMap.entrySet()) {
            for (Stations stations : stationsList) {
                if (stations.getName().equals(entry.getKey())) {
                    stations.setDepth(entry.getValue());
                }
            }
        }
        for (int i = 0; i < stationsList.size(); i++) {
            int count = 0;
            String name = stationsList.get(i).getName();
            for (Stations stations : stationsList) {
                if (stations.getName().equals(name)) {
                    count++;
                }
                if (count >= 2){
                    stationsList.get(i).setHasConnection(true);
                } else {
                    stationsList.get(i).setHasConnection(null);
                }
            }
        }
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.set("stations", objectMapper.valueToTree(stationsList));
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(jsonNode.toString());
        fileWriter.close();
    }
}
