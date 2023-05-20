package models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Getter@ToString
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

    public String getAsHtml(){
        StringBuilder builder = new StringBuilder();

        builder.append("<h1> Weather in ").append(city.getName()).append(" is: ").append(temp).append(" Celsius </h1>")
                .append("<h3> feels Like: ").append(feelsLike).append("</h3>")
                .append("<p>Additional Info: </p>")
                .append("<ul> <li>").append(" Min Temp: ").append(minTemp).append("</li>")
                .append("<li>").append(" Max Temp: ").append(maxTemp).append("</li>")
                .append("<li>").append(" Humidity: ").append(humidity).append("</li>").append("</ul>");

        return  builder.toString();
    }
}
