package com.project.haratres.service;

import com.project.haratres.model.User;
import com.project.haratres.repository.UserRepository;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Resource
    private final UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
