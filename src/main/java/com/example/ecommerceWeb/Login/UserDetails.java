package com.example.ecommerceWeb.Login;


import com.example.ecommerceWeb.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetails {
    private String password;
    private String email;
    private UserType userType;
}
