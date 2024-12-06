package com.alimento.prototype.controllers;

import com.alimento.prototype.entities.User;
import com.alimento.prototype.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

}
