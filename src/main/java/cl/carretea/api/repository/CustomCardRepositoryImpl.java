package cl.carretea.api.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import cl.carretea.api.model.Card;
import cl.carretea.api.model.CardCompany;
import cl.carretea.api.model.CardType;

@Component
public class CustomCardRepositoryImpl implements CustomCardRepository{

    @Autowired
    MongoTemplate mongoTemplate;
	
	@Override
	public List<Card> findRepresentatives() {
		Query query = new Query(Criteria.where("representative").is(true));
		query.with(Sort.by(Sort.Direction.ASC, "institution"));
		List<Card> results = mongoTemplate.find(query, Card.class);
		return results;
	}

	@Override
	public List<CardType> findAll() {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.ASC, "name"));
		List<CardType> myClassList=  mongoTemplate.find(query, CardType.class);
		return  mongoTemplate.findAll(CardType.class);
	}
	
	@Override
	public Map<String, List<Card>> findAllByInstitution() {
		// TODO Auto-generated method stub
		List<Card> cards = mongoTemplate.findAll(Card.class);
		Map<String, List<Card>> cardssorted = cards.stream()
	            .collect(Collectors.groupingBy(Card::getInstitution));
		return cardssorted; 
	}
	
	@Override
	public List<CardCompany> findAllCompanies() {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.ASC, "name"));
		List<CardCompany> myClassList=  mongoTemplate.find(query, CardCompany.class);
		return  myClassList;
	}
}
