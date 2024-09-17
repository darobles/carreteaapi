package cl.carretea.api.model;

import java.util.Arrays;

public class Prediction {
	String placeId;
	Geometry location;
	String formattedAddress;
	String[] types;
	
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public Geometry getLocation() {
		return location;
	}
	public void setLocation(Geometry location) {
		this.location = location;
	}
	public String getFormattedAddress() {
		return formattedAddress;
	}
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	
	@Override
	public String toString() {
		return "Place [placeId=" + placeId + ", location=" + location + ", formattedAddress=" + formattedAddress
				+ ", types=" + Arrays.toString(types) + "]";
	}
	
}
