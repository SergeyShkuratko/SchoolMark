package classes.dto;

public class SchoolClassDTO {

    public int id;
    public int num;
    public String name;
    public int schoolId;

    public SchoolClassDTO(int num, String name, int schoolId) {
        this.num = num;
        this.name = name;
        this.schoolId = schoolId;
    }

    public SchoolClassDTO(int id, int num, String name, int schoolId) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.schoolId = schoolId;
    }

    public int getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public int getSchoolId() {
        return schoolId;
    }
}
