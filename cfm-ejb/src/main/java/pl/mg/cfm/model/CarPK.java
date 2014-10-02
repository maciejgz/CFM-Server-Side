package pl.mg.cfm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class CarPK implements Serializable {
    
    @Id
    @Column(name = "id")
    protected Integer id;

    @Id
    @Column(name = "car_id")
    protected Integer car_id;

    public CarPK() {
    };

    public CarPK(Integer id, Integer carId) {
        this.id = id;
        this.car_id = carId;
    }
}
