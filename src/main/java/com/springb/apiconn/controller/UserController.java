package com.springb.apiconn.controller;

import com.springb.apiconn.UserService;
import com.springb.apiconn.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Users createUser(@RequestBody Users users){
        return userService.createuser(users);
    }

    @GetMapping
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public Users searchUserById(@PathVariable("id") Long id){
        return userService.getuserById(id);
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
