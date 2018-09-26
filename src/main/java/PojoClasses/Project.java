package PojoClasses;

public class Project implements Pojo {

    private String id;

    public Project(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
