import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

import java.util.Map;

public class JSONParser {
    Map<String, String> depthMap = new LinkedHashMap<>();

    public void jsonParse(String path) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String input = Files.readString(Paths.get(path));
        StationDepth stations = new StationDepth();
        StationDepth[] stationDepths = objectMapper.readValue(input, StationDepth[].class);
        System.out.println("\u001B[31m" + "Открыт файл: " + path + "\u001B[0m");
        for (StationDepth stationDepth : stationDepths) {
            if (depthMap.containsKey(stationDepth.getStation_name())){
                String name = stationDepth.getStation_name();
                depthMap.put(name, depthMap.get(name) + ", " + stationDepth.getDepth());
            } else {
                depthMap.put(stationDepth.getStation_name(), stationDepth.getDepth());
            }
        }

        stations.setStation(depthMap);
        stations.printStations();
    }

    public Map<String, String> getDepthMap() {
        return depthMap;
    }
}
