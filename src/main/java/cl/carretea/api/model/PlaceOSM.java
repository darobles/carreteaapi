package cl.carretea.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("view_promotion")
public class PlaceOSM {
	Geometry location;
	String locality;
	String city;
	String county;
	String country;
	String type;
	String street;
	String housenumber;
	String state;
	String district;
	int place_id;
	String display_name;
	String custom_display_name;
	
	
	public Geometry getLocation() {
		return location;
	}



	public void setLocation(Geometry location) {
		this.location = location;
	}



	public String getLocality() {
		return locality;
	}



	public void setLocality(String locality) {
		this.locality = locality;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getCounty() {
		return county;
	}



	public void setCounty(String county) {
		this.county = county;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getHousenumber() {
		return housenumber;
	}



	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}

	

	public String getDistrict() {
		return district;
	}



	public void setDistrict(String district) {
		this.district = district;
	}

	


	public int getPlace_id() {
		return place_id;
	}



	public void setPlace_id(int place_id) {
		this.place_id = place_id;
	}

	

	public String getDisplay_name() {
		return display_name;
	}



	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}



	public String getCustom_display_name() {
		return custom_display_name;
	}



	public void setCustom_display_name(String custom_display_name) {
		this.custom_display_name = custom_display_name;
	}



	@Override
	public String toString() {
		return "PlaceOSM [location=" + location + ", locality=" + locality + ", city=" + city + ", county=" + county
				+ ", country=" + country + ", type=" + type + ", street=" + street + ", housenumber=" + housenumber
				+ ", state=" + state + ", district=" + district + ", place_id=" + place_id + ", display_name="
				+ display_name + ", custom_display_name=" + custom_display_name + "]";
	}

	
}
