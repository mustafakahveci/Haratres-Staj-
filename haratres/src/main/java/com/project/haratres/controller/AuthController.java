package com.project.haratres.controller;

import com.project.haratres.dto.TokenDto;
import com.project.haratres.request.LoginRequest;
import com.project.haratres.request.RegisterRequest;
import com.project.haratres.service.AuthService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private final AuthService authService;

     @PostMapping("/login")
    public TokenDto login(@RequestBody LoginRequest request){
        return authService.login(request);
     }


    @PostMapping("/register")
    public TokenDto register(@Valid @RequestBody RegisterRequest request){
         return authService.register(request);
    }

}
