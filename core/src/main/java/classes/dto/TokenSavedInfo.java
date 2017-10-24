package classes.dto;

import classes.Role;

public class TokenSavedInfo {
    public String token;
    public Role role;
    public String regionName;
    public String cityName;
    public String schoolName;
    public String className;

    public TokenSavedInfo(String token, Role role, String regionName, String cityName, String schoolName, String className) {
        this.token = token;
        this.role = role;
        this.regionName = regionName;
        this.cityName = cityName;
        this.schoolName = schoolName;
        this.className = className;
    }

    public String getToken() {
        return token;
    }

    public Role getRole() {
        return role;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getClassName() {
        return className;
    }
}
