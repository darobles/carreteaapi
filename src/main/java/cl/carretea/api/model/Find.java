package cl.carretea.api.model;

import java.util.Arrays;
import java.util.List;

public class Find {

	double[] point;
	double rating = 0.0;
	List<Integer> card_id;
	List<String> institutions;
	List<Integer> promotion_days;
	int discount;
	int radius;
	boolean open;
	boolean featured;
	
	public double[] getPoint() {
		return point;
	}
	public void setPoint(double[] point) {
		this.point = point;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public List<Integer> getCard_id() {
		return card_id;
	}
	public void setCard_id(List<Integer> card_id) {
		this.card_id = card_id;
	}
	
	public List<String> getInstitutions() {
		return institutions;
	}
	public void setInstitutions(List<String> institutions) {
		this.institutions = institutions;
	}
	public List<Integer> getPromotion_days() {
		return promotion_days;
	}
	public void setPromotion_days(List<Integer> promotion_days) {
		this.promotion_days = promotion_days;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isFeatured() {
		return featured;
	}
	public void setFeatured(boolean featured) {
		this.featured = featured;
	}
	
	@Override
	public String toString() {
		return "Find [point=" + Arrays.toString(point) + ", rating=" + rating + ", card_id=" + card_id
				+ ", institutions=" + institutions + ", promotion_days=" + promotion_days + ", discount=" + discount
				+ ", radius=" + radius + ", open=" + open + ", featured=" + featured + "]";
	}

}
