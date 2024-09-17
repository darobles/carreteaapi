package cl.carretea.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.carretea.api.model.Place;
import cl.carretea.api.repository.CustomPlaceRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/place")
public class PlaceController {

	@Autowired
    CustomPlaceRepository customRepo;
	
	@GetMapping("")
	public List<Place> findAll(){
		List<Place> placeList = customRepo.findAll();
		return placeList;
	}
}
