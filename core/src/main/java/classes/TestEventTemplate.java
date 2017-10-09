package classes;

public class TestEventTemplate {
    private final int id;
    private final String theme;
    private final String description;
    private final int class_num;
    private final int id_subject;

    public TestEventTemplate(int id, String theme, String description, int class_num, int id_subject) {
        this.id = id;
        this.theme = theme;
        this.description = description;
        this.class_num = class_num;
        this.id_subject = id_subject;
    }

    public int getId() {
        return id;
    }

    public String getTheme() {
        return theme;
    }

    public String getDescription() {
        return description;
    }

    public int getClass_num() {
        return class_num;
    }

    public int getId_subject() {
        return id_subject;
    }
}
