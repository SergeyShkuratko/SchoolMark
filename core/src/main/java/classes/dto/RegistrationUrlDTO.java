package classes.dto;

import classes.Role;

public class RegistrationUrlDTO {
    private String url;
    private Role role;
    private int schoolId;
    private int classId;

    public RegistrationUrlDTO(String url, String roleName, int schoolId, int classId) {
        this.url = url;
        this.role = Role.valueOf(roleName);
        this.schoolId = schoolId;
        this.classId = classId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Role getRole() {
        return role;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public int getClassId() {
        return classId;
    }
}
