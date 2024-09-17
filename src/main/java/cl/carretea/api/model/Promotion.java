package cl.carretea.api.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("promotions")
public class Promotion {

	String _id;
	String description;
	int card_id;
	Date expiration;
	Date initial_date;
	String name_store;
	String url_description;
	String place_id;
	boolean featured;
	@Field(value = "cards")
	List<Card> cards;
	
	public Promotion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String get_id() {
		return _id;
	}


	public void set_id(String _id) {
		this._id = _id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getCard_id() {
		return card_id;
	}


	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}


	public Date getExpiration() {
		return expiration;
	}


	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	
	public Date getInitial_date() {
		return initial_date;
	}

	public void setInitial_date(Date initial_date) {
		this.initial_date = initial_date;
	}


	public String getName_store() {
		return name_store;
	}


	public void setName_store(String name_store) {
		this.name_store = name_store;
	}

	public String getUrl_description() {
		return url_description;
	}


	public void setUrl_description(String url_description) {
		this.url_description = url_description;
	}


	public String getPlace_id() {
		return place_id;
	}


	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}
	

	public boolean isFeatured() {
		return featured;
	}


	public void setFeatured(boolean featured) {
		this.featured = featured;
	}


	public List<Card> getCards() {
		return cards;
	}


	public void setCards(List<Card> cards) {
		this.cards = cards;
	}


	@Override
	public String toString() {
		return "Promotion [_id=" + _id + ", description=" + description + ", card_id=" + card_id + ", expiration="
				+ expiration + ", initial_date=" + initial_date + ", name_store=" + name_store + ", url_description="
				+ url_description + ", place_id=" + place_id + ", featured=" + featured + ", cards=" + cards + "]";
	}

	
	
	
}
