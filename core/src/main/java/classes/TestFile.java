package classes;

public class TestFile {
    private final int id;
    private final int id_learnerTest;
    private final String filePath;

    public TestFile(int id, int id_learnerTest, String filePath) {
        this.id = id;
        this.id_learnerTest = id_learnerTest;
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public int getId_learnerTest() {
        return id_learnerTest;
    }

    public String getFilePath() {
        return filePath;
    }
}
