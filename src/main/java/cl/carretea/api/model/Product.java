package cl.carretea.api.model;

public class Product {
	int quantity;
	String name;
	int price;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [quantity=" + quantity + ", name=" + name + ", price=" + price + "]";
	}
	
	
	
}
