package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiService {
    private URL url;
    private HttpURLConnection con;

    public ApiService(UrlBuilder urlBuilder){
        this.url = urlBuilder.getUrl();

        try{
            con = (HttpURLConnection) url.openConnection();

            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
        }
        catch (IOException e){ System.out.println(e); }
    }

    public String makeRequest() throws IOException {

        System.out.println(con.getURL().toString());

        if(con.getResponseCode() == 200){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            return content.toString();
        }
        else{
            return "error with status:" + con.getResponseCode();
        }
    }
}

