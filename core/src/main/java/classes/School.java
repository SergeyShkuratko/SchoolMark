package classes;

public class School {
    private final int id;
    private final String name;
    private final String region;
    private final String city;
    private final int id_schoolType;

    public School(int id, String name, String region, String city, int id_schoolType) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.city = city;
        this.id_schoolType = id_schoolType;
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

    public int getId_schoolType() {
        return id_schoolType;
    }
}
