package classes;

public class TestTemplate {
    private final int id;
    private final String theme;
    private final String description;
    private final int classNum;
    private final Subject subject;

    public TestTemplate(int id, String theme, String description, int classNum, Subject subject) {
        this.id = id;
        this.theme = theme;
        this.description = description;
        this.classNum = classNum;
        this.subject = subject;
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

    public int getClassNum() {
        return classNum;
    }

    public Subject getSubject() {
        return subject;
    }
}
