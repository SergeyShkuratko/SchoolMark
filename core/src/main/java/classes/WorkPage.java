package classes;

public class WorkPage {
    private final int id;
    private final Work work;
    private String filePath;

    public WorkPage(int id, Work work) {
        this.id = id;
        this.work = work;
    }

    public int getId() {
        return id;
    }

    public Work getWork() {
        return work;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
