# MultiDataCollector
## Overview
The following app collects information about Moscow metro stations and lines from HTML, CSV and JSON files and then exports said information to JSON file.
## Guidelines
1. Clone this repository
2. Open this project with your preferred IDE
3. Navigate to Main.java class
4. Specify which website and files you want to parse
```java
	String url = "https://skillbox-java.github.io/";
        String csvPath = "data/4/6/dates-1.csv";
        String jsonPath = "data/2/4/depths-1.json";
```
5. Choose where JSON file with results will be located
   ```java
   JSONExporter jsonExporter = new JSONExporter();
        jsonExporter.jsonExportStationsLines(url, "data/lines_and_stations.json");
        jsonExporter.jsonExportStations(url, csvPath, jsonPath, "data/stations.json");
   ```
## Result of app execution
![{59D74BDE-9234-4259-827E-675F4D1BC208}](https://github.com/user-attachments/assets/ccbaebc5-ec2b-4549-a695-51517c9d823b) 

![{65AB6D5A-8980-4A66-96D2-1D7F27A1725A}](https://github.com/user-attachments/assets/acae2219-aeee-441e-8f09-02bbfd8c5ea7)

