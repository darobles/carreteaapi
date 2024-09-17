package cl.carretea.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.GeocodingResult;

import cl.carretea.api.model.Geometry;
import cl.carretea.api.model.Input;
import cl.carretea.api.model.Prediction;
import cl.carretea.api.repository.CustomPlaceRepository;
import cl.carretea.api.repository.PlaceRepository;
import cl.carretea.api.model.PlaceOSM;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/map")
public class MapController {
	static String API_KEY = "API_KEY";

	@Autowired
	CustomPlaceRepository customRepo;

	private final PlaceRepository placeRepo;

	public MapController(final PlaceRepository springDataRiceProductionRepository) {
		this.placeRepo = springDataRiceProductionRepository;
	}

	@PostMapping("/osmprediction")
	public List<Prediction> getPrediction(@RequestBody Input partialAddress) {
		List<Prediction> places = new ArrayList();
		GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
		try {
			AutocompletePrediction[] predictions = PlacesApi
					.queryAutocomplete(context, partialAddress.getAddress() + " , Chile").await();

			for (AutocompletePrediction prediction : predictions) {
				GeocodingResult[] results = GeocodingApi.geocode(context, prediction.description).await();
				Prediction place = new Prediction();
				place.setPlaceId(prediction.placeId);
				Geometry geometry = new Geometry();
				geometry.setLatitude(results[0].geometry.location.lat);
				geometry.setLongitude(results[0].geometry.location.lng);
				place.setLocation(geometry);
				place.setFormattedAddress(results[0].formattedAddress);
				// place.setTypes(results[0].types);
				places.add(place);
			}
		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();
		}
		return places;
	}

	@PostMapping("/reversegeocode")
	public PlaceOSM reversegeoCode(@RequestBody Input input) {
		/* desbloquear cuando se acaben las otras peticiones */
		String url = "https://geocode.maps.co/reverse?lat="+input.getLatitude()+"&lon="+input.getLongitude()+"&api_key=myKey";
		//String url = "https://us1.locationiq.com/v1/reverse?key=pk&lat=" + input.getLatitude() +"&lon=" + input.getLatitude() +"&format=json&";
		PlaceOSM place = new PlaceOSM();
		try {
			URL obj = new URL(url);

			// Abrir conexión
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// Establecer el método de petición
			con.setRequestMethod("GET");

			// Establecer propiedades de la solicitud (opcional)
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // éxito
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// Convertir la respuesta a JSON
				// JsonObject jsonResponse = new Gson().fromJson(response.toString(),
				// JsonArray.class);
				JsonObject object = new Gson().fromJson(response.toString(), JsonObject.class);

				Geometry geometry = new Geometry();
				place.setPlace_id(object.get("place_id").getAsInt());
				geometry.setLatitude(object.get("lat").getAsDouble());
				geometry.setLongitude(object.get("lon").getAsDouble());
				place.setLocation(geometry);
				place.setDisplay_name(object.get("display_name").getAsString());
				JsonObject address = object.get("address").getAsJsonObject();
				if(address.get("road") != null  && address.get("house_number") != null && address.get("county") != null)
					place.setCustom_display_name(address.get("road").getAsString() + " " + address.get("house_number").getAsString() + " " + address.get("county").getAsString());
				else {
					place.setCustom_display_name(object.get("display_name").getAsString());
				}
				// place.setTypes(results[0].types);

			} else {
				System.out.println("Petición GET fallida.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return place;
	}
	
	@PostMapping("/osmprediction2")
	public List<Prediction> getOsmPrediction(@RequestBody Input partialAddress) {
		List<Prediction> places = new ArrayList();

		System.out.println(partialAddress);
		try {
			String url = "https://nominatim.openstreetmap.org/search.php?q="
					+ partialAddress.getAddress().replaceAll(" ", "%20") + "&accept-language=es&countrycodes=cl&format=jsonv2";
			// Crear objeto URL
			URL obj = new URL(url);

			// Abrir conexión
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// Establecer el método de petición
			con.setRequestMethod("GET");

			// Establecer propiedades de la solicitud (opcional)
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			System.out.println(responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // éxito
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// Convertir la respuesta a JSON
				// JsonObject jsonResponse = new Gson().fromJson(response.toString(),
				// JsonArray.class);
				JsonArray jsonArr = new Gson().fromJson(response.toString(), JsonArray.class);
				for (JsonElement location : jsonArr) {
					JsonObject properties = location.getAsJsonObject();
					Prediction place = new Prediction();
					place.setPlaceId(properties.get("place_id").getAsString());
					Geometry geometry = new Geometry();
					geometry.setLatitude(properties.get("lat").getAsDouble());
					geometry.setLongitude(properties.get("lon").getAsDouble());
					place.setLocation(geometry);
					String name[] = properties.get("display_name").getAsString().split(",");
					place.setFormattedAddress(properties.get("display_name").getAsString());
					/*String region = "";
					String par_number = name[0];
					String street = "";
					String province = "";
					try {
						Integer.parseInt(name[name.length-2].trim());
						region = name[name.length-3];
						province = name[name.length-5];
					}
					catch(Exception ex)
					{	
						region = name[name.length-2];
						province = name[name.length-4];
					}
					
					try {
						Integer.parseInt(name[0].trim());
						street = name[1] + " " + name[0] + province +", " + region;
					}
					catch(Exception ex)
					{
						street = name[0] + " " + name[1] + province + ", " + region;
					}
					place.setFormattedAddress(street);*/
					// place.setTypes(results[0].types);
					places.add(place);
				}
			} else {
				System.out.println("Petición GET fallida.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return places;
	}

	@PostMapping("/geocode")
	public List<PlaceOSM> geocode(@RequestBody Input partialAddress) {
		List<PlaceOSM> places = new ArrayList();
		try {
			// URL de la API que quieres consultar
			String[] partialQuery = partialAddress.getAddress().split(",");
			String query = "";
			int i = 0;
			for (String partial : partialQuery) {

				if (i == 1) {
					String[] comunne_partial = partial.split(" ");
					if (comunne_partial != null && comunne_partial.length > 1) {
						for (int j = 1; j < comunne_partial.length; j++) {
							query = query + "+" + comunne_partial[j];
						}

					} else {
						query = query + partial;
					}

					break;
				} else {
					query = query + partial;
				}
				i++;
			}
			String url = "https://photon.komoot.io/api/?q=" + query.replaceAll(" ", "+") + "+chile";
			System.out.println(url);
			// Crear objeto URL
			URL obj = new URL(url);

			// Abrir conexión
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// Establecer el método de petición
			con.setRequestMethod("GET");

			// Establecer propiedades de la solicitud (opcional)
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // éxito
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// Convertir la respuesta a JSON
				JsonObject jsonResponse = new Gson().fromJson(response.toString(), JsonObject.class);
				JsonArray jsonArr = jsonResponse.get("features").getAsJsonArray();
				for (JsonElement location : jsonArr) {
					JsonObject properties = location.getAsJsonObject().get("properties").getAsJsonObject();
					PlaceOSM place = new PlaceOSM();
					JsonObject geometry = location.getAsJsonObject().get("geometry").getAsJsonObject();
					if (geometry != null) {
						JsonArray coordinates = geometry.getAsJsonObject().get("coordinates").getAsJsonArray();
						Geometry coords = new Geometry();
						coords.setLatitude(coordinates.get(1).getAsDouble());
						coords.setLongitude(coordinates.get(0).getAsDouble());
						place.setLocation(coords);
					}

					JsonElement country = properties.get("country");
					if (country != null)
						place.setCountry(properties.get("country").getAsString());
					JsonElement city = properties.get("city");
					if (city != null)
						place.setCity(city.getAsString());
					JsonElement county = properties.get("county");
					if (county != null)
						place.setCounty(properties.get("county").getAsString());

					JsonElement state = properties.get("state");
					if (state != null)
						place.setState(properties.get("state").getAsString());
					JsonElement street = properties.get("street");
					if (street != null)
						place.setStreet(properties.get("street").getAsString());
					JsonElement housenumber = properties.get("housenumber");
					if (housenumber != null)
						place.setHousenumber(properties.get("housenumber").getAsString());
					JsonElement district = properties.get("district");
					if (district != null)
						place.setDistrict(properties.get("district").getAsString());
					places.add(place);
				}
				// Imprimir el JSON
				// Imprime con indentación de 4 espacios
			} else {
				System.out.println("Petición GET fallida.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return places;
	}

	public void getCoords(@RequestBody String address) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();

		try {
			GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
			if (results.length > 0) {
				System.out.println(results[0]);
			} else {
				System.out.println("No se encontraron coordenadas para: " + address);
			}
		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();
		}

	}

}
