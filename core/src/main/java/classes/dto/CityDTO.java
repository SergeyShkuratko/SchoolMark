package classes.dto;

public class CityDTO {
    private int id;
    private String regionName;
    private int regionId;
    private String name;

    public CityDTO(int id, String name, int regionId, String regionName) {
        this.id = id;
        this.regionName = regionName;
        this.regionId = regionId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getRegionName() {
        return regionName;
    }

    public int getRegionId() {
        return regionId;
    }

    public String getName() {
        return name;
    }
}
