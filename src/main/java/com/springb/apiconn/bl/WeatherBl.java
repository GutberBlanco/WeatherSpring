package com.springb.apiconn.bl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.springb.apiconn.model.Weather;
import com.springb.apiconn.repository.WeatherRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherBl {
    @Autowired
    private WeatherRepository weatherRepository;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherBl.class);

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
    public static Logger getLogger() {
        return LOGGER;
    }

    @Value("${weather.url}")
    private String url;

    public String getWeather(String city) throws IOException, JSONException {
        LOGGER.info("Obteniendo clima de ciudad");
        Response response = invokeApi(url+city+"");

        String jso = String.valueOf(response.peekBody(2048).string());
        JSONObject jsonObject = new JSONObject(jso);
        //LOGGER.info("bbbbbb"+jsonObject.getJSONObject("main").get("temp"));

        //LOGGER.info("aaaaa2"+ jsonObject.getJSONArray("weather").getJSONObject(0).get("main"));

        String climate = jsonObject.getJSONArray("weather").getJSONObject(0).get("main").toString();
        String temperature = jsonObject.getJSONObject("main").get("temp").toString();
        String humidity = jsonObject.getJSONObject("main").get("humidity").toString();
        String text = jsonObject.getJSONObject("coord").get("lon")+"";
        double lon = Double.parseDouble(text);
        double lat = (double) jsonObject.getJSONObject("coord").get("lat");


        Weather weathersave = new Weather();
        weathersave.setClimate(climate);
        weathersave.setTemperature(temperature);
        weathersave.setHumidity(humidity);
        weathersave.setLon(lon);
        weathersave.setLat(lat);



        weatherRepository.save(weathersave);

        LOGGER.info("consulta del clima guardara");

        return response.body().string();
    }

    public Response invokeApi(String endpoint) {
        LOGGER.info("Invocando el servicio para obtener el clima");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(endpoint)
                .build();

        try {
            return client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error en el servicio del clima");
        }
    }

    public Weather createWeather(Weather weather){
        LOGGER.info("Guardando consulta del clima");
        return weatherRepository.save(weather);
    }

    public List<Weather> getAllWeather(){
        LOGGER.info("Obteniendo todos los climas guardados");
        return weatherRepository.findAll();
    }

    public Weather getWeatherById(Long id){
        LOGGER.info("Obteniendo un clima guardado por id: "+ id);
        Optional<Weather> optionalWeather = weatherRepository.findById(id);
        return optionalWeather.get();
    }

    public void deleteWeather(Long id){
        LOGGER.info("borrando clima guardado mediante el id");
        weatherRepository.deleteById(id);
    }


}
