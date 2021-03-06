package classes;

public class TestFile {
    private final int id;
    private final Work work;
    private final String filePath;

    public TestFile(int id, Work work, String filePath) {
        this.id = id;
        this.work = work;
        this.filePath = filePath;
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
}
