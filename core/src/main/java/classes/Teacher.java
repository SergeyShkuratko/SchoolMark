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
    private final School school;
    private final List<SubjectTeacherConnector> subject;
    private final int minClass;
    private final int maxClass;
    //TODO: квалификаций у учителя может быть много и каждая из них связана с предметом
    private final String qualification;

    public Teacher(int id, int userId, String lastname, String firstname, String patronymic, School school, Collection<SubjectTeacherConnector> subject, int minClass, int maxClass, String qualification) {
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

    /**
     * @return фамилия учителя + инициалы
     */
    public String getFullName() {
        return lastname + " "
                + firstname.substring(0, 1) + ". "
                + patronymic.substring(0, 1) + ".";
    }
}
