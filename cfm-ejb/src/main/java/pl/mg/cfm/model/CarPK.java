package pl.mg.cfm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CarPK implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    protected Integer id;

    @Column(name = "car_id")
    protected String car_id;

    public CarPK() {
    };

    public CarPK(Integer id, String carId) {
        this.id = id;
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
}
