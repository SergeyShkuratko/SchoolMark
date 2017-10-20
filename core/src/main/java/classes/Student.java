package classes;

import java.time.LocalDate;

public class Student extends User {
    private final int id;
    private final String lastName;
    private final String firstName;
    private final String patronymic;
    private SchoolClass schoolClass;
    private final School school;

    public Student(int id, int userId, String login, LocalDate regDate, String lastName, String firstName, String patronymic, SchoolClass schoolClass, School school) {
        super(userId, login, regDate);
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.schoolClass = schoolClass;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public School getSchool() {
        return school;
    }
}
