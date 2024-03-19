package nus.iss.day17.service;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import nus.iss.day17.model.GiphyImage;

@Service
public class GiphyService {
    
    public static final String SEARCH_URL = "https://api.giphy.com/v1/gifs/search";

    @Value("${giphy.key}")
    private String apiKey;

    public List<GiphyImage> search(String term) {
        String url = UriComponentsBuilder
            .fromUriString(SEARCH_URL)
            .queryParam("api_key", apiKey)
            .queryParam("q", term)
            .queryParam("limit", 5)
            .toUriString();
        
        System.out.println("URL >>>> " + url);

        // make the GET request
        RequestEntity<Void> req = RequestEntity.get(url).build();

        // make the call
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        System.out.println("Response: Status code >>>> " + resp.getStatusCode().value());
        // System.out.println("Response: Payload >>>> " + resp.getBody());

        List<GiphyImage> images = new LinkedList<>();

        // process the body
        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject giphyResp = reader.readObject();
        JsonArray data = giphyResp.getJsonArray("data");
        for (int i = 0; i < data.size(); i++) {
            JsonObject element = data.get(i).asJsonObject();
            JsonObject imgs = element.getJsonObject("images");
            JsonObject fixedWidth = imgs.getJsonObject("fixed_width");
            String imgUrl = fixedWidth.getString("url");
            
            GiphyImage giphyImage = new GiphyImage(element.getString("title"), imgUrl);
            images.add(giphyImage);
        }

        return images;
    }
}
