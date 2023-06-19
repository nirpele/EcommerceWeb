package com.example.ecommerceWeb.Controller;

import com.example.ecommerceWeb.Exceptions.LoginException;
import com.example.ecommerceWeb.Login.UserDetails;
import com.example.ecommerceWeb.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
@CrossOrigin
public class LoginController {
    private final LoginService loginService;

    @PostMapping()
    public ResponseEntity<?> getLoginToken(@RequestBody UserDetails userDetails) throws LoginException {
        try {
            return loginService.login(userDetails);
        } catch (LoginException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
