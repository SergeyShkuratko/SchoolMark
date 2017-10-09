package classes;

import java.time.LocalDate;

public class TestEventHistory {
    private final int id;
    private final int id_control;
    private final int id_teacher;
    private final LocalDate dateTime;
    private final int id_old_status;
    private final int id_new_status;

    public TestEventHistory(int id, int id_control, int id_teacher, LocalDate dateTime, int id_old_status, int id_new_status) {
        this.id = id;
        this.id_control = id_control;
        this.id_teacher = id_teacher;
        this.dateTime = dateTime;
        this.id_old_status = id_old_status;
        this.id_new_status = id_new_status;
    }

    public int getId() {
        return id;
    }

    public int getId_control() {
        return id_control;
    }

    public int getId_teacher() {
        return id_teacher;
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
