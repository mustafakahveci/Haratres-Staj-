package com.project.haratres.controller;

import com.project.haratres.model.User;
import com.project.haratres.service.UserService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Resource
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
