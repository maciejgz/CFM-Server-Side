package pl.mg.cfm.pojo;

public class CarPojo {

    private int id;
    private String carId;
    private long distance;
    private Double latitude;
    private Double longitude;
    private int ownerId;

    public CarPojo() {

    }

    public CarPojo(int id, String carId, long distance, Double latitude, Double longitude, int ownerId) {
        this.id = id;
        this.carId = carId;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("id=" + this.id + ";carID=" + this.carId + ";distance=" + distance
                + ";latitude=" + latitude + ";longitude=" + longitude + ";ownerID=" + ownerId);
        return buffer.toString();
    }

}
