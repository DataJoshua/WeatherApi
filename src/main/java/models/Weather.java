package models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Getter@Setter@ToString
public class Weather {

    private String temp;
    private String feelsLike;
    private String minTemp;
    private String maxTemp;
    private String humidity;
    private City city;

    public Weather(JsonNode data, City city) {
        this.city = city;
        JsonNode main = data.at("/main");

        this.temp = main.get("temp").asText();
        this.feelsLike = main.get("feels_like").asText();
        this.minTemp = main.get("temp_min").asText();
        this.maxTemp = main.get("temp_max").asText();
        this.humidity = main.get("humidity").asText();
    }
}
