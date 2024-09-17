package cl.carretea.api.model;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("view_promotion")
public class Place {

	@Id
	String _id;
	String business_status;
	Object geometry;
	String icon;
	String icon_background_color;
	String icon_mask_base_uri;
	String name;
	Object opening_hours;
	Object photos;
	String place_id;
	Object plus_code;
	Double rating;
	String reference;
	String scope;
	Object types;
	Integer user_ratings_total;
	String vicinity;
	String url;
	String website;
	String formatted_phone_number;
	Double distance;
	boolean serves_vegetarian_food = false;
	boolean wheelchair_accessible_entrance = false;
	@Field(value = "promotions")
	List<Promotion> promotions;
	
	
	
	public Place() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getBusiness_status() {
		return business_status;
	}
	public void setBusiness_status(String business_status) {
		this.business_status = business_status;
	}
	public Object getGeometry() {
		return geometry;
	}
	public void setGeometry(Object geometry) {
		this.geometry = geometry;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIcon_background_color() {
		return icon_background_color;
	}
	public void setIcon_background_color(String icon_background_color) {
		this.icon_background_color = icon_background_color;
	}
	public String getIcon_mask_base_uri() {
		return icon_mask_base_uri;
	}
	public void setIcon_mask_base_uri(String icon_mask_base_uri) {
		this.icon_mask_base_uri = icon_mask_base_uri;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getOpening_hours() {
		return opening_hours;
	}
	public void setOpening_hours(Object opening_hours) {
		this.opening_hours = opening_hours;
	}
	public Object getPhotos() {
		return photos;
	}
	public void setPhotos(Object photos) {
		this.photos = photos;
	}
	public String getPlace_id() {
		return place_id;
	}
	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}
	public Object getPlus_code() {
		return plus_code;
	}
	public void setPlus_code(Object plus_code) {
		this.plus_code = plus_code;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public Object getTypes() {
		return types;
	}
	public void setTypes(Object types) {
		this.types = types;
	}
	public Integer getUser_ratings_total() {
		return user_ratings_total;
	}
	public void setUser_ratings_total(Integer user_ratings_total) {
		this.user_ratings_total = user_ratings_total;
	}
	public String getVicinity() {
		return vicinity;
	}
	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}
	public List<Promotion> getPromotions() {
		return promotions;
	}
	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getFormatted_phone_number() {
		return formatted_phone_number;
	}
	public void setFormatted_phone_number(String formatted_phone_number) {
		this.formatted_phone_number = formatted_phone_number;
	}
	public boolean isServes_vegetarian_food() {
		return serves_vegetarian_food;
	}
	public void setServes_vegetarian_food(boolean serves_vegetarian_food) {
		this.serves_vegetarian_food = serves_vegetarian_food;
	}
	public boolean isWheelchair_accessible_entrance() {
		return wheelchair_accessible_entrance;
	}
	public void setWheelchair_accessible_entrance(boolean wheelchair_accessible_entrance) {
		this.wheelchair_accessible_entrance = wheelchair_accessible_entrance;
	}
	
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	@Override
	public String toString() {
		return "Place [_id=" + _id + ", business_status=" + business_status + ", geometry=" + geometry + ", icon="
				+ icon + ", icon_background_color=" + icon_background_color + ", icon_mask_base_uri="
				+ icon_mask_base_uri + ", name=" + name + ", opening_hours=" + opening_hours + ", photos=" + photos
				+ ", place_id=" + place_id + ", plus_code=" + plus_code + ", rating=" + rating + ", reference="
				+ reference + ", scope=" + scope + ", types=" + types + ", user_ratings_total=" + user_ratings_total
				+ ", vicinity=" + vicinity + ", url=" + url + ", website=" + website + ", formatted_phone_number="
				+ formatted_phone_number + ", distance=" + distance + ", serves_vegetarian_food="
				+ serves_vegetarian_food + ", wheelchair_accessible_entrance=" + wheelchair_accessible_entrance
				+ ", promotions=" + promotions + "]";
	}

	
	
	
	
}
