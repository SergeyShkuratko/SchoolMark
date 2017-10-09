package classes;

public class SubjectTeacherConnector {
    private final int id;
    private final int id_teacher;
    private final int id_subject;

    public SubjectTeacherConnector(int id, int id_teacher, int id_subject) {
        this.id = id;
        this.id_teacher = id_teacher;
        this.id_subject = id_subject;
    }

    public int getId() {
        return id;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public int getId_subject() {
        return id_subject;
    }
}
