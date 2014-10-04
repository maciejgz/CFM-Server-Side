package pl.mg.cfm.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee_role")
public class EmployeeRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    
    @OneToMany
    private Set<Employee> employees;


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


    public Set<Employee> getEmployees() {
        return employees;
    }


    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}