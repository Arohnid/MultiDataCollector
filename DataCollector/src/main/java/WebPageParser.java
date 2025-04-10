import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;

public class WebPageParser {
    private Map<String, String> linesMap = new LinkedHashMap<>();
    private Map<String, String> stationsMap = new LinkedHashMap<>();
    List<Lines> linesMoscow = new ArrayList<>();

    public void linesParser(String url) throws Exception {
        MoscowLines moscowLines = new MoscowLines();
        Document doc = Jsoup.connect(url).get();
        Elements lines = doc.select("span").select("span.js-metro-line");
        List<String> linesList = new ArrayList<>();
        lines.forEach(line -> linesList.add(line.toString()));
        linesList.forEach(line -> {
            line = line
                    .substring(line.indexOf("data-line"))
                    .trim()
                    .replaceAll("[^0-9А-яAD \"-]+", "")
                    .replace("data-line", "")
                    .replaceFirst("-\"", "");
            linesMap.put(line.substring(0, line.indexOf("\"")), line.substring(line.indexOf("\"") + 1));
        });
        for (Map.Entry<String, String> entry : linesMap.entrySet()){
            linesMoscow.add(new Lines(entry.getKey(), entry.getValue()));
        }
        moscowLines.setLinesMap(linesMap);
        moscowLines.printLines();
    }

    public void stationsParser(String url) throws Exception {
        MoscowStations moscowStations = new MoscowStations();
        Document doc = Jsoup.connect(url).get();
        Elements stations = doc.select("div.js-depend");
        Elements linesElem = doc.select("span").select("span.js-metro-line");
        String[] lines = new String[linesElem.size()];
        List<String> stationsList = new ArrayList<>();
        for (int i =0; i < linesElem.size(); i++){
            lines[i] = linesElem.get(i).attr("data-line");
        }
        stations.forEach(station -> stationsList.add(station.text()));
        int count = 0;
        for (String station : stationsList){
            String[] arr = station.split("[0-9]+. ");
            for (int i = 1; i < arr.length; i++){
                if (stationsMap.containsKey(lines[count])){
                    if(arr[i].charAt(arr[i].length() - 1) == ' '){
                        arr[i] = arr[i].substring(0, arr[i].length() - 1).trim();
                    }
                    stationsMap.put(lines[count], stationsMap.get(lines[count]) + ", " + arr[i]);
                } else{
                    if(arr[i].charAt(arr[i].length() - 1) == ' '){
                        arr[i] = arr[i].substring(0, arr[i].length() - 1).trim();
                    }
                    stationsMap.put(lines[count], arr[i]);
                }
            }
            count++;
        }
        moscowStations.setStations(stationsMap);
        moscowStations.printStations();
    }

    public Map<String, String> getStationsMap() {
        return stationsMap;
    }

    public Map<String, String> getLinesMap(){
        return linesMap;
    }

    public List<Lines> getLinesMoscow(){
        return linesMoscow;
    }
}
