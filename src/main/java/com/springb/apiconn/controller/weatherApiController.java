package com.springb.apiconn.controller;

import com.springb.apiconn.WeatherService;
import com.springb.apiconn.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/weather")
public class weatherApiController {

    @Autowired
    private WeatherService weatherService;
    @GetMapping("{city}")
    public String searchUserByCity(@PathVariable("city") String city){
        return weatherService.getWeather2(city).toString();
    }
}
