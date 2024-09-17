package cl.carretea.api.repository;

import java.util.List;
import java.util.Map;

import cl.carretea.api.model.Card;
import cl.carretea.api.model.CardCompany;
import cl.carretea.api.model.CardType;

public interface CustomCardRepository {
    
    List<Card> findRepresentatives();

	List<CardType> findAll();
	
	public Map<String, List<Card>> findAllByInstitution(); 
	
	List<CardCompany> findAllCompanies();
}