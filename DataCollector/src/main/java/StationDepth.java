import java.util.LinkedHashMap;
import java.util.Map;

public class StationDepth {
    private String station_name;
    private String depth;
    private Map<String, String> stations = new LinkedHashMap<>();

    public StationDepth() {

    }

    public StationDepth(String station_name, String depth){
        this.station_name = station_name;
        this.depth = depth;
    }

    public void setStation(Map<String, String> stations){
        this.stations = stations;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public void printStations(){
        System.out.println("\u001B[32m" + "Станции и их глубина: " + "\u001B[0m");
        for (Map.Entry<String, String> entry : stations.entrySet()){
            System.out.println("Станция: " + entry.getKey() + " | " + "Глубина: " + entry.getValue());
        }
    }
}
