import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearcher {
    List<String> fileList = new ArrayList<>();

    public void fileSearch (String path) {
        File file = new File(path);
        fileFinder(file);
    }

    private void fileFinder (File file){
        if (file.isFile() && (file.getName().contains(".json") || file.getName().contains(".csv"))){
            fileList.add("Имя файла: " + file.getName() + " Путь: " + file.getPath());
        }
        if (file.isDirectory()){
            for (File fileEntry : file.listFiles()){
                fileFinder(fileEntry);
            }
        }
    }

    public void printFiles(){
        System.out.println("\u001B[32m" + "Найдены следующие файлы: " + "\u001B[0m");
        for (String file : fileList){
            System.out.println(file);
        }
    }
}
