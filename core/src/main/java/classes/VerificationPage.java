package classes;

public class VerificationPage {
    private final int id;
    private final VerificationResult result;
    private String filePath;

    public VerificationPage(int id, VerificationResult result) {
        this.id = id;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public VerificationResult getResult() {
        return result;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
