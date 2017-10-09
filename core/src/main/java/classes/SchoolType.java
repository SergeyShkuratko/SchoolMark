package classes;

public class SchoolType {
    private final int id;
    private final String typeName;

    public SchoolType(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }
}
