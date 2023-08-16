package com.springb.apiconn;

import com.springb.apiconn.repository.WeatherRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("/ownService")
public class WeatherService {
    private String url="https://api.openweathermap.org/data/2.5/weather?appid=5c48904548d2583539b0550088c86d5a&q=";


    @Autowired
    WeatherRepository weatherRepository;

}
