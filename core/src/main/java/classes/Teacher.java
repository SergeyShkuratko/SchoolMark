package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Teacher extends User {
    private final int id;
    private final String lastname;
    private final String firstname;
    private final String patronymic;
    private School school;
    private List<SubjectTeacherConnector> subject;
    private int minClass;
    private int maxClass;
    private String qualification;

    //TODO вернуть тип subject перед комитом
    public Teacher(int id, int userId, String login, LocalDate regDate, String lastname, String firstname, String patronymic, School school, Collection<SubjectTeacherConnector> subject, int minClass, int maxClass, String qualification) {
        super(userId, login, regDate);
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.school = school;
        this.subject = new ArrayList<>();
        this.subject.addAll(subject);
        this.minClass = minClass;
        this.maxClass = maxClass;
        this.qualification = qualification;
    }

    public int getId() {
        return id;
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

    public School getSchool() {
        return school;
    }

    public List<SubjectTeacherConnector> getSubject() {
        return subject;
    }

    public int getMinClass() {
        return minClass;
    }

    public int getMaxClass() {
        return maxClass;
    }

    public String getQualification() {
        return qualification;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void setSubject(List<SubjectTeacherConnector> subject) {
        this.subject = subject;
    }

    public void setMinClass(int minClass) {
        this.minClass = minClass;
    }

    public void setMaxClass(int maxClass) {
        this.maxClass = maxClass;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    /**
     * @return фамилия учителя + инициалы
     */
    public String getFullName() {
        return lastname + " "
                + firstname.substring(0, 1) + ". "
                + patronymic.substring(0, 1) + ".";
    }
}
