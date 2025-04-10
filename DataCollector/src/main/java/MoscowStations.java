import java.util.LinkedHashMap;
import java.util.Map;

public class MoscowStations {
    private Map<String, String> stations = new LinkedHashMap<>();

    public void setStations(Map<String, String> stations) {
        this.stations = stations;
    }

    public void printStations(){
        System.out.println("\u001B[32m" + "Список станций Московского метро:" + "\u001B[0m");
        for (Map.Entry<String, String> entry : stations.entrySet()){
            System.out.println("Линия: " + entry.getKey() + " | Станции: " + entry.getValue());
        }
    }
}
