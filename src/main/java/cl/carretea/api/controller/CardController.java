package cl.carretea.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.carretea.api.model.Card;
import cl.carretea.api.model.CardCompany;
import cl.carretea.api.model.CardInstitution;
import cl.carretea.api.model.CardType;
import cl.carretea.api.repository.CustomCardRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/card")
public class CardController {

	@Autowired
	CustomCardRepository customRepo;
	
	@GetMapping("/types")
	public List<CardType> findAll(){
		List<CardType> cardtypesList = customRepo.findAll();
		return cardtypesList;
	}
	
	@GetMapping("/companies")
	public List<CardCompany> findAllCompanies(){
		List<CardCompany> cardtypesList = customRepo.findAllCompanies();
		return cardtypesList;
	}
	
	@GetMapping("/grouped")
	public List<CardInstitution> findAllByInstitution(){
		Map<String, List<Card>> cardtypesList = customRepo.findAllByInstitution();	
		List<CardInstitution> cardInstitutionList = new ArrayList<>();
		for (Entry<String, List<Card>> entry : cardtypesList.entrySet()) {
            CardInstitution card = new CardInstitution(entry.getKey(), entry.getValue());
            cardInstitutionList.add(card);
        }
		return cardInstitutionList;
	}
	
	@GetMapping("/sorted")
	public List<Card> findAllCards(){
		List<Card> cards = customRepo.findRepresentatives();
		return cards;
	}
}
