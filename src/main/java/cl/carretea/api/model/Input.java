package cl.carretea.api.model;

public class Input {
	String address;
	double latitude;
	double longitude;

	public Input() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

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
		return "Input [address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	
}
