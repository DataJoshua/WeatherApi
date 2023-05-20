package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.*;
import models.City;
import models.Email;
import models.Weather;
import service.ApiService;
import service.CityService;
import service.MailTrapService;
import service.UrlBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, MessagingException {

        Scanner scanner = new Scanner(System.in);

        String from = scanner.nextLine();
        String to = scanner.nextLine();

        City kazan = new City("55.7879", "49.1233", "Kazan");

        // set the lat and long as parameters for the request
        Map<String, String> parameters = CityService.setCityCoordinates(kazan);

        // set the api key
        parameters.put("appid", "f1955a2d3ad7e2119a0e5bcb6caa33f2");

        UrlBuilder url = new UrlBuilder("https://api.openweathermap.org/data/2.5/weather", parameters);

        ApiService service = new ApiService(url);

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode node =  objectMapper.readTree(service.makeRequest());

        Weather weather = new Weather(node, kazan);
        System.out.println(node);

        ArrayList<String> imgs = new ArrayList<>();
        imgs.add("src/main/java/assets/cat.jpg");

        Email email = new Email(from, to, "Teme",  weather.getAsHtml(), imgs);

        // Set MailTrap credentials
        MailTrapService mailTrapService = new MailTrapService("username","password", email);

        mailTrapService.send();

        // Ask for emails in the console


    }
}