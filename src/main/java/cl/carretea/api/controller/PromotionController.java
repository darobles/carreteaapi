package cl.carretea.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import cl.carretea.api.model.ApiResponse;
import cl.carretea.api.model.Card;
import cl.carretea.api.model.Find;
import cl.carretea.api.model.Place;
import cl.carretea.api.model.Promotion;
//import cl.carretea.api.repository.CardRepository;
import cl.carretea.api.repository.CustomCardRepository;
import cl.carretea.api.repository.CustomPlaceRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/promotion")
public class PromotionController {
	
	@Autowired
    CustomPlaceRepository customRepo;
	@Autowired
	CustomCardRepository customCardRepo;
	

	@GetMapping("")
	public ResponseEntity<ApiResponse> newBenefit(){
		ApiResponse json = new ApiResponse();
		json.setMessage("ok");
		return ResponseEntity.ok(json);
	}

	@PostMapping("/find")
	public List<Place> findAll(@RequestBody Find finder){
		List<Place> placeList = customRepo.findByGeonear(finder);
		return placeList;
	}
	@GetMapping("/cards")
	public List<Card> getCards(){
		List<Card> cards = customCardRepo.findRepresentatives();
		return cards;
	}
}
