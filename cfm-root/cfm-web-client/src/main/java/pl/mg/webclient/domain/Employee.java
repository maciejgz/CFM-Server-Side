package pl.mg.webclient.domain;

public class Employee {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + "]";
    }

    private Employee() {
    };
}
