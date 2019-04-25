package ternak.lele.models;

public class Level {
    protected int idLevel;
    protected String level;

    public Level(int idLevel, String level) {
        this.idLevel = idLevel;
        this.level = level;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
