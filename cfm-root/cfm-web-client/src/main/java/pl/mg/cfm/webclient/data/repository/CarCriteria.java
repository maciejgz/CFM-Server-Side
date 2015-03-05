package pl.mg.cfm.webclient.data.repository;

/**
 * Car searching criteria
 * Created by m on 2015-03-04.
 */
public class CarCriteria {

    private String carId = null;


    private Long minDistance = null;
    private Long maxDistance = null;


    private Double minLatitude = null;
    private Double maxLatitude = null;
    private Double minLongitude = null;
    private Double maxLongitude = null;


    private String ownerEmplyeeFirstName = null;
    private String ownerEmployeeLastName = null;
    private Integer ownerId = null;


    public String getOwnerEmplyeeFirstName() {
        return ownerEmplyeeFirstName;
    }

    public void setOwnerEmplyeeFirstName(String ownerEmplyeeFirstName) {
        this.ownerEmplyeeFirstName = ownerEmplyeeFirstName;
    }

    public String getOwnerEmployeeLastName() {
        return ownerEmployeeLastName;
    }

    public void setOwnerEmployeeLastName(String ownerEmployeeLastName) {
        this.ownerEmployeeLastName = ownerEmployeeLastName;
    }

    public CarCriteria() {
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Long getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(Long minDistance) {
        this.minDistance = minDistance;
    }

    public Long getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Long maxDistance) {
        this.maxDistance = maxDistance;
    }

    public Double getMinLatitude() {
        return minLatitude;
    }

    public void setMinLatitude(Double minLatitude) {
        this.minLatitude = minLatitude;
    }

    public Double getMaxLatitude() {
        return maxLatitude;
    }

    public void setMaxLatitude(Double maxLatitude) {
        this.maxLatitude = maxLatitude;
    }

    public Double getMinLongitude() {
        return minLongitude;
    }

    public void setMinLongitude(Double minLongitude) {
        this.minLongitude = minLongitude;
    }

    public Double getMaxLongitude() {
        return maxLongitude;
    }

    public void setMaxLongitude(Double maxLongitude) {
        this.maxLongitude = maxLongitude;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
