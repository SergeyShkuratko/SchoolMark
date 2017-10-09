package classes;

public class Teacher {
    private final int id;
    private final int id_user;
    private final String lastname;
    private final String firstname;
    private final String patronymic;
    private final int id_school;
    private final int id_subject;
    private final int min_class;
    private final int max_class;
    private final String qualification;

    public Teacher(int id, int id_user, String lastname, String firstname, String patronymic, int id_school, int id_subject, int min_class, int max_class, String qualification) {
        this.id = id;
        this.id_user = id_user;
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.id_school = id_school;
        this.id_subject = id_subject;
        this.min_class = min_class;
        this.max_class = max_class;
        this.qualification = qualification;
    }

    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getId_school() {
        return id_school;
    }

    public int getId_subject() {
        return id_subject;
    }

    public int getMin_class() {
        return min_class;
    }

    public int getMax_class() {
        return max_class;
    }

    public String getQualification() {
        return qualification;
    }
}
