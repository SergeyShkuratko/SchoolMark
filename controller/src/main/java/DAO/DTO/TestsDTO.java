package DAO.DTO;

import java.sql.Date;

public class TestsDTO {
    public int id;

    public int classNum;

    public String subjectName;

    public Date deadLine;

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
}