package com.example.ecommerceWeb.services;

import com.example.ecommerceWeb.Exceptions.LoginException;
import com.example.ecommerceWeb.Login.UserDetails;
import com.example.ecommerceWeb.Repositories.CustomerRepo;
import com.example.ecommerceWeb.Utils.JWTUtil;
import com.example.ecommerceWeb.entities.Customer;
import com.example.ecommerceWeb.enums.UserType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class LoginService {

    private final CustomerRepo customerRepo;
    private final JWTUtil jwtUtil;

    public ResponseEntity<?> login(UserDetails userDetails) throws LoginException {
        boolean isExist = false;
        switch (userDetails.getUserType()) {
            case ADMINISTRATOR:
                isExist = userDetails.getPassword().equals("admin") && userDetails.getEmail().
                        equals("admin@admin") && userDetails.getUserType().equals(UserType.ADMINISTRATOR);
                break;
            case CUSTOMER:
                List<Customer> customers = customerRepo.findAll().stream().
                        filter(customer -> customer.getEmail().equals(userDetails.getEmail())).
                        filter(customer -> customer.getPassword().equals(userDetails.getPassword())).
                        collect(Collectors.toList());
                if (customers.size() == 1) {
                    isExist = true;
                }
                break;
        }
        if (isExist) {
            return new ResponseEntity<>(jwtUtil.generateToken(userDetails), HttpStatus.ACCEPTED);
        } else {
            throw new LoginException("user details not valid");
        }
    }

}
