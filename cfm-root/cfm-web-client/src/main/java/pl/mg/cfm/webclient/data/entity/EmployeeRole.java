package pl.mg.cfm.webclient.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "employee_role")
@NamedQueries({
        @NamedQuery(name = "employeeRole.getAll", query = "SELECT e FROM EmployeeRole e")
})
public class EmployeeRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role", fetch = FetchType.EAGER)
    private Collection<Employee> employees = new ArrayList<Employee>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "EmployeeRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
               /* ", employees=" + employees +*/
                '}';
    }
}
