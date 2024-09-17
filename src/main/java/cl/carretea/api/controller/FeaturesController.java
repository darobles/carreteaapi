package cl.carretea.api.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.carretea.api.model.InputImage;
import cl.carretea.api.model.Product;
import cl.carretea.api.model.Receipt;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/features")
public class FeaturesController {
	String apiKey = "apiKey";
	
	@PostMapping("/splitreceipt")
	public Receipt getReceipt(@RequestBody InputImage json){
        String base64Image = json.getImage();
        Receipt receipt = new Receipt();
        // Crear el objeto JSON para el payload
        JSONObject imageUrlObject = new JSONObject();
        imageUrlObject.put("url", "data:image/jpeg;base64," + base64Image);

        JSONArray messagesArray = new JSONArray();
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");

        JSONArray contentArray = new JSONArray();
        JSONObject textObject = new JSONObject();
        textObject.put("type", "text");
        textObject.put("text", "Extrae la información de los productos consumidos, la cantidad y el costo unitario de estos en pesos chilenos. Además incluye la propina, los descuentos y total de la siguiente boleta. Agrupa los items iguales, usa los siguientes nombres para los campos: items,nombre,costo_unitario,cantidad, propina y total.La propina es normalmente 10% del consumo, se debe validar o recalcular. Retorna la información como un json");
        contentArray.put(textObject);

        JSONObject imageObject = new JSONObject();
        imageObject.put("type", "image_url");
        imageObject.put("image_url", imageUrlObject);
        contentArray.put(imageObject);

        userMessage.put("content", contentArray);
        messagesArray.put(userMessage);

        JSONObject payload = new JSONObject();
        payload.put("model", "gpt-4o-mini");
        payload.put("messages", messagesArray);
        payload.put("max_tokens", 1000);

        // Crear la solicitud HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();

        // Enviar la solicitud y obtener la respuesta
        HttpResponse<String> response;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			String jsonResponse = response.body();
			JSONObject jsonBody = new JSONObject(jsonResponse);
			System.out.println(jsonBody);
			JSONArray test = jsonBody.getJSONArray("choices");
			JSONObject resp = (JSONObject)test.get(0);
	        String[] split = resp.getJSONObject("message").getString("content").replace("\n```", "").split("json\n");
	        JSONObject jsonObject = new JSONObject(split[1]);
	        JSONArray products = (JSONArray) jsonObject.get("items");
	       
	        List<Product> productsList = new ArrayList();
	        for(int i = 0; i < products.length(); i++) {
	        	JSONObject prod = products.getJSONObject(i);
	        	Product product = new Product();
	        	product.setName(prod.getString("nombre"));
	        	product.setPrice(prod.getInt("costo_unitario"));
	        	product.setQuantity(prod.getInt("cantidad"));
	        	productsList.add(product);
	        }
	        
	        receipt.setProducts(productsList);
	        if(jsonObject.has("propina"))
	        	receipt.setTip(jsonObject.getInt("propina"));
	        if(jsonObject.has("total"))
	        	receipt.setTotal_payed(jsonObject.getInt("total"));
	        if(jsonObject.has("descuento"))
	        	receipt.setDiscount(jsonObject.getInt("descuento"));
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return receipt;
	}

}
