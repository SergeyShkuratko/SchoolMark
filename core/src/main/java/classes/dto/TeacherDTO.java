package classes.dto;

public class TeacherDTO {

    public int id;
    public int userId;
    public String lastname;
    public String firstname;
    public String patronymic;
    public int school;

    public TeacherDTO(int id, int userId, String lastname, String firstname, String patronymic, int school) {
        this.id = id;
        this.userId = userId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
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

    public int getSchool() {
        return school;
    }
}
