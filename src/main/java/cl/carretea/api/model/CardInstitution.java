package cl.carretea.api.model;

import java.util.List;

public class CardInstitution {
	String name;
	List<Card> cards;
	
	
	
	public CardInstitution(String name, List<Card> cards) {
		super();
		this.name = name;
		this.cards = cards;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	@Override
	public String toString() {
		return "CardInstitution [name=" + name + ", cards=" + cards + "]";
	}
	
	

}
