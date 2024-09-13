package com.csc340.Somethingelse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class SomethingelseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SomethingelseApplication.class, args);
	}

	@GetMapping("/food-image")
		public String getFoodImage() {
			String url = "https://foodish-api.com/api/";
			RestTemplate restTemplate = new RestTemplate();


			try {
				String jsonResponse = restTemplate.getForObject(url, String.class);
				ObjectMapper objectMapper = new ObjectMapper();
				Foodish foodish = objectMapper.readValue(jsonResponse, Foodish.class);

				return "Food Image URL: " + foodish.getImage();
			} catch (Exception e) {
				e.printStackTrace();
				return "error retrieving food image";
			}
		}

	public static class Foodish {
		private String image;

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}
	}
}
