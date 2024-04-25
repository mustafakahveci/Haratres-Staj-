package com.project.haratres.request;

import com.project.haratres.enums.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class RegisterRequest {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String username;
    private String password;
    private Date birthDate;
    private Role role;

}
