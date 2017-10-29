package template.dto;

public class Teacher {
    private int id;
    private int userId;
    private String lastName;
    private String firstName;
    private String patronymic;
    private int schoolId;
    private int minClassNumber;
    private int maxClassNumber;

    public Teacher(int id, int userId, String lastName, String firstName, String patronymic, int schoolId, int minClassNumber, int maxClassNumber) {
        this.id = id;
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.schoolId = schoolId;
        this.minClassNumber = minClassNumber;
        this.maxClassNumber = maxClassNumber;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getMinClassNumber() {
        return minClassNumber;
    }

    public void setMinClassNumber(int minClassNumber) {
        this.minClassNumber = minClassNumber;
    }

    public int getMaxClassNumber() {
        return maxClassNumber;
    }

    public void setMaxClassNumber(int maxClassNumber) {
        this.maxClassNumber = maxClassNumber;
    }
}
