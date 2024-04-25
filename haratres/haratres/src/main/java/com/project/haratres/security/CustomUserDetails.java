package com.project.haratres.security;

import com.project.haratres.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> roles;

    CustomUserDetails(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = Arrays.stream(user.getRole().toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    /* public Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static CustomUserDetails create(User user){
        List<GrantedAuthority> authorityList = new ArrayList<>();
        Role role = user.getRole();
        authorityList.add(new SimpleGrantedAuthority(role.toString()));
        //CUSTOMER dönüyor
        //System.out.println(
        // );
        return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), authorityList);
    }*/
/*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  //?? bu kısmı sor
        return null;
    }*/
/*
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }*/

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
