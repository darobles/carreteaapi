package cl.carretea.api.model;

import java.util.List;

public class Receipt {

	List<Product> products;
	int tip;
	double discount;
	int total_payed;
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public int getTip() {
		return tip;
	}
	public void setTip(int tip) {
		this.tip = tip;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public int getTotal_payed() {
		return total_payed;
	}
	public void setTotal_payed(int total_payed) {
		this.total_payed = total_payed;
	}
	@Override
	public String toString() {
		return "Receipt [products=" + products + ", tip=" + tip + ", discount=" + discount + ", total_payed="
				+ total_payed + "]";
	}
	
	
}
