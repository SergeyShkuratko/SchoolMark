package classes.dto;

import classes.City;
import classes.Region;
import classes.SchoolType;

public class SchoolDTO {

    public int id;
    public String name;
    public int regionId;
    public int cityId;
    public String regionName;
    public String cityName;
    public String schoolType;
    public int schoolTypeId;

    public SchoolDTO(int id, String name,
                     int regionId, int cityId,
                     String regionName, String cityName,
                     String schoolType,
                     int schoolTypeId) {
        this.id = id;
        this.name = name;
        this.regionId = regionId;
        this.cityId = cityId;
        this.regionName = regionName;
        this.cityName = cityName;
        this.schoolType = schoolType;
        this.schoolTypeId = schoolTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getRegionId() {
        return regionId;
    }

    public int getCityId() {
        return cityId;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public int getSchoolTypeId() {
        return schoolTypeId;
    }
}
