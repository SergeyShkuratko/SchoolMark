package studentmodule.dto;

public class DTOVariant {
    private int id;
    private String name;

    public DTOVariant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
