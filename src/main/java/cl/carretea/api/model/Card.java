package cl.carretea.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cards")
public class Card {
	@Id
	String _id;
	int id;
	String name;
	String institution;
	String extra_name;
	String image;
	String url;
	String url_discount;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getExtra_name() {
		return extra_name;
	}
	public void setExtra_name(String extra_name) {
		this.extra_name = extra_name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl_discount() {
		return url_discount;
	}
	public void setUrl_discount(String url_discount) {
		this.url_discount = url_discount;
	}
	@Override
	public String toString() {
		return "Card [_id=" + _id + ", id=" + id + ", name=" + name + ", institution=" + institution + ", extra_name="
				+ extra_name + ", image=" + image + ", url=" + url + ", url_discount=" + url_discount + "]";
	}

	
	
	
	
}
