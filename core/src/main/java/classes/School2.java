package classes;

public class School2 {
    private final int id;
    private final String name;
    private final Region region;
    private final City city;
    private final SchoolType schoolTypeId;

    public School2(int id, String name, Region region, City city, SchoolType schoolTypeId) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.city = city;
        this.schoolTypeId = schoolTypeId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Region getRegion() {
        return region;
    }

    public City getCity() {
        return city;
    }

    public SchoolType getSchoolTypeId() {
        return schoolTypeId;
    }
}
