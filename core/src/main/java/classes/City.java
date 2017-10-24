package classes;

public class City {

    private final int id;
    private Region region;
    private int regionId;
    private final String name;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public City(int id, Region region, String name) {
        this.id = id;
        this.region = region;
        this.name = name;
    }

    public City(int id, int regionId, String name) {
        this.id = id;
        this.regionId = regionId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRegionId() { return regionId; }
}
