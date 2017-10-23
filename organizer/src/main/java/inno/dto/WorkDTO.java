package inno.dto;

public class WorkDTO {
    int id;
    int studentId;
    String studentFullName;
    String status;

    public WorkDTO(int id, int studentId, String studentFullName, String status) {
        this.id = id;
        this.studentId = studentId;
        this.studentFullName = studentFullName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentFullname() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
