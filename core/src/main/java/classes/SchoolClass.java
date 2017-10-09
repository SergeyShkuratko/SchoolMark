package classes;

public class SchoolClass {
    private final int id;
    private final int num;
    private final String name;

    public SchoolClass(int id, int num, String name) {
        this.id = id;
        this.num = num;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }
}
