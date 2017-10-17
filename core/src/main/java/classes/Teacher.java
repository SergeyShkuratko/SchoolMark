package classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Teacher {
    private final int id;
    private final int userId;
    private final String lastname;
    private final String firstname;
    private final String patronymic;
    private School school;
    private List<Subject> subject;
    private int minClass;
    private int maxClass;
    private String qualification;

    public Teacher(int id, int userId, String lastname, String firstname, String patronymic) {
        this.id = id;
        this.userId = userId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
    }

    //TODO вернуть тип subject перед комитом
    public Teacher(int id, int userId, String lastname, String firstname, String patronymic, School school, Collection<Subject> subject, int minClass, int maxClass, String qualification) {
        this.id = id;
        this.userId = userId;
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

    public School getSchool() {
        return school;
    }

    public List<Subject> getSubject() {
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

    public void setSubject(List<Subject> subject) {
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
