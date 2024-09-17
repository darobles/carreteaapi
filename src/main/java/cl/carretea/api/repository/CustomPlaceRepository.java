package cl.carretea.api.repository;

import java.util.List;

import cl.carretea.api.model.Find;
import cl.carretea.api.model.Place;

public interface CustomPlaceRepository {
    
    void updateItemQuantity(String name, float newQuantity);
    
    List<Place> findByQuery(Find finder);
    
    List<Place> findByGeonear(Find finder);
    
    List<Place> findAll();

}