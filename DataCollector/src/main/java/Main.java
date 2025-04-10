public class Main {
    public static void main(String[] args) throws Exception{
        String url = "https://skillbox-java.github.io/";
        String csvPath = "data/4/6/dates-1.csv";
        String jsonPath = "data/2/4/depths-1.json";
        
        FileSearcher fileSearcher = new FileSearcher();
        fileSearcher.fileSearch("data/");
        fileSearcher.printFiles();

        JSONExporter jsonExporter = new JSONExporter();
        jsonExporter.jsonExportStationsLines(url, "data/lines_and_stations.json");
        jsonExporter.jsonExportStations(url, csvPath, jsonPath, "data/stations.json");

    }
}
