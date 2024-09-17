package cl.carretea.api.repository;


import java.util.List;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.GeoNearOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Collation;
import com.mongodb.client.result.UpdateResult;

import cl.carretea.api.model.Find;
import cl.carretea.api.model.Place;

@Component
public class CustomPlaceRepositoryImpl implements CustomPlaceRepository {

    @Autowired
    MongoTemplate mongoTemplate;
    
    public void updateItemQuantity(String name, float newQuantity) {
        Query query = new Query(Criteria.where("name").is(name));
        Update update = new Update();
        update.set("user_ratings_total", newQuantity);
        
        UpdateResult result = mongoTemplate.updateFirst(query, update, Place.class);
        
        if(result == null)
            System.out.println("No documents updated");
        else
            System.out.println(result.getModifiedCount() + " document(s) updated..");

    }

	@Override
	public List<Place> findByQuery(Find finder) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("rating").gte(finder.getRating()).and("radius").lte(finder.getRadius()));

		List<Place> results = mongoTemplate.find(query, Place.class);
		
		return results;
	}

	@Override
	public List<Place> findByGeonear(Find finder) {
		// TODO Auto-generated method stub
		List<AggregationOperation> operations = new ArrayList<>();
		Point location = new Point(finder.getPoint()[1], finder.getPoint()[0]);

		double km = (double)finder.getRadius()/1000;
		Distance distance = new Distance(km, Metrics.KILOMETERS);
		NearQuery nearQuery = NearQuery.near(location).maxDistance(distance);
        GeoNearOperation geoNearOperation = Aggregation.geoNear(nearQuery, "distance");
        MatchOperation ratingFilter = Aggregation.match(Criteria.where("rating").gte(finder.getRating()));
        AggregationOperation lookupOperation = null;
        if(!finder.getPromotion_days().isEmpty())
        {
        	 lookupOperation = context -> new Document("$lookup",
                    new Document("from", "promotions_cards")
                            .append("let", new Document("nameVar", "$name_clean"))
                            .append("pipeline", Arrays.asList(
                                    new Document("$match",
                                            new Document("$and", Arrays.asList(
                                            		new Document("$expr", 
                                            			    new Document("$eq", Arrays.asList("$name_store_clean", "$$nameVar"))
                                            			),
                                                    new Document("expiration", 
                                                            new Document("$gt", new Date()) // Use ISODate for date comparisons
                                                    ),
                                                    new Document("days", 
                                                            new Document("$in",finder.getPromotion_days())
                                                    )
                                            ))
                                    ),
                                    new Document("$sort", new Document("featured", -1))
                            ))
                            .append("as", "promotions")
            );
        }
         else {
        	lookupOperation = context -> new Document("$lookup",
                     new Document("from", "promotions_cards")
                             .append("let", new Document("nameVar", "$name_clean"))
                             .append("pipeline", Arrays.asList(
                                     new Document("$match",
                                             new Document("$and", Arrays.asList(
                                                     new Document("$expr", 
                                                             new Document("$eq", Arrays.asList("$name_store_clean", "$$nameVar"))
                                                     ),
                                                     new Document("expiration", 
                                                             new Document("$gt", new Date()) // Use ISODate for date comparisons
                                                     )
                                             ))
                                     ),
                                     new Document("$sort", new Document("featured", -1))
                             ))
                             .append("as", "promotions")
             );
        	
        }
        
        operations.add(geoNearOperation);
        operations.add(ratingFilter);
        
        operations.add(lookupOperation);  
        if(!finder.getInstitutions().isEmpty() || finder.isFeatured() )
        {
        	MatchOperation cardPromoFilter;
        	if(finder.isFeatured())
        	{
        		operations.add(Aggregation.match(new Criteria()
        			    .orOperator(
        			            Criteria.where("promotions.institution").in(finder.getInstitutions()),
        			            Criteria.where("promotions.featured").gte(true)
        			        ).andOperator(Criteria.where("promotions.featured").gte(true))   			    
        				));
        	}
        	else {
        		cardPromoFilter = Aggregation.match(Criteria.where("promotions.institution").in(finder.getInstitutions()));
        		operations.add(cardPromoFilter);
        	}
        	
        	
        }
        

        Aggregation aggregation = Aggregation.newAggregation(operations);
        AggregationResults<Place> results = mongoTemplate.aggregate(aggregation, "bar", Place.class);
		return results.getMappedResults();
	}

	@Override
	public List<Place> findAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(Place.class);
	}

}