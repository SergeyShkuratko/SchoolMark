package classes;

public class School {
    private final int id;
    private final String name;
    private String region;
    private String city;
    private City cityObj;
    private final SchoolType schoolTypeId;

    public School(int id, String name, String region, String city, SchoolType schoolTypeId) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.city = city;
        this.schoolTypeId = schoolTypeId;
    }

    public School(int id, String name, City cityObj, SchoolType schoolTypeId) {
        this.id = id;
        this.name = name;
        this.cityObj = cityObj;
        this.schoolTypeId = schoolTypeId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public SchoolType getSchoolTypeId() {
        return schoolTypeId;
    }
}
