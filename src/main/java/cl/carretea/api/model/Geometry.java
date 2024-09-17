package cl.carretea.api.model;

public class Geometry {
	
	double latitude;
	double longitude;
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Geometry [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
}
