package classes;

import java.time.LocalDate;

public class LearnerTestHistory {
    private final int id;
    private final int id_learnerTest;
    private final int id_learner;
    private final LocalDate dateTime;
    private final int id_old_status;
    private final int id_new_status;

    public LearnerTestHistory(int id, int id_learnerTest, int id_learner, LocalDate dateTime, int id_old_status, int id_new_status) {
        this.id = id;
        this.id_learnerTest = id_learnerTest;
        this.id_learner = id_learner;
        this.dateTime = dateTime;
        this.id_old_status = id_old_status;
        this.id_new_status = id_new_status;
    }

    public int getId() {
        return id;
    }

    public int getId_learnerTest() {
        return id_learnerTest;
    }

    public int getId_learner() {
        return id_learner;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public int getId_old_status() {
        return id_old_status;
    }

    public int getId_new_status() {
        return id_new_status;
    }
}

