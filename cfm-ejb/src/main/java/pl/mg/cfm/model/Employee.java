package pl.mg.cfm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @Column(name="idemployee")
    private int idemployee;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="second_name")
    private String secondName;
    
    //relacja jest wiele do jednego, bo wielu pracowników moze byc przypisanych do jednego typu
    @ManyToOne
    @JoinColumn(name = "id")
    private EmployeeRole role;
    
    @Column(name = "password")
    private String password;
    
    

    public int getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(int idemployee) {
        this.idemployee = idemployee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
