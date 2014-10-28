package pl.mg.cfm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class CarPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "car_id")
    protected String car_id;

    public CarPK() {
    };

    public CarPK(String carId) {
        this.car_id = carId;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }
}
