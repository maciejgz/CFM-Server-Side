package pl.mg.cfm.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

    @EmbeddedId
    private CarPK carPk;

    @Column(name = "distance")
    private long distance;

}
