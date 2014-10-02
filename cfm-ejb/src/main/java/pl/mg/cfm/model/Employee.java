package pl.mg.cfm.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Employee {

    @Id
    @Column(name="idemployee")
    private int idemployee;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="second_name")
    private String secondName;
    
    
    
}
