package classes;

public class Subject {
    private final int id;
    private final String name;

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean sameAs(Subject that) {
        if (!name.equals(that.getName())) {
            return false;
        }
        return true;
    }

}
