package classes;

public class Region {

    private final int id;
    private int num;
    private final String name;

    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Region(int id, int num, String name) {
        this.id = id;
        this.num = num;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNum() { return num; }
}
