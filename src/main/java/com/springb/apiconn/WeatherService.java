package com.springb.apiconn;

import com.springb.apiconn.repository.WeatherRepository;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


@Service("/ownService")
public class WeatherService {
    private String url="https://api.openweathermap.org/data/2.5/weather?appid=5c48904548d2583539b0550088c86d5a&q=";
    private OkHttpClient client;
    private Response response;
    private String CityName;

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);


    @Autowired
    WeatherRepository weatherRepository;

/*
    public String getWeather(String city) {
        String url="https://api.openweathermap.org/data/2.5/weather?appid=5c48904548d2583539b0550088c86d5a&q=";
        String apiUrl = url+city;
        Map<String, Object> weatherData = restTemplate.getForObject(apiUrl, Map.class);
        logger.info(weatherData+"");

        return weatherData+"";
    }

    public Map<String, Object> getWeather2(String city) {
        String apiUrl = url+city;
        Map<String, Object> weatherData = restTemplate.getForObject(apiUrl, Map.class);
        logger.info(weatherData+"");

        return weatherData;
    }*/

    public JSONObject getWeather2(String city) {
        client = new OkHttpClient();
        Request request = new Request.Builder().url(url+city).build();

        try {
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        }catch (IOException | JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
