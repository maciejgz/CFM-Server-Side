package pl.mg.cfm.webclient.domain;

import java.io.Serializable;

public class Car implements Serializable {

	private static final long serialVersionUID = 1L;

	private String carId;
	private Long distance;
	private Double latitude;
	private Double longitude;
	private Integer ownerId;

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", distance=" + distance + ", latitude="
				+ latitude + ", longitude=" + longitude + ", ownerId="
				+ ownerId + "]";
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
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

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
}
