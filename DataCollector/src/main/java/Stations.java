public class Stations {
    private String name;
    private String line;
    private String date;
    private String depth;
    private Boolean hasConnection;

    public Stations() {

    }

    public Stations(String name, String line) {
        this.name = name;
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public Boolean getHasConnection() {
        return hasConnection;
    }

    public void setHasConnection(Boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    @Override
    public String toString() {
        return "Stations{" +
                "name='" + name + '\'' +
                ", line='" + line + '\'' +
                '}';
    }
}
