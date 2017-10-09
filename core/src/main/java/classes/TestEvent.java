package classes;

import java.time.LocalDate;

public class TestEvent {
    private final int id;
    private final int id_sample;
    private final int id_teacher;
    private final int id_class;
    private LocalDate date_start;
    private LocalDate date_verification;
    private int id_status;

    public TestEvent(int id, int id_sample, int id_teacher, int id_class) {

        this.id = id;
        this.id_sample = id_sample;
        this.id_teacher = id_teacher;
        this.id_class = id_class;
    }

    public int getId() {
        return id;
    }

    public int getId_sample() {
        return id_sample;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public int getId_class() {
        return id_class;
    }

    public LocalDate getDate_start() {
        return date_start;
    }

    public void setDate_start(LocalDate date_start) {
        this.date_start = date_start;
    }

    public LocalDate getDate_verification() {
        return date_verification;
    }

    public void setDate_verification(LocalDate date_verification) {
        this.date_verification = date_verification;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }


}
