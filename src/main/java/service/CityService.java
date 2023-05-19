package service;

import models.City;

import java.util.HashMap;
import java.util.Map;

public class CityService {
    public static Map<String, String> setCityCoordinates(City city){
        Map<String, String> map = new HashMap<>();

        map.put("lat", city.getLat());
        map.put("lon", city.getLon());

        return map;
    }
}
