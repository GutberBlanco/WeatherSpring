package com.springb.apiconn.controller;

import com.springb.apiconn.model.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/weather")
public class weatherApiController {

    /*
    @GetMapping("{city}")
    public Users searchUserById(@PathVariable("city") String city){

        return ;
    }*/
}
