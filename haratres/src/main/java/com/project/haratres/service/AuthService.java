package com.project.haratres.service;

import com.project.haratres.dto.TokenDto;
import com.project.haratres.enums.Role;
import com.project.haratres.model.User;
import com.project.haratres.repository.UserRepository;
import com.project.haratres.request.LoginRequest;
import com.project.haratres.request.RegisterRequest;
import com.project.haratres.security.JwtTokenService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Resource
    private final AuthenticationManager authenticationManager;
    @Resource
    private final JwtTokenService jwtTokenService;
    @Resource
    private final PasswordEncoder passwordEncoder;
    @Resource
    private final UserRepository userRepository;
    public TokenDto login(LoginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authenticate.isAuthenticated())
            return TokenDto
                    .builder()
                    .token(jwtTokenService.generateToken(request.getUsername()))
                    .build();
        else{
            return null;
        }
    }

    public TokenDto register(RegisterRequest request) {
        final var toRegister = User.builder()
                .username(request.getUsername())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .phone(request.getPhone())
                .birthDate(request.getBirthDate())
                .role(Role.CUSTOMER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(toRegister);
        return TokenDto
                .builder()
                .token(jwtTokenService.generateToken(request.getUsername()))
                .build();
    }
}
