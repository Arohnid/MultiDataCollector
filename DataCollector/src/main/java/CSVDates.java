import java.util.LinkedHashMap;
import java.util.Map;

public class CSVDates {
    private Map<String, String> stationDates = new LinkedHashMap<>();

    public void setStationDates(Map<String, String> stationDates) {
        this.stationDates = stationDates;
    }

    public void printStationDates() {
        System.out.println("\u001B[32m" + "Станции и даты открытия: " + "\u001B[0m");
        for (Map.Entry<String, String> entry : stationDates.entrySet()) {
            System.out.println("Станция: " + entry.getKey() + " | Дата открытия: " + entry.getValue());
        }
    }
}
