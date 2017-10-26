package student.dto;

public class DTOFile {
    private int id;
    private String file;

    public DTOFile(int id, String file) {
        this.id = id;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public String getFile() {
        return file;
    }
}
