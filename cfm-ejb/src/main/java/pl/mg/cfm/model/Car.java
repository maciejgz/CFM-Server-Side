package pl.mg.cfm.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CarPK carPk;

    @Column(name = "distance")
    private long distance;
    
    @Column(name="latitude")
    private Double latitude;

    @Column(name="longitude")
    private Double longitude;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="car_owner_id")
    private Employee owner;

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }


}
