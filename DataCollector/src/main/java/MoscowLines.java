import java.util.LinkedHashMap;
import java.util.Map;

public class MoscowLines {
    private Map<String, String> linesMap = new LinkedHashMap<>();


    public void setLinesMap(Map<String, String> linesMap) {
        this.linesMap = linesMap;
    }


    public void printLines() {
        System.out.println("\u001B[32m" + "Список линий Московского метро:" + "\u001B[0m");
        for (Map.Entry<String, String> entry : linesMap.entrySet()) {
            System.out.println("Линия: " + entry.getKey() + " | Название: " + entry.getValue());
        }
    }
}
