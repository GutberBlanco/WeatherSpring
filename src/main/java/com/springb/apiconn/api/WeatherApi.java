package com.springb.apiconn.api;

import com.springb.apiconn.bl.WeatherBl;
import com.springb.apiconn.model.Weather;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/weather")
@RestController
public class WeatherApi{
    @Autowired
    private WeatherBl weatherBl;

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherApi.class);

    @GetMapping("{city}")
    public String searchWeatherByCity(@PathVariable("city") String city) throws IOException, JSONException {
        LOGGER.info("Empezando funcion para obtener clima de ciudad: {}", city);
        String result = weatherBl.getWeather(city);

        return result;
    }

    @PostMapping
    public Weather createWeather(@RequestBody Weather weather){
        LOGGER.info("Empezando funcion de guardado de clima");
        return weatherBl.createWeather(weather);
    }

    @GetMapping
    public List<Weather> getAllWeather(){
        return weatherBl.getAllWeather();
    }

    @GetMapping("/id/")
    public Weather searchWeatherById(@RequestParam("id") Long id){
        LOGGER.info("Empezando funcion de obtener clima mediante el id");
        LOGGER.info("este es el id: "+id);
        return weatherBl.getWeatherById(id);
    }

    @DeleteMapping("{id}")
    public void deleteWeatherById(@PathVariable("id") Long id){
        weatherBl.deleteWeather(id);
    }
}
