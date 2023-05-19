package models;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class City {
    private String lat;
    private String lon;
    private String name;

    public City(String lat, String lon, String name) {
        this.lat = lat;
        this.lon = lon;
        this.name = name;
    }
}
