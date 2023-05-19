package service;

import lombok.Getter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Getter
public class UrlBuilder{
    private URL url;
    public UrlBuilder(String base, Map<String, String> parameters) throws MalformedURLException {
        StringBuilder urlString = new StringBuilder();
        urlString.append(base);
        urlString.append("?");
        urlString.append("&lon="+parameters.get("lon"));
        urlString.append("&lat="+parameters.get("lat"));
        urlString.append("&appid="+parameters.get("appid"));

        url = new URL(urlString.toString());
    }
}
