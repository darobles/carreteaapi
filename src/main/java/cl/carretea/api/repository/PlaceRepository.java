package cl.carretea.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cl.carretea.api.model.Place;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String>{

}
