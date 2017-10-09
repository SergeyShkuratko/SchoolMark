package classes;

public class LearnerTest {
    private final int id;
    private final int id_control;
    private final int id_learner;
    private int id_status;
    private int mark;
    private String comment;

    public LearnerTest(int id, int id_control, int id_learner) {
        this.id = id;
        this.id_control = id_control;
        this.id_learner = id_learner;
    }

    public int getId() {
        return id;
    }

    public int getId_control() {
        return id_control;
    }

    public int getId_learner() {
        return id_learner;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
