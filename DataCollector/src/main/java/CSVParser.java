import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CSVParser {
    Map<String, String> datesMap = new LinkedHashMap<>();

    public void csvParse(String path) {
        List<String> lines = new ArrayList<>();
        CSVDates csvDates = new CSVDates();
        try {
            lines = Files.readAllLines(Paths.get(path));
            System.out.println("\u001B[31m" + "Открыт файл: " + path + "\u001B[0m");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String firstLine = null;
        for (String line : lines) {
            if (firstLine == null) {
                firstLine = line;
                continue;
            }
            String[] fragments = line.split(",");
            datesMap.put(fragments[0], fragments[1]);
        }
        csvDates.setStationDates(datesMap);
        csvDates.printStationDates();
    }

    public Map<String, String> getDatesMap() {
        return datesMap;
    }
}
