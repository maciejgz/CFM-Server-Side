package pl.mg.cfm.webclient.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }
}
