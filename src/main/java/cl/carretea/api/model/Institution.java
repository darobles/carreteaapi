package cl.carretea.api.model;

import java.util.List;

public class Institution {
	int id;
	String nombre;
	List<Card> cards;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Card> getCards() {
		return cards;
	}


	public void setCards(List<Card> cards) {
		this.cards = cards;
	}


	@Override
	public String toString() {
		return "Institution [id=" + id + ", nombre=" + nombre + ", cards=" + cards + "]";
	}
	
	
}
