package classes;

public class Student {
    private final int id;
    private final int userId;
    private final String lastName;
    private final String firstName;
    private final String patronymic;
    private SchoolClass schoolClass;
    private final School school;

    public Student(int id, int userId, String lastName, String firstName, String patronymic, SchoolClass schoolClass, School school) {
        this.id = id;
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.schoolClass = schoolClass;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
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
