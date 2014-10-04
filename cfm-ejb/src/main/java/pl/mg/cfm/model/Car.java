package pl.mg.cfm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CarPK carPk;

    @Column(name = "distance")
    private long distance;

    public CarPK getCarPk() {
        return carPk;
    }

    public void setCarPk(CarPK carPk) {
        this.carPk = carPk;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }


}
