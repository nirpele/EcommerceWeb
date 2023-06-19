package com.example.ecommerceWeb.Utils;

import com.example.ecommerceWeb.Login.UserDetails;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTUtil {
    //type of encryption - סוג של אלגורתים להצפנה
    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    //our private key - מפתח ההצפנה שקיים רק אצלנו
    private String encodedSecretKey = "this+is+my+key+and+it+must+be+at+least+256+bits+long";
    //create our private key - יצירה של מפתח ההצפנה לשימוש ביצירה של הטוקנים שלנו
    private Key decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey), this.signatureAlgorithm);

    //generate key
    //we need user email, password and role (תפקיד) for create the token
    //since the userDetail is an instace of class, we need to make it hashcode
    //the token need to get clamis which is the information in hashcode
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();        //create new hash map for claims
        claims.put("userEmail", userDetails.getEmail());  //insert email
        claims.put("userType", userDetails.getUserType());  //insert user type (role)
        return createToken(claims, userDetails.getEmail()); //send the subject (email)
    }

    //we create the JWT token from the information that we got.
    private String createToken(Map<String, Object> claims, String email) {
        Instant now = Instant.now();//get current time
        return Jwts.builder()       //create JWT builder to assist us with JWT creation
                .setClaims(claims)  //set our data (clamis-user,password,role,etc...)
                .setSubject(email)  //set our subject, the first item that we will check
                .setIssuedAt(Date.from(now))  //create issued at , which is current time
                //expiration date, which after the date, we need to create a new token
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .signWith(this.decodedSecretKey) //sign the token with our secret key
                .compact();   //compact our token, that it will be smaller :)
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(this.decodedSecretKey)
                .build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    private String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Date extractExpirationDate(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private boolean isTokenExpired(String token) {
        try {
            extractAllClaims(token);
            return false;
        } catch (ExpiredJwtException err) {
            return true;
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userEmail = extractEmail(token);
        return (userEmail.equals(userDetails.getEmail()) && !isTokenExpired(token));
    }
}
