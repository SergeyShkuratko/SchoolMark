package controller.dao.dto;

import java.sql.Date;

public class TestsDTO {
    private int id;

    private int classNum;

    private String subjectName;

    private Date deadLine;

    public TestsDTO(int id, int classNum, String subjectName, Date deadLine) {
        this.id = id;
        this.classNum = classNum;
        this.subjectName = subjectName;
        this.deadLine = deadLine;
    }

    public int getId() {
        return id;
    }

    public int getClassNum() {
        return classNum;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }
}